package _06lab_springdataadvancedquerying.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class JavaConfig {

    private Environment environment;

    @Autowired
    public JavaConfig(Environment environment) {
        this.environment = environment;
    }
}
