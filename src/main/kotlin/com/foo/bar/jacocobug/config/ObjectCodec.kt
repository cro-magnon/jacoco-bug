package com.foo.bar.jacocobug.config

import io.lettuce.core.codec.RedisCodec
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.nio.ByteBuffer

class ObjectCodec : RedisCodec<String, Any> {
    private val charset = Charsets.UTF_8

    override fun decodeKey(bytes: ByteBuffer): String = charset.decode(bytes).toString()

    override fun decodeValue(bytes: ByteBuffer): Any? {
        val array = ByteArray(bytes.remaining())
        bytes[array]
        val stream = ObjectInputStream(ByteArrayInputStream(array))
        return stream.readObject()
    }

    override fun encodeKey(key: String): ByteBuffer = charset.encode(key)

    override fun encodeValue(value: Any): ByteBuffer? {
        val bytes = ByteArrayOutputStream()
        val os = ObjectOutputStream(bytes)
        os.writeObject(value)
        return ByteBuffer.wrap(bytes.toByteArray())
    }
}
