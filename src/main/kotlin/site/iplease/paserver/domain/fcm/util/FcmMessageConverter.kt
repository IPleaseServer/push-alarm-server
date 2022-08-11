package site.iplease.paserver.domain.fcm.util

import com.google.firebase.messaging.Message
import reactor.core.publisher.Mono

interface FcmMessageConverter {
    fun toMessage(token: String, title: String, content: String): Mono<Message>
}
