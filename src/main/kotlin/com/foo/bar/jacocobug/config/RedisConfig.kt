package com.foo.bar.jacocobug.config

import com.foo.bar.jacocobug.config.properties.RedisProperties
import io.lettuce.core.RedisURI
import io.lettuce.core.cluster.RedisClusterClient
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection
import io.micronaut.context.annotation.Requires
import jakarta.inject.Singleton
import java.time.Duration
import javax.annotation.PreDestroy

@Singleton
@Requires(property = "redis")
class RedisConfig(
    private val redisProperties: RedisProperties
) {
    private val connection: StatefulRedisClusterConnection<String, Any>

    init {
        connection = getStatefulRedisClusterConnection()
    }

    @PreDestroy
    fun destroy() {
        connection.close()
    }

    fun connect(): StatefulRedisClusterConnection<String, Any> = connection

    private fun getStatefulRedisClusterConnection(): StatefulRedisClusterConnection<String, Any> {
        val redisUri: RedisURI = RedisURI.Builder
            .redis(redisProperties.host, redisProperties.port)
            .withPassword(redisProperties.password?.toCharArray())
            .withTimeout(Duration.parse("PT${redisProperties.timeout}"))
            .build()

        return RedisClusterClient
            .create(redisUri)
            .connect(ObjectCodec())
    }
}
