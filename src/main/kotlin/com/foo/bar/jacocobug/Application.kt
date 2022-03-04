package com.foo.bar.jacocobug

import io.micronaut.runtime.Micronaut

@lombok.Generated
fun main(args: Array<String>) {
    Micronaut.build()
        .args(*args)
        .packages("com.foo.bar.jacocobug")
        .start()
}
