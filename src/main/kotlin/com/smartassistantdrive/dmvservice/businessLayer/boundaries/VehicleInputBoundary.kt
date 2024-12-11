package com.smartassistantdrive.dmvservice.businessLayer.boundaries

import com.smartassistantdrive.dmvservice.businessLayer.adapter.VehicleResponseModel

/**
 * Car management.addRoadd
 */
interface VehicleInputBoundary {

	/**
	 *
	 */
	fun addCar(
		vin: String,
		plate: String,
		model: String,
		cv: Int,
		cc: Int,
		registrationDate: String,
	): Result<VehicleResponseModel>

	/**
	 *
	 */
	fun updateCar(
		vin: String,
		newPlate: String,
		newRegistrationDate: String,
	): Result<VehicleResponseModel>

	/**
	 *
	 */
	fun getCarByPlate(plate: String): Result<VehicleResponseModel>

	/**
	 *
	 */
	fun getCarByVin(vin: String): Result<VehicleResponseModel>
}
