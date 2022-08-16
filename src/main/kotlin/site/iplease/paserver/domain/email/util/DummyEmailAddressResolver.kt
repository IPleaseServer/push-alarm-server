package site.iplease.paserver.domain.email.util

import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

//@Component
class DummyEmailAddressResolver: EmailAddressResolver {
    override fun resolve(receiverId: Long): Mono<String> = "iplease.user@gmail.com".toMono()
}