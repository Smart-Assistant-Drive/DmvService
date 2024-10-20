package com.smartassistantdrive.dmvservice.businessLayer.exception

class LicenceExistsException : Exception() {
	override val message: String
		get() = "A valid licence already exists that is not expired yet"
}
