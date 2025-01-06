package com.papaska.data.infrastructure.local.datastore

import androidx.datastore.core.Serializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

/**
 * Сериализатор для [ProtoDataStore]
 *
 * Этот класс реализует интерфейс [Serializer] и обеспечивает сериализацию и десериализацию
 * объектов типа [T]. В случае ошибок десериализации возвращает заданное значение по умолчанию.
 *
 * @param T Тип данных, для которого используется сериализатор.
 */
internal class ProtoDataStoreSerializer<T>(
    private val serializer: KSerializer<T>,
    override val defaultValue: T,
) : Serializer<T> {
    override suspend fun readFrom(input: InputStream): T =
        try {
            Json.decodeFromString(
                deserializer = serializer,
                string = input.readBytes().decodeToString(),
            )
        } catch (e: SerializationException) {
            defaultValue
        }

    override suspend fun writeTo(
        t: T,
        output: OutputStream,
    ) {
        withContext(Dispatchers.IO) {
            output.write(
                Json
                    .encodeToString(
                        serializer = serializer,
                        value = t,
                    ).encodeToByteArray(),
            )
        }
    }
}
