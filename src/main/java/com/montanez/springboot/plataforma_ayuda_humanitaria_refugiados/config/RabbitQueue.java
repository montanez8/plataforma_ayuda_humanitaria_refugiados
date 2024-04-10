package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitQueue {
    @Bean
    public Queue myQueue() {
        return new Queue("colaEnvio", true);
    }
}
