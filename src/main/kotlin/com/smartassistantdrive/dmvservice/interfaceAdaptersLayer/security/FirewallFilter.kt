package com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.security

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 *
 */
class FirewallFilter : Filter {

	private val allowedIPs: ArrayList<String> = ArrayList()
	private var logger: Logger = LoggerFactory.getLogger(FirewallFilter::class.java)

	init {
		allowedIPs.add("127.0.0.1")
	}

	override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
		val httpRequest = request as HttpServletRequest
		val httpResponse = response as HttpServletResponse

		val clientIP: String = getClientIP(httpRequest).toString()

		logger.info("Request from: $clientIP")

		if (!isAllowedIP(clientIP)) {
			httpResponse.status = HttpServletResponse.SC_FORBIDDEN
			return
		}

		chain?.doFilter(request, response)
	}

	private fun getClientIP(request: HttpServletRequest): String? {
		var clientIP = request.getHeader("X-Forwarded-For")
		if (clientIP == null || clientIP.isEmpty() || "unknown".equals(clientIP, ignoreCase = true)) {
			clientIP = request.getHeader("HTTP_CLIENT_IP")
		} else {
			if (clientIP.isEmpty() || "unknown".equals(clientIP, ignoreCase = true)) {
				clientIP = request.getHeader("Proxy-Client-IP")
			}
			if (clientIP == null || clientIP.isEmpty() || "unknown".equals(clientIP, ignoreCase = true)) {
				clientIP = request.getHeader("HTTP_X_FORWARDED_FOR")
			}
			if (clientIP == null || clientIP.isEmpty() || "unknown".equals(clientIP, ignoreCase = true)) {
				clientIP = request.remoteAddr
			}
		}
		return clientIP
	}

	private fun isAllowedIP(clientIP: String): Boolean {
		return allowedIPs.contains(clientIP)
	}
}
