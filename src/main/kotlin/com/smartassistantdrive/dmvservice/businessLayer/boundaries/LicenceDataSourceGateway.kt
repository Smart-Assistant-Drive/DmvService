package com.smartassistantdrive.dmvservice.businessLayer.boundaries

import com.smartassistantdrive.dmvservice.domainLayer.Licence
import java.time.LocalDate

interface LicenceDataSourceGateway {

	fun save(licence: Licence): Result<String>

	fun update(
		licenceId: String,
		newExpireDate: LocalDate,
		newReleaseDate: LocalDate,
		newResidence: String,
	): Result<String>

	fun getLicence(licenceId: String): Result<Licence>

	fun getNewId(): Long
}
