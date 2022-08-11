package site.iplease.paserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import site.iplease.paserver.domain.email.config.EmailProperties

@SpringBootApplication
@ConfigurationPropertiesScan(basePackageClasses = [EmailProperties::class])
class PushAlarmServerApplication

fun main(args: Array<String>) {
	runApplication<PushAlarmServerApplication>(*args)
}
