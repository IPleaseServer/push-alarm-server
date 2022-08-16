package site.iplease.paserver.domain.email.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("iplease.email")
data class EmailProperties(
    val from: String,
    val cssFilePath: String
)
