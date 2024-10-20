package com.smartassistantdrive.dmvservice.businessLayer.exception

class VehicleNotFoundException : Exception() {
	override val message: String
		get() = "Vehicle not found"
}
