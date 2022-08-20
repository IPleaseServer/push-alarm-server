package site.iplease.paserver.domain.fcm.strategy

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import site.iplease.paserver.domain.email.data.event.SendEmailRequestedEvent
import site.iplease.paserver.global.fcm.strategy.FcmPushAlarmStrategy

@Component
class TemporaryFcmPushAlarmStrategy (
    private val applicationEventPublisher: ApplicationEventPublisher
): FcmPushAlarmStrategy {
    override fun sendAlarm(receiverId: Long, title: String, description: String): Mono<Unit> =
        SendEmailRequestedEvent(receiverId, title, description).toMono()
            .map { applicationEventPublisher.publishEvent(it) }
}