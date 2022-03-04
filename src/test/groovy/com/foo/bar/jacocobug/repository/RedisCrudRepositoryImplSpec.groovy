package com.foo.bar.jacocobug.repository

import com.foo.bar.jacocobug.config.RedisConfig
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection
import io.lettuce.core.cluster.api.reactive.RedisAdvancedClusterReactiveCommands
import io.micronaut.context.annotation.Any
import reactor.core.publisher.Mono
import spock.lang.Specification

class RedisCrudRepositoryImplSpec extends Specification {

    RedisConfig config = Mock()
    RedisCrudRepository repository = new RedisCrudRepositoryImpl(config)

    def 'Test findAllById'() {
        setup:
        StatefulRedisClusterConnection<String, Any> connection = Mock()
        RedisAdvancedClusterReactiveCommands<Any, Any> command = Mock()

        when:
        String something = repository.doSomething('foo', 'bar').block()

        then:
        1 * config.connect() >> connection
        1 * connection.reactive() >> command
        1 * command.hget('foo', 'bar') >> Mono.just('baz')
        something == 'baz'
        0 * _._
    }
}
