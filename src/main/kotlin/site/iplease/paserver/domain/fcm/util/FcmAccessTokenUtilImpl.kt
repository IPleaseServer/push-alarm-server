package site.iplease.paserver.domain.fcm.util

import com.google.auth.oauth2.GoogleCredentials
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import site.iplease.paserver.global.fcm.config.FcmProperties

@Component
class FcmAccessTokenUtilImpl(
    private val fcmProperties: FcmProperties
): FcmAccessTokenUtil {
    override fun getAccessToken(): Mono<String> =
        fcmProperties.firebaseConfigPath.toMono()
            .map { GoogleCredentials.fromStream(ClassPathResource(it).inputStream) }
            .map { it.createScoped(listOf("https://www.googleapis.com/auth/cloud-platform")) }
            .map { it.refreshIfExpired(); it.accessToken.tokenValue }
}