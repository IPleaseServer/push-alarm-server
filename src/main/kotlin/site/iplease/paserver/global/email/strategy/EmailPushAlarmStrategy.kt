package site.iplease.paserver.global.email.strategy

import reactor.core.publisher.Mono

interface EmailPushAlarmStrategy {
    fun sendAlarm(receiverId: Long, header: String, content: String): Mono<Unit>
}
