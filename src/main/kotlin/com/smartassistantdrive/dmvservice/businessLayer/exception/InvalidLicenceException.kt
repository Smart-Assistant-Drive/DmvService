package com.smartassistantdrive.dmvservice.businessLayer.exception

/**
 *
 */
class InvalidLicenceException : Exception() {
	override val message: String
		get() = "Invalid licence information"
}
