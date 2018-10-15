package com.hashads.hashads;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public AdProducer adProducer()
    {
        AdProducer p = new AdProducer();
        return p;
    }
}
