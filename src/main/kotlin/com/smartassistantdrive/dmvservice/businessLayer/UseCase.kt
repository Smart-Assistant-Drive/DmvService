package com.smartassistantdrive.dmvservice.businessLayer

import com.smartassistantdrive.dmvservice.businessLayer.boundaries.VehicleDataSourceGateway
import com.smartassistantdrive.dmvservice.businessLayer.boundaries.InputBoundary
import com.smartassistantdrive.dmvservice.domainLayer.Vehicle
import com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.persistence.entity.VehicleDataSourceModel
import java.time.Instant
import java.time.LocalDateTime
import java.util.Date

class UseCase(private var userDataSourceGateway: VehicleDataSourceGateway): InputBoundary {

    // test fun
    override fun addCar() {
        val vehicle: Vehicle = Vehicle.create("1234", "RN1234OZ", "Bmw", 300, 3000, LocalDateTime.now())
        val vehicleDataSourceModel = VehicleDataSourceModel(vehicle)
        userDataSourceGateway.saveNewVehicle(vehicleDataSourceModel)
    }

    override fun getCarByPlate(plate: String): Vehicle {
        val vehicleDataSourceModel = userDataSourceGateway.existsByPlate(plate)
        return vehicleDataSourceModel.getVehicleModel()
    }

}