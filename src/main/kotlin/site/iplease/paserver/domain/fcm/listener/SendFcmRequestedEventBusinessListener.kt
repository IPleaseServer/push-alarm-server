package site.iplease.paserver.domain.fcm.listener

import org.springframework.context.event.EventListener
import site.iplease.paserver.domain.fcm.data.event.SendFcmRequestedEvent
import site.iplease.paserver.domain.fcm.service.FcmService
import site.iplease.paserver.domain.fcm.util.FcmTokenResolver

class SendFcmRequestedEventBusinessListener(
    private val fcmTokenResolver: FcmTokenResolver,
    private val fcmService: FcmService
) {
    @EventListener
    fun handle(event: SendFcmRequestedEvent) {
        fcmTokenResolver.resolve(event.receiverId)
            .map { token -> fcmService.sendFcm(token = token, title = event.title, content = event.content) }
            .block()!!
    }
}