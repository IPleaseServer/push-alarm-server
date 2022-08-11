package site.iplease.paserver.domain.fcm.util

import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import site.iplease.paserver.domain.fcm.repository.FcmTokenRepository

@Component
class FcmTokenResolverImpl(
    private val fcmTokenRepository: FcmTokenRepository
): FcmTokenResolver {
    override fun resolve(receiverId: Long): Mono<String> =
        fcmTokenRepository.findByAccountId(receiverId).map { it.token }
}