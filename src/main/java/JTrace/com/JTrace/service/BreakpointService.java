package JTrace.com.JTrace.service;

import JTrace.com.JTrace.model.Breakpoint;
import JTrace.com.JTrace.repository.BreakpointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BreakpointService {

    @Autowired
    private RedisTemplate <String,Object> redisTemplate;

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @Autowired
    private BreakpointRepository repository;


    private static final String CACHE_PREFIX = "breakpoint_";
    private static final String TOPIC = "breakpoint-events"; // Kafka topic


    public Breakpoint addBreakpoint(Breakpoint breakpoint) {
        Breakpoint savedBreakpoint = repository.save(breakpoint);
        String redisKey = CACHE_PREFIX + savedBreakpoint.getId();
        redisTemplate.opsForValue().set(redisKey,savedBreakpoint,1, TimeUnit.HOURS);
        kafkaTemplate.send(TOPIC, "Added new breakpoint: " + savedBreakpoint.toString());
        return savedBreakpoint;
    }

    public Breakpoint getBreakpointById(String id) {
        String redisKey = CACHE_PREFIX + id;
        Breakpoint cachedBreakpoint = (Breakpoint) redisTemplate.opsForValue().get(redisKey);

        if (cachedBreakpoint != null) {//if found returns from cache
            return cachedBreakpoint;
        }
        // If not found in cache, retrieves from DB and caches it
        Breakpoint breakpoint = repository.findById(id).orElse(null);
        if (breakpoint != null) {
            redisTemplate.opsForValue().set(redisKey, breakpoint, 1, TimeUnit.HOURS);// Cache for 1 hour
            kafkaTemplate.send(TOPIC, "Fetched breakpoint from DB: " + breakpoint.toString());
        }return breakpoint;
    }

    public List<Breakpoint> getAllBreakpoint() {
        return repository.findAll();
    }

    public void deleteBreakpoint(String id) {
        repository.deleteById(id);
        String redisKey = CACHE_PREFIX + id;
        redisTemplate.delete(redisKey);
        // Sending a Kafka message for the deleted breakpoint
        kafkaTemplate.send(TOPIC, "Deleted breakpoint with ID: " + id);
    }

    public Breakpoint updatebreakpoint(String id, Breakpoint breakpoint) {
        breakpoint.setId(id);
        Breakpoint updatedBreakpoint = repository.save(breakpoint);
        // Updates the cache
        String redisKey = CACHE_PREFIX + id;
        redisTemplate.opsForValue().set(redisKey, updatedBreakpoint, 1, TimeUnit.HOURS);  // Cache for 1 hour
        kafkaTemplate.send(TOPIC, "Updated breakpoint: " + updatedBreakpoint.toString());
        return updatedBreakpoint;
    }
}


