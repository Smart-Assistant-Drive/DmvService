package com.smartassistantdrive.dmvservice.health

import org.bson.json.JsonObject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

/**
 *
 */
@Component("tracing")
@Suppress("TooGenericExceptionCaught")
class HealthCheck : HealthIndicator {

	private val logger: Logger = LoggerFactory.getLogger(HealthCheck::class.java)

	override fun health(): Health {
		val pair = check() // perform some specific health check
		if (pair.first != 0) {
			return Health.down().build()
		}
		return Health.up().withDetail("services", JsonObject(pair.second)).build()
	}

	/**
	 *
	 */
	fun check(): Pair<Int, String> {
		try {
			// zipkin service check
			val uri = "http://localhost:9411/api/v2/services"

			val restTemplate = RestTemplate()
			val callResult = restTemplate.getForObject(uri, String::class.java)

			logger.info("result: $callResult")
			return Pair(0, callResult!!)
		} catch (e: Exception) {
			logger.error("Error: ${e.message}")
			return Pair(1, e.message!!)
		}
	}
}
