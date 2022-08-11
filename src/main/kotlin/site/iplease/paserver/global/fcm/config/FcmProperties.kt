package site.iplease.paserver.global.fcm.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("iplease.fcm")
data class FcmProperties(
    val firebaseConfigPath: String,
    val apiUrl: String
)