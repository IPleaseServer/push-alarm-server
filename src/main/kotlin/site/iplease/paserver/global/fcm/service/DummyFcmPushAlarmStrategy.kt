package site.iplease.paserver.global.fcm.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Component
class DummyFcmPushAlarmStrategy: FcmPushAlarmStrategy {
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun sendAlarm(title: String, description: String): Mono<Unit> {
        logger.info("sendFCMAlarm: title=$title, description=$description")
        return Unit.toMono()
    }
}