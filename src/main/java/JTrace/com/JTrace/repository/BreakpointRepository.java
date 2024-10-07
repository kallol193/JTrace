package JTrace.com.JTrace.repository;

import JTrace.com.JTrace.model.Breakpoint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreakpointRepository extends MongoRepository<Breakpoint,String> {
}
