package site.iplease.paserver.domain.email.util

import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import site.iplease.paserver.infra.account.service.AccountQueryService

@Component
class HttpEmailAddressResolver(
    private val accountQueryService: AccountQueryService
): EmailAddressResolver {
    override fun resolve(receiverId: Long): Mono<String> =
        accountQueryService.findById(receiverId) { common.email }
}