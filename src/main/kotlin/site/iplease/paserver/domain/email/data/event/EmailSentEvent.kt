package site.iplease.paserver.domain.email.data.event

data class EmailSentEvent(
    val address: String,
    val title: String,
    val content: String
)