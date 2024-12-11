package com.smartassistantdrive.dmvservice.businessLayer.boundaries

import com.smartassistantdrive.dmvservice.businessLayer.adapter.LicenceResponseModel

/**
 *
 */
interface LicenceInputBoundary {
	// licence management

	/**
	 *
	 */
	fun addLicence(
		name: String,
		surname: String,
		birthDate: String,
		licenceCountry: String,
		residence: String,
	): Result<LicenceResponseModel>

	/**
	 *
	 */
	fun updateLicence(
		licenceId: String,
		newExpireDate: String,
		newReleaseDate: String,
		newResidence: String,
	): Result<LicenceResponseModel>

	/**
	 *
	 */
	fun getLicence(licenceId: String): Result<LicenceResponseModel>
}
