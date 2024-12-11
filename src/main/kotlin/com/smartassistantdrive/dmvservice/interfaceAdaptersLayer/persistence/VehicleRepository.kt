package com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.persistence

import com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.persistence.entity.VehicleDataSourceModel
import org.springframework.data.mongodb.repository.MongoRepository

/**
 *
 */
interface VehicleRepository : MongoRepository<VehicleDataSourceModel?, String?> {

	/**
	 *
	 */
	fun findByPlate(plate: String?): VehicleDataSourceModel?

	/**
	 *
	 */
	fun findByVin(vin: String?): VehicleDataSourceModel?
}
