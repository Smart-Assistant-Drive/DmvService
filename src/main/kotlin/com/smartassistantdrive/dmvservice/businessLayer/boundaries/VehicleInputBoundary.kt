package com.smartassistantdrive.dmvservice.businessLayer.boundaries

import com.smartassistantdrive.dmvservice.businessLayer.adapter.VehicleResponseModel
import java.time.LocalDate

interface VehicleInputBoundary {
	// car management
	fun addCar(
		vin: String,
		plate: String,
		model: String,
		cv: Int,
		cc: Int,
		registrationDate: LocalDate,
	): Result<VehicleResponseModel>

	fun updateCar(
		vin: String,
		newPlate: String,
		newRegistrationDate: LocalDate,
	): Result<VehicleResponseModel>

	fun getCarByPlate(plate: String): Result<VehicleResponseModel>

	fun getCarByVin(vin: String): Result<VehicleResponseModel>
}
