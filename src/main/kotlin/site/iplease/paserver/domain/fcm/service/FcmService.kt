package site.iplease.paserver.domain.fcm.service

import reactor.core.publisher.Mono

interface FcmService {
    fun sendFcm(token: String, title: String, content: String)
    fun addToken(issuerId: Long, token: String): Mono<Unit>
    fun removeToken(issuerId: Long): Mono<Unit>
}
