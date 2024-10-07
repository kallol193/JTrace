package JTrace.com.JTrace.service;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class DebuggingMetricService {

    private final MeterRegistry meterRegistry;

    public DebuggingMetricService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }
    public void trackBreakpointHit(){
        meterRegistry.counter("debugger.breakpoints.hit").increment();
    }
    public void trackMethodExecutionTime(long exectionTime){
        meterRegistry.timer("debugger.method.execution").record(exectionTime, TimeUnit.MILLISECONDS);
    }
}
