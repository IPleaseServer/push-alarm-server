package site.iplease.paserver.domain.alarm.subscriber

import org.slf4j.LoggerFactory
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
    private val logger = LoggerFactory.getLogger(this::class.java)
    override fun subscribe(message: SendAlarmMessage) {
        sendAlarmConverter.convert(message)
            .flatMap { data -> pushAlarmService.sendAlarm(data) }
            .doOnError { throwable ->
                logger.error("알람송신로직 수행중 오류가 발생하였습니다!")
                logger.error("type: ${throwable::class.simpleName}")
                logger.error("exception: ${throwable.message}")
            }.onErrorResume { Mono.empty() }
            .block()
    }
}