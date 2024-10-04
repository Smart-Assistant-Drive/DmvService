package com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.persistence

import com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.persistence.entity.VehicleDataSourceModel
import org.springframework.data.mongodb.repository.MongoRepository

interface VehicleRepository: MongoRepository<VehicleDataSourceModel?, String?> {

    // query
    fun findByPlate(plate: String?): VehicleDataSourceModel?

}