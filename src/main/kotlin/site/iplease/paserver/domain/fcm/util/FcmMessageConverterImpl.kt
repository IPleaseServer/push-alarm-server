package site.iplease.paserver.domain.fcm.util

import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Component
class FcmMessageConverterImpl: FcmMessageConverter {
    override fun toMessage(token: String, title: String, content: String): Mono<Message> =
        Message.builder()
            .setToken(token)
            .setNotification(
                Notification.builder()
                    .setTitle(title)
                    .setBody(content)
                    .build()
            ).build().toMono()
}