package site.iplease.paserver.domain.alarm.subscriber

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import site.iplease.paserver.domain.alarm.service.PushAlarmService
import site.iplease.paserver.domain.alarm.util.SendAlarmConverter
import site.iplease.paserver.global.alarm.data.message.SendAlarmMessage
import site.iplease.paserver.global.alarm.subscriber.SendAlarmSubscriber

@Service
class SendAlarmSubscriberV1(
    private val sendAlarmConverter: SendAlarmConverter,
    private val pushAlarmService: PushAlarmService
): SendAlarmSubscriber {
    override fun subscribe(message: SendAlarmMessage) {
        sendAlarmConverter.convert(message)
            .flatMap { data -> pushAlarmService.sendAlarm(data) }
            .onErrorResume { Mono.empty() }
            .block()
    }
}