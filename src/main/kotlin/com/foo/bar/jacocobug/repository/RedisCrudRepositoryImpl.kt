package com.foo.bar.jacocobug.repository

import com.foo.bar.jacocobug.config.RedisConfig
import reactor.core.publisher.Mono
import javax.inject.Singleton

@Singleton
class RedisCrudRepositoryImpl<K>(
    private val redisConfig: RedisConfig
) : RedisCrudRepository<K> {

    override fun doSomething(key: String, id: String): Mono<K> {
        return redisConfig
            .connect()
            .reactive()
            .hget(key, id) as Mono<K>
    }
}
