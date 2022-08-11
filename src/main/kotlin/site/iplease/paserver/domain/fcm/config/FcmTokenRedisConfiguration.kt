package site.iplease.paserver.domain.fcm.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import site.iplease.paserver.domain.fcm.data.entity.FcmToken

@Configuration
class FcmTokenRedisConfiguration {
    @Bean
    fun fcmTokenReactiveRedisTemplate(connectionFactory: ReactiveRedisConnectionFactory, objectMapper: ObjectMapper): ReactiveRedisTemplate<String, FcmToken> =
        Jackson2JsonRedisSerializer(FcmToken::class.java).apply { setObjectMapper(objectMapper) }
            .let { (StringRedisSerializer() to it) }
            .let {
                RedisSerializationContext
                    .newSerializationContext<String, FcmToken>()
                    .key(it.first).value(it.second)
                    .hashKey(it.first).hashValue(it.second)
                    .build()
            }.let { ReactiveRedisTemplate(connectionFactory, it) }
}