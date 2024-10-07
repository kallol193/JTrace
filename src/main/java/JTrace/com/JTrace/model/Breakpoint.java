package JTrace.com.JTrace.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "breakpoints")
@AllArgsConstructor
@NoArgsConstructor
public class Breakpoint {


        @Id
        private String id;
        private String fileName;
        private int lineNumber;
        private String condition;

}
