package site.iplease.paserver.domain.email.service

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import reactor.core.scheduler.Schedulers
import reactor.kotlin.core.publisher.toMono

@Service
class SmtpEmailService(private val javaMailSender: JavaMailSender): EmailService {
    override fun sendEmail(address: String, title: String, content: String) {
        Unit.toMono()
            .subscribeOn(Schedulers.boundedElastic())
            .map { javaMailSender.createMimeMessage() }
            .map { message -> MimeMessageHelper(message, true)
                .apply { setSubject(title); setTo(address); setText(content, true) }
                .let { message }
                //jms 는blocking I/O이므로, publisher가 consumer에 비해 느리다 아래링크참조
                //https://velog.io/@nkjang/publishOn-subscribeOn
            }.subscribe { javaMailSender.send(it) }
    }
}