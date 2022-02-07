package com.example.rabbitmq.demo

import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class Client {

    @Autowired
    private lateinit var template: RabbitTemplate

    @Autowired
    private lateinit var exchange: DirectExchange

    init {
        // just to move things in time
        Executors.newSingleThreadScheduledExecutor().run {
            schedule(::performUpload, 5, TimeUnit.SECONDS)
        }
    }

    private fun performUpload() {
        exchange.name?.let {
            this.javaClass.getResourceAsStream("/590_roles.json.gz")?.use { file ->
                println(
                    "ungzipped in client: ${
                        ungzip(
                            (template.convertSendAndReceive(
                                it,
                                "rpc",
                                file.readBytes()
                            ) as ByteArray)
                        )
                    }"
                )
            }
        }
    }
}