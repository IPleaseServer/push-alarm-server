package site.iplease.paserver.domain.email.listener

import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import site.iplease.paserver.domain.email.data.event.SendEmailRequestedEvent
import kotlin.random.Random

@Component
class SendEmailRequestedEventLoggingListener {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @EventListener
    fun handle(event: SendEmailRequestedEvent) {
        val id = Random.nextLong()
        logger.info("[$id] 이메일을 전송합니다!")
        logger.info("[$id] 수신자ID: ${event.receiverId}")
        logger.info("[$id] 제목: ${event.title}")
        logger.info("[$id] 내용: ${event.content}")
    }
}