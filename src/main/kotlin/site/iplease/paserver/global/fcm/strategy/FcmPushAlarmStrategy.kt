package site.iplease.paserver.global.fcm.strategy

import reactor.core.publisher.Mono

interface FcmPushAlarmStrategy {
    fun sendAlarm(receiverId: Long, title: String, description: String): Mono<Unit>
}
