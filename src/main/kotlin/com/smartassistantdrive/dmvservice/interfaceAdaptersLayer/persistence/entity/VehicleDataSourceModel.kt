package com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.persistence.entity

import com.smartassistantdrive.dmvservice.domainLayer.Vehicle
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import java.time.LocalDateTime
import java.util.*

class VehicleDataSourceModel {

    @Id
    var vin: String? = null

    @Indexed(unique = true)
    var plate: String? = null

    var model: String? = null

    var cv: Int? = null

    var cc: Int? = null

    var registrationDate: LocalDateTime? = null

    constructor()

    constructor(vehicle: Vehicle) {
        this.vin = vehicle.vin
        this.plate = vehicle.plate
        this.cv = vehicle.cv
        this.model = vehicle.model
        this.cc = vehicle.cc
        this.registrationDate = vehicle.registrationDate
    }

    fun getVehicleModel(): Vehicle {
        return Vehicle.create(vin!!, plate!!, model!!, cv!!, cc!!, registrationDate!!)
    }

    override fun toString(): String {
        return "VehicleDataSource(vin=$vin, plate=$plate, model=$model, cv=$cv, cc=$cc, registrationDate=$registrationDate)"
    }


}