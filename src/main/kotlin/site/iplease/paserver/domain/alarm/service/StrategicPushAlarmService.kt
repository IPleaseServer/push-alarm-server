package site.iplease.paserver.domain.alarm.service

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import site.iplease.paserver.domain.alarm.data.dto.SendAlarmDto
import site.iplease.paserver.global.email.strategy.EmailPushAlarmStrategy
import site.iplease.paserver.global.alarm.data.type.AlarmType
import site.iplease.paserver.global.fcm.strategy.FcmPushAlarmStrategy

@Service
class StrategicPushAlarmService(
    private val emailPushAlarmStrategy: EmailPushAlarmStrategy,
    private val fcmPushAlarmStrategy: FcmPushAlarmStrategy
): PushAlarmService {
    override fun sendAlarm(data: SendAlarmDto): Mono<Unit> =
        when(data.type) {
            AlarmType.EMAIL -> emailPushAlarmStrategy.sendAlarm(receiverId = data.receiverId, header = data.title, content = data.description)
            AlarmType.FCM -> fcmPushAlarmStrategy.sendAlarm(receiverId = data.receiverId, title = data.title, description = data.description)
        }
}