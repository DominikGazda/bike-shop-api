package pl.gazda.admin.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages ={
        "pl.gazda.admin",
        "pl.shop.commons"
})
@EnableJpaRepositories(basePackages = "pl.shop.commons.dao")
@EntityScan(basePackages = {
        "pl.shop.bike.models.model",
        "pl.shop.bike.models.model.entities"
})
@EnableFeignClients(basePackages = "pl.shop.commons.clients")
public class AdminServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminServiceApplication.class, args);
    }

}
