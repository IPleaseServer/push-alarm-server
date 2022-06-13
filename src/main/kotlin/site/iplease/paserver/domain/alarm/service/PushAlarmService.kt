package site.iplease.paserver.domain.alarm.service

import reactor.core.publisher.Mono
import site.iplease.paserver.domain.alarm.data.dto.SendAlarmDto

interface PushAlarmService {
    fun sendAlarm(data: SendAlarmDto): Mono<Unit>
}
