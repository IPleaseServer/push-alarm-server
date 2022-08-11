package site.iplease.paserver.domain.email.listener

import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import site.iplease.paserver.domain.email.data.event.SendEmailRequestedEvent
import site.iplease.paserver.domain.email.service.EmailService
import site.iplease.paserver.domain.email.util.EmailAddressResolver
import site.iplease.paserver.domain.email.util.EmailContentResolver

@Component
class SendEmailRequestedEventBusinessListener(
    private val emailAddressResolver: EmailAddressResolver,
    private val emailContentResolver: EmailContentResolver,
    private val emailService: EmailService
) {
    @EventListener
    fun handle(event: SendEmailRequestedEvent) {
        emailAddressResolver.resolve(event.receiverId)
            .flatMap { address -> emailContentResolver.resolve(event.content).map { content -> address to content } }
            .map { data -> emailService.sendEmail(address = data.first, title = event.title, content = data.second) }
            .block()!!
    }
}