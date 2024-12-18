package com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.persistence

import com.smartassistantdrive.dmvservice.businessLayer.boundaries.VehicleDataSourceGateway
import com.smartassistantdrive.dmvservice.domainLayer.Vehicle
import com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.persistence.entity.VehicleDataSourceModel
import java.time.LocalDate

/**
 *
 */
class VehicleDataSourceGatewayImpl(private val vehicleRepository: VehicleRepository) : VehicleDataSourceGateway {

	override fun saveNewVehicle(vehicle: Vehicle) {
		val vehicleDataSourceModel = VehicleDataSourceModel(vehicle)
		vehicleRepository.save(vehicleDataSourceModel)
	}

	override fun updateVehicle(vin: String, newPlate: String, newRegistrationDate: LocalDate): Vehicle {
		val vehicleDataSourceModel = vehicleRepository.findByVin(vin)!!
		vehicleDataSourceModel.plate = newPlate
		vehicleDataSourceModel.registrationDate = newRegistrationDate
		saveNewVehicle(vehicleDataSourceModel.getVehicleModel())
		return Vehicle.create(
			vehicleDataSourceModel.vin!!,
			vehicleDataSourceModel.plate!!,
			vehicleDataSourceModel.model!!,
			vehicleDataSourceModel.cv!!,
			vehicleDataSourceModel.cc!!,
			vehicleDataSourceModel.registrationDate!!
		)
	}

	override fun existsByPlate(plate: String): Vehicle {
		val vehicleDataSourceModel = vehicleRepository.findByPlate(plate)
		return vehicleDataSourceModel!!.getVehicleModel()
	}

	override fun existsByVin(vin: String): Vehicle {
		val vehicleDataSourceModel = vehicleRepository.findByVin(vin)
		return vehicleDataSourceModel!!.getVehicleModel()
	}
}
