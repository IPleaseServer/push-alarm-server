package site.iplease.paserver.domain.email.util

import reactor.core.publisher.Mono

interface EmailAddressResolver {
    fun resolve(receiverId: Long): Mono<String>

}
