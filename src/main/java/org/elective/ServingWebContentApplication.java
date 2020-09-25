package org.elective;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
        //exclude = {DataSourceAutoConfiguration.class},
        scanBasePackages={
                "org.elective.repos",
                "org.elective.domain.dto"
        }
)
@PropertySources({
        @PropertySource(value = "classpath:application.properties")
})
public class ServingWebContentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServingWebContentApplication.class, args);
    }

}
