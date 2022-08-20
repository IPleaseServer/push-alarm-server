package site.iplease.paserver.domain.fcm.listener

import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import site.iplease.paserver.domain.email.data.event.SendEmailRequestedEvent
import site.iplease.paserver.domain.fcm.data.event.SendFcmRequestedEvent

@Component
class TemporarySendFcmRequestEventBusinessListenerWithEmail(
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    @EventListener
    fun handle(event: SendFcmRequestedEvent) {
        applicationEventPublisher.publishEvent(SendEmailRequestedEvent(event.receiverId, event.title, event.content))
    }
}