package site.iplease.paserver.global.fcm.strategy

import org.slf4j.LoggerFactory
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

//@Component
@Deprecated("use event driven or another implementation")
class DummyFcmPushAlarmStrategy: FcmPushAlarmStrategy {
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun sendAlarm(receiverId: Long, title: String, description: String): Mono<Unit> {
        logger.info("sendFCMAlarm: receiverId=$receiverId, title=$title, description=$description")
        return Unit.toMono()
    }
}