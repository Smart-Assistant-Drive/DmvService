package com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.persistence

import com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.persistence.entity.LicenceDataSourceModel
import org.springframework.data.mongodb.repository.MongoRepository

interface LicenceRepository : MongoRepository<LicenceDataSourceModel?, String?> {
	fun findByLicenceId(licenceId: String?): LicenceDataSourceModel?
}
