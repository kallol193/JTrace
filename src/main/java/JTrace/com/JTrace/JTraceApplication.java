package JTrace.com.JTrace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class JTraceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JTraceApplication.class, args);
	}

}
