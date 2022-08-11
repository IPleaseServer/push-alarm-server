package site.iplease.paserver.domain.fcm.util

import reactor.core.publisher.Mono

interface FcmTokenResolver {
    fun resolve(receiverId: Long): Mono<String>
}
