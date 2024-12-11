package com.smartassistantdrive.dmvservice.businessLayer.exception

/**
 *
 */
class LicenceNotFoundException : Exception() {
	override val message: String
		get() = "Licence not found"
}
