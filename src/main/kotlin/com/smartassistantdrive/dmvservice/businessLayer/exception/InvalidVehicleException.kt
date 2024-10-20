package com.smartassistantdrive.dmvservice.businessLayer.exception

class InvalidVehicleException : Exception() {
	override val message: String
		get() = "Invalid vehicle information"
}
