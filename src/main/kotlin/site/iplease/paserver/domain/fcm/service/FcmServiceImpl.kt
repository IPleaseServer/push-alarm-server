package site.iplease.paserver.domain.fcm.service

import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import site.iplease.paserver.domain.fcm.data.entity.FcmToken
import site.iplease.paserver.domain.fcm.repository.FcmTokenRepository
import site.iplease.paserver.domain.fcm.util.FcmAccessTokenUtil
import site.iplease.paserver.domain.fcm.util.FcmMessageConverter
import site.iplease.paserver.global.fcm.config.FcmProperties

@Service
class FcmServiceImpl(
    private val fcmProperties: FcmProperties,
    private val fcmMessageConverter: FcmMessageConverter,
    private val fcmAccessTokenUtil: FcmAccessTokenUtil,
    private val fcmTokenRepository: FcmTokenRepository,
    private val webClientBuilder: WebClient.Builder
): FcmService {
    override fun sendFcm(token: String, title: String, content: String) {
        fcmMessageConverter.toMessage(token = token, title = title, content = content)
            .flatMap { message ->
                fcmAccessTokenUtil.getAccessToken()
                    .map { accessToken -> message to accessToken }
            }.flatMap {
                webClientBuilder.build().post()
                    .uri(fcmProperties.apiUrl)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer ${it.second}")
                    .header(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                    .bodyValue(it.first)
                    .retrieve().bodyToMono(String::class.java)
            }.block()!!
    }

    override fun addToken(issuerId: Long, token: String): Mono<Unit> = fcmTokenRepository.save(FcmToken(issuerId, token)).map{ }

    override fun removeToken(issuerId: Long): Mono<Unit> = fcmTokenRepository.deleteById(issuerId)
}