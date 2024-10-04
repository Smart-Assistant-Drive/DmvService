package com.smartassistantdrive.dmvservice.businessLayer.boundaries

import com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.persistence.entity.VehicleDataSourceModel

interface VehicleDataSourceGateway {

    fun saveNewVehicle(vehicle: VehicleDataSourceModel)

    fun existsByPlate(plate: String): VehicleDataSourceModel

}