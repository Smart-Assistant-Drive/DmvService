package com.smartassistantdrive.dmvservice.businessLayer

import com.smartassistantdrive.dmvservice.businessLayer.adapter.VehicleResponseModel
import com.smartassistantdrive.dmvservice.businessLayer.boundaries.VehicleDataSourceGateway
import com.smartassistantdrive.dmvservice.businessLayer.boundaries.VehicleInputBoundary
import com.smartassistantdrive.dmvservice.businessLayer.exception.InvalidVehicleException
import com.smartassistantdrive.dmvservice.businessLayer.exception.VehicleExistsException
import com.smartassistantdrive.dmvservice.businessLayer.exception.VehicleNotFoundException
import com.smartassistantdrive.dmvservice.domainLayer.Vehicle
import java.time.LocalDate

class VehicleUseCase(private var vehicleDataSourceGateway: VehicleDataSourceGateway) : VehicleInputBoundary {

	override fun addCar(
		vin: String,
		plate: String,
		model: String,
		cv: Int,
		cc: Int,
		registrationDate: LocalDate,
	): Result<VehicleResponseModel> {
		if (vin.isEmpty() || plate.isEmpty() || model.isEmpty() || cv == 0 || cc == 0 || registrationDate > LocalDate.now()) {
			return Result.failure(
				InvalidVehicleException()
			)
		}

		if (exists(vin, plate)) return Result.failure(VehicleExistsException())

		try {
			val vehicle: Vehicle = Vehicle.create(
				vin,
				plate,
				model,
				cv,
				cc,
				registrationDate
			)
			vehicleDataSourceGateway.saveNewVehicle(vehicle)
			return getCarByVin(vin)
		} catch (e: Exception) {
			return Result.failure(e)
		}
	}

	override fun updateCar(
		vin: String,
		newPlate: String,
		newRegistrationDate: LocalDate,
	): Result<VehicleResponseModel> {
		if (vin.isEmpty() || newPlate.isEmpty() || newRegistrationDate > LocalDate.now()) {
			return Result.failure(
				InvalidVehicleException()
			)
		}

		if (!exists(vin)) return Result.failure(VehicleNotFoundException())

		try {
			val updatedVehicle = vehicleDataSourceGateway.updateVehicle(vin, newPlate, newRegistrationDate)
			return Result.success(
				VehicleResponseModel(
					updatedVehicle
				)
			)
		} catch (e: Exception) {
			return Result.failure(e)
		}
	}

	override fun getCarByPlate(plate: String): Result<VehicleResponseModel> {
		try {
			val vehicle = vehicleDataSourceGateway.existsByPlate(plate)
			return Result.success(
				VehicleResponseModel(
					vehicle
				)
			)
		} catch (e: NullPointerException) {
			return Result.failure(VehicleNotFoundException())
		}
	}

	override fun getCarByVin(vin: String): Result<VehicleResponseModel> {
		try {
			val vehicle = vehicleDataSourceGateway.existsByVin(vin)
			return Result.success(
				VehicleResponseModel(
					vehicle
				)
			)
		} catch (e: NullPointerException) {
			return Result.failure(VehicleNotFoundException())
		}
	}

	fun exists(vin: String, plate: String = ""): Boolean {
		val resultByVin = getCarByVin(vin)
		val resultByPlate = getCarByPlate(plate)
		if (resultByVin.isSuccess || resultByPlate.isSuccess) return true
		return false
	}
}
