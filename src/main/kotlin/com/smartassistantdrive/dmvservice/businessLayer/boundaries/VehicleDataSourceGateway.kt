package com.smartassistantdrive.dmvservice.businessLayer.boundaries

import com.smartassistantdrive.dmvservice.domainLayer.Vehicle
import com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.persistence.entity.VehicleDataSourceModel
import java.time.LocalDateTime

interface VehicleDataSourceGateway {

    fun saveNewVehicle(vehicle: Vehicle)

    fun updateVehicle(vin: String, newPlate: String, newRegistrationDate: LocalDateTime)

    fun existsByPlate(plate: String): Vehicle

    fun existsByVin(vin: String): Vehicle

}