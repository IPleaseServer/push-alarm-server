package site.iplease.paserver.domain.alarm.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import site.iplease.paserver.domain.alarm.data.dto.SendAlarmDto

@Service
class DummyPushAlarmService: PushAlarmService {
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun sendAlarm(data: SendAlarmDto): Mono<Unit> =
        data.toMono().map {
            logger.info("푸시알림을 송신합니다!")
            logger.info("type: ${data.type}")
            logger.info("title: ${data.title}")
            logger.info("description: ${data.description}")
        }
}