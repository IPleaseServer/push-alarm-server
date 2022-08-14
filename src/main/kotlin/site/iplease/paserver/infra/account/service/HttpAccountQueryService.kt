package site.iplease.paserver.infra.account.service

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import site.iplease.paserver.infra.account.data.response.ProfileResponse
import site.iplease.paserver.infra.account.exception.ApiException

@Service
class HttpAccountQueryService(
    private val webClientBuilder: WebClient.Builder
): AccountQueryService {
    override fun <T> findById(accountId: Long, mapper: ProfileResponse.() -> T): Mono<T> =
        webClientBuilder.baseUrl("lb://account-server").build()
            .get()
            .uri("/api/v1/account/profile/query/id/$accountId")
            .retrieve()
            .onStatus({ !it.is2xxSuccessful }, { handleError("/api/v1/account/profile/query/id/$accountId", it) })
            .bodyToMono(ProfileResponse::class.java)
            .map { mapper.invoke(it) }

    fun handleError(path: String, response: ClientResponse): Mono<Throwable> = Mono.error(ApiException("$path 로 요청을 보내는 중 오류가 발생하였습니다! status: ${response.statusCode()}"))
}