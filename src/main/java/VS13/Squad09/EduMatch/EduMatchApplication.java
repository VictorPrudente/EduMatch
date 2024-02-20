package VS13.Squad09.EduMatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class EduMatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(EduMatchApplication.class, args);
	}

}
