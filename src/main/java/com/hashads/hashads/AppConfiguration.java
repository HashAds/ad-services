package com.hashads.hashads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class AppConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public AdProducer adProducer()
    {
        AdProducer p = new AdProducer(env);
        return p;
    }


}
