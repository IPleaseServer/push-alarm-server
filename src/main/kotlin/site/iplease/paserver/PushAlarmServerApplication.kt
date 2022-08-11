package site.iplease.paserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import site.iplease.paserver.domain.email.config.EmailProperties
import site.iplease.paserver.global.fcm.config.FcmProperties

@SpringBootApplication
@ConfigurationPropertiesScan(basePackageClasses = [EmailProperties::class, FcmProperties::class])
class PushAlarmServerApplication

fun main(args: Array<String>) {
	runApplication<PushAlarmServerApplication>(*args)
}
