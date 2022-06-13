package site.iplease.paserver.domain.alarm.util

import reactor.core.publisher.Mono
import site.iplease.paserver.domain.alarm.data.dto.SendAlarmDto
import site.iplease.paserver.global.alarm.data.message.SendAlarmMessage

interface SendAlarmConverter {
    fun convert(message: SendAlarmMessage): Mono<SendAlarmDto>
}
