package site.iplease.paserver.domain.email.strategy

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import site.iplease.paserver.domain.email.data.event.SendEmailRequestedEvent
import site.iplease.paserver.global.email.strategy.EmailPushAlarmStrategy

@Component
class EventDrivenEmailPushAlarmStrategy(
    val applicationEventPublisher: ApplicationEventPublisher
): EmailPushAlarmStrategy {
    override fun sendAlarm(receiverId: Long, header: String, content: String): Mono<Unit> =
        SendEmailRequestedEvent(receiverId, header, content).toMono()
            .map { applicationEventPublisher.publishEvent(it) }
}