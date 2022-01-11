package pl.shop.bike.read.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages ={
        "pl.shop.bike.read",
        "pl.shop.commons.dao",
        "pl.shop.commons.errors"
})
@EnableJpaRepositories(basePackages = "pl.shop.commons.dao")
@EntityScan(basePackages = {
        "pl.shop.bike.models.model",
        "pl.shop.bike.models.model.entities",
})
public class ReadDaoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadDaoServiceApplication.class, args);
    }

}
