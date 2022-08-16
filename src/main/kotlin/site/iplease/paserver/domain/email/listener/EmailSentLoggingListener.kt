package site.iplease.paserver.domain.email.listener

import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import site.iplease.paserver.domain.email.data.event.EmailSentEvent
import kotlin.random.Random

@Component
class EmailSentLoggingListener {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @EventListener(EmailSentEvent::class)
    fun handleEvent(event: EmailSentEvent) {
        val id = Random.nextLong()
        logger.info("[$id] 이메일을 전송하였습니다!")
        logger.info("[$id] 수신자이메일: ${event.address}")
        logger.info("[$id] 제목: ${event.title}")
        logger.info("[$id] 내용: ${event.content}")
    }
}