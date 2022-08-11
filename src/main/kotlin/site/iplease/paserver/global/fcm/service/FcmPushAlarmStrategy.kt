package site.iplease.paserver.global.fcm.service

import reactor.core.publisher.Mono

interface FcmPushAlarmStrategy {
    fun sendAlarm(title: String, description: String): Mono<Unit>
}
