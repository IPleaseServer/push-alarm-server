package site.iplease.paserver.domain.email.service

interface EmailService {
    fun sendEmail(address: String, title: String, content: String)

}
