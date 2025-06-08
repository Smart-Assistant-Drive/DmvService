package com.smartassistantdrive.dmvservice.businessLayer

import com.smartassistantdrive.dmvservice.businessLayer.adapter.VehicleResponseModel
import com.smartassistantdrive.dmvservice.businessLayer.boundaries.VehicleDataSourceGateway
import com.smartassistantdrive.dmvservice.businessLayer.boundaries.VehicleInputBoundary
import com.smartassistantdrive.dmvservice.businessLayer.exception.InvalidVehicleException
import com.smartassistantdrive.dmvservice.businessLayer.exception.VehicleExistsException
import com.smartassistantdrive.dmvservice.businessLayer.exception.VehicleNotFoundException
import com.smartassistantdrive.dmvservice.domainLayer.Vehicle
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 *
 */
@Suppress("TooGenericExceptionCaught")
class VehicleUseCase(private var vehicleDataSourceGateway: VehicleDataSourceGateway) : VehicleInputBoundary {

	private val logger: Logger = LoggerFactory.getLogger(VehicleUseCase::class.java)

	override fun addCar(
		vin: String,
		plate: String,
		model: String,
		cv: Int,
		cc: Int,
		registrationDate: String,
	): Result<VehicleResponseModel> {
		val registrationLocalDate: LocalDate
		try {
			val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
			registrationLocalDate = LocalDate.parse(registrationDate, formatter)
			if (vin.isEmpty() || plate.isEmpty() || model.isEmpty()) {
				throw InvalidVehicleException()
			}
			if (cv == 0 || cc == 0 || registrationLocalDate > LocalDate.now()) {
				throw InvalidVehicleException()
			}

			if (exists(vin, plate)) throw VehicleExistsException()

			val vehicle: Vehicle = Vehicle.create(
				vin,
				plate,
				model,
				cv,
				cc,
				registrationLocalDate
			)
			vehicleDataSourceGateway.saveNewVehicle(vehicle)
			return getCarByVin(vin)
		} catch (e: Exception) {
			logger.error(e.toString())
			return Result.failure(e)
		}
	}

	override fun updateCar(
		vin: String,
		newPlate: String,
		newRegistrationDate: String,
	): Result<VehicleResponseModel> {
		val registrationDate: LocalDate
		try {
			val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
			registrationDate = LocalDate.parse(newRegistrationDate, formatter)
			if (vin.isEmpty() || newPlate.isEmpty() || registrationDate > LocalDate.now()) {
				throw InvalidVehicleException()
			}

			if (!exists(vin)) throw VehicleNotFoundException()

			val updatedVehicle = vehicleDataSourceGateway.updateVehicle(vin, newPlate, registrationDate)
			return Result.success(
				VehicleResponseModel(
					updatedVehicle
				)
			)
		} catch (e: Exception) {
			logger.error(e.toString())
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
			logger.error(e.toString())
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
			logger.error(e.toString())
			return Result.failure(VehicleNotFoundException())
		}
	}

	private fun exists(vin: String, plate: String = ""): Boolean {
		val resultByVin = getCarByVin(vin)
		val resultByPlate = getCarByPlate(plate)
		if (resultByVin.isSuccess || resultByPlate.isSuccess) return true
		return false
	}
}
