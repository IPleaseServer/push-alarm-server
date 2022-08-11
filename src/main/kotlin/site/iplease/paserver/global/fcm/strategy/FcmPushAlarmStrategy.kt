package site.iplease.paserver.global.fcm.strategy

import reactor.core.publisher.Mono

interface FcmPushAlarmStrategy {
    fun sendAlarm(title: String, description: String): Mono<Unit>
}
