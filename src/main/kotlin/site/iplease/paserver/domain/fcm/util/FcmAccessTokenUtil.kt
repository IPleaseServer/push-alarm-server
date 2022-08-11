package site.iplease.paserver.domain.fcm.util

import reactor.core.publisher.Mono

interface FcmAccessTokenUtil {
    fun getAccessToken(): Mono<String>
}
