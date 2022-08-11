package site.iplease.paserver.domain.fcm.listener

import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import site.iplease.paserver.domain.email.data.event.SendEmailRequestedEvent
import site.iplease.paserver.domain.fcm.service.FcmService
import site.iplease.paserver.domain.fcm.util.FcmTokenResolver

@Component
class SendFcmRequestedEventBusinessListener(
    private val fcmTokenResolver: FcmTokenResolver,
    private val fcmService: FcmService
) {
    @EventListener
    fun handle(event: SendEmailRequestedEvent) {
        fcmTokenResolver.resolve(event.receiverId)
            .map { token -> fcmService.sendFcm(token = token, title = event.title, content = event.content) }
            .block()!!
    }
}