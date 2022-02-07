package com.example.rabbitmq.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.ConfigurableApplicationContext

class AppRunner : CommandLineRunner {
    private val duration = 30000

    @Autowired
    lateinit var ctx: ConfigurableApplicationContext

    override fun run(vararg arg: String?) {
        println("Ready ... running for " + duration + "ms")
        Thread.sleep(duration.toLong())
        ctx.close()
    }
}