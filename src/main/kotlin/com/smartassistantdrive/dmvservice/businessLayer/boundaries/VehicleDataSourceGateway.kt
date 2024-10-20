package com.smartassistantdrive.dmvservice.businessLayer.boundaries

import com.smartassistantdrive.dmvservice.domainLayer.Vehicle
import java.time.LocalDate

interface VehicleDataSourceGateway {
	fun saveNewVehicle(vehicle: Vehicle)

	fun updateVehicle(vin: String, newPlate: String, newRegistrationDate: LocalDate): Vehicle

	fun existsByPlate(plate: String): Vehicle

	fun existsByVin(vin: String): Vehicle
}
