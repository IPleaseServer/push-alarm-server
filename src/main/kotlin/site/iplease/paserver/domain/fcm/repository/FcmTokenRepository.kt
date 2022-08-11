package site.iplease.paserver.domain.fcm.repository

import reactor.core.publisher.Mono
import site.iplease.paserver.domain.fcm.data.entity.FcmToken

interface FcmTokenRepository {
    fun findByAccountId(receiverId: Long): Mono<FcmToken>
    fun save(fcmToken: FcmToken): Mono<FcmToken>
    fun deleteById(issuerId: Long): Mono<Unit>
}
