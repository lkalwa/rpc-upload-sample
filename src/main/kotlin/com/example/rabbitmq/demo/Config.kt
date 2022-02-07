package com.example.rabbitmq.demo

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Profile("rpc")
@Configuration
class Config {

    @Profile("worker")
    private class WorkerConfig {
        @Bean
        fun worker(): Worker {
            return Worker()
        }

        @Bean
        fun exchange(): DirectExchange {
            return DirectExchange("rpc")
        }

        @Bean
        fun queue(): Queue {
            return Queue("rpc.requests")
        }

        @Bean
        fun binding(exchange: DirectExchange, queue: Queue): Binding {
            return BindingBuilder.bind(queue).to(exchange).with("rpc")
        }
    }

    @Profile("client")
    private class ClientConfig {
        @Bean
        fun exchange(): DirectExchange {
            return DirectExchange("rpc")
        }

        @Bean
        fun client(): Client {
            return Client()
        }
    }
}