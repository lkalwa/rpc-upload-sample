package com.example.rabbitmq.demo

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile


@SpringBootApplication
class Application {
	@Bean
	@Profile("rpc")
	fun tutorial(): CommandLineRunner {
		return AppRunner()
	}

	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			runApplication<Application>(*args)
		}
	}

}

