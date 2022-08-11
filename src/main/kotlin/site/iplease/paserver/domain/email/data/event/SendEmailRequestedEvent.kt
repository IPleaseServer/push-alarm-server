package site.iplease.paserver.domain.email.data.event

data class SendEmailRequestedEvent(
    val receiverId: Long,
    val title: String,
    val content: String
)