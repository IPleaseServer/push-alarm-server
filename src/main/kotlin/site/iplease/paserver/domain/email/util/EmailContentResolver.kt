package site.iplease.paserver.domain.email.util

import reactor.core.publisher.Mono

interface EmailContentResolver {
    fun resolve(content: String): Mono<String>

}
