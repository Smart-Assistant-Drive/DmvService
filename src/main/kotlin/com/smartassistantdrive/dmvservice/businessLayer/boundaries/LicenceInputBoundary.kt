package com.smartassistantdrive.dmvservice.businessLayer.boundaries

import com.smartassistantdrive.dmvservice.businessLayer.adapter.LicenceResponseModel
import java.time.LocalDate

interface LicenceInputBoundary {
	// licence management

	fun addLicence(
		name: String,
		surname: String,
		birthDate: String,
		licenceCountry: String,
		residence: String,
	): Result<LicenceResponseModel>

	fun updateLicence(
		licenceId: String,
		newExpireDate: LocalDate,
		newReleaseDate: LocalDate,
		newResidence: String,
	): Result<LicenceResponseModel>

	fun getLicence(licenceId: String): Result<LicenceResponseModel>
}
