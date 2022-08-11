package site.iplease.paserver.domain.alarm.util

import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import site.iplease.paserver.domain.alarm.data.dto.SendAlarmDto
import site.iplease.paserver.global.alarm.data.message.SendAlarmMessage

@Component
class SendAlarmConverterImpl: SendAlarmConverter {
    override fun convert(message: SendAlarmMessage): Mono<SendAlarmDto> =
        message.toMono()
            .map { SendAlarmDto(
                type = it.type,
                receiverId = it.receiverId,
                title = it.title,
                description = it.description
            ) }
}