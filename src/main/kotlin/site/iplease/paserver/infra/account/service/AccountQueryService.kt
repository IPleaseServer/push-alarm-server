package site.iplease.paserver.infra.account.service

import reactor.core.publisher.Mono
import site.iplease.paserver.infra.account.data.response.ProfileResponse

interface AccountQueryService {
    fun <T> findById(accountId: Long, mapper: (ProfileResponse.() -> T)): Mono<T>
}