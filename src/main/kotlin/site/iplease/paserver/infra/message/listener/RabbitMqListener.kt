package site.iplease.paserver.infra.message.listener

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import site.iplease.paserver.global.alarm.data.message.SendAlarmMessage
import site.iplease.paserver.global.alarm.subscriber.SendAlarmSubscriber
import site.iplease.paserver.infra.message.type.MessageType

@Component
class RabbitMqListener(
    private val sendAlarmSubscriber: SendAlarmSubscriber,
    private val objectMapper: ObjectMapper
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @RabbitListener(queues = ["iplease.alarm"])
    fun listen(message: Message) {
        val routingKey = message.messageProperties.receivedRoutingKey
        when (MessageType.of(routingKey)) {
            MessageType.SEND_ALARM ->
                objectMapper.toMono()
                    .map { it.readValue(String(message.body), SendAlarmMessage::class.java) }
                    .doOnError { throwable ->
                        logger.error("메세지를 직렬화하는도중 오류가 발생하였습니다!")
                        logger.error("exception: ${throwable.localizedMessage}")
                        logger.error("payload(string): ${String(message.body)}")
                        logger.error("payload(byte): ${message.body}")
                    }.onErrorResume { Mono.empty() }
                    .map { sendAlarmSubscriber.subscribe(message = it) }
                    .doOnError { throwable ->
                        logger.error("알 수 없는 오류가 발생하였습니다!")
                        logger.error("exception: ${throwable.localizedMessage}")
                        logger.error("payload(string): ${String(message.body)}")
                        logger.error("payload(byte): ${message.body}")
                    }.onErrorResume { Mono.empty() }
                    .block()
            else -> {
                logger.warn("처리대상이 아닌 메세지가 바인딩되어있습니다!")
                logger.warn("routingKey: ${message.messageProperties.receivedRoutingKey}")
                logger.warn("payload(string): ${String(message.body)}")
                logger.warn("payload(byte): ${message.body}")
            }
        }
    }
}