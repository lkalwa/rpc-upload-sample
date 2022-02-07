package com.example.rabbitmq.demo

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.neo4j.driver.AuthTokens
import org.neo4j.driver.GraphDatabase
import java.io.ByteArrayOutputStream
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream

class Worker {
    val driver = GraphDatabase.driver("neo4j://localhost:7687", AuthTokens.basic("neo4j", "password"))

    @RabbitListener(queues = ["rpc.requests"])
    fun process(content: ByteArray): ByteArray =
        gzip(
            driver.session().writeTransaction { tx ->
                tx.run(
                    "CALL org.atomic.upload(1, \$content) YIELD value RETURN value",
                    mapOf("content" to ungzip(content))
                ).single().get(0).asString()
            }
        )
}


fun ungzip(content: ByteArray): String =
    GZIPInputStream(content.inputStream()).bufferedReader(Charsets.UTF_8).use { it.readText() }

fun gzip(content: String): ByteArray =
    ByteArrayOutputStream().let {
        GZIPOutputStream(it).bufferedWriter(Charsets.UTF_8).use { gzipped -> gzipped.write(content) }
        it.toByteArray()
    }