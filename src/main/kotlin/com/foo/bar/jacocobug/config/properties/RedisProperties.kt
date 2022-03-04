package com.foo.bar.jacocobug.config.properties

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("redis")
class RedisProperties {
    var host: String? = null
    var port: Int = 6379
    var timeout: String? = null
    var password: String? = null
}
