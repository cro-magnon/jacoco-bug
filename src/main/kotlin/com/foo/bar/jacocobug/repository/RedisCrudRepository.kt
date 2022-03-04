package com.foo.bar.jacocobug.repository

import reactor.core.publisher.Mono

interface RedisCrudRepository<T> {
    fun doSomething(key: String, id: String): Mono<T>
}
