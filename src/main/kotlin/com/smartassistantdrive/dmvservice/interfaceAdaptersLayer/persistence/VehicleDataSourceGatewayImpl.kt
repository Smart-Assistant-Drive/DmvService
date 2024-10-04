package com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.persistence

import com.smartassistantdrive.dmvservice.businessLayer.boundaries.VehicleDataSourceGateway
import com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.persistence.entity.VehicleDataSourceModel

class VehicleDataSourceGatewayImpl(vehicleRepository: VehicleRepository): VehicleDataSourceGateway {

    val vehicleRepository: VehicleRepository = vehicleRepository


    override fun saveNewVehicle(vehicle: VehicleDataSourceModel) {
        vehicleRepository.save(vehicle)
    }

    override fun existsByPlate(plate: String): VehicleDataSourceModel {
        val vehicleDataSourceModel = vehicleRepository.findByPlate(plate)
        return vehicleDataSourceModel!!
    }


}