package com.smartassistantdrive.dmvservice.businessLayer.exception

/**
 *
 */
class VehicleExistsException : Exception() {
	override val message: String
		get() = "A valid vehicle with this information already exists"
}
