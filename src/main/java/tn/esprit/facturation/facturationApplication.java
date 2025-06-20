package tn.esprit.facturation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableJpaRepositories
@EnableAspectJAutoProxy
@SpringBootApplication
public class facturationApplication {

    public static void main(String[] args) {
        SpringApplication.run(facturationApplication.class, args);
    }

}
