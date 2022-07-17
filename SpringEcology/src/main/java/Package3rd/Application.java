package Package3rd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Application {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(SpringApplication.class, args);
    }
}
