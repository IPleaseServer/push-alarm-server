package site.iplease.paserver.domain.fcm.repository

import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import site.iplease.paserver.domain.fcm.data.entity.FcmToken
import site.iplease.paserver.global.fcm.config.FcmProperties

@Component
class RedisFcmTokenRepository(
    private val redisTemplate: ReactiveRedisTemplate<String, FcmToken>,
    private val fcmProperties: FcmProperties
): FcmTokenRepository {
    override fun findByAccountId(receiverId: Long): Mono<FcmToken> =
        redisTemplate.opsForValue().get(formatKey(receiverId))

    private fun formatKey(receiverId: Long) = "${fcmProperties.redisKeyPrefix}_$receiverId"
}