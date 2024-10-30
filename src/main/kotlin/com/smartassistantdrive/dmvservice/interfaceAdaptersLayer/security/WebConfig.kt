package com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.security

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class WebConfig {
	@Bean
	fun firewallFilter(): FilterRegistrationBean<FirewallFilter> {
		val registrationBean = FilterRegistrationBean<FirewallFilter>()
		registrationBean.filter = FirewallFilter()
		registrationBean.addUrlPatterns("/*")
		return registrationBean
	}
}
