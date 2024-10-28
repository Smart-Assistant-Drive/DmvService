package com.smartassistantdrive.dmvservice.businessLayer

import com.smartassistantdrive.dmvservice.businessLayer.adapter.LicenceResponseModel
import com.smartassistantdrive.dmvservice.businessLayer.boundaries.LicenceDataSourceGateway
import com.smartassistantdrive.dmvservice.businessLayer.boundaries.LicenceInputBoundary
import com.smartassistantdrive.dmvservice.businessLayer.exception.InvalidLicenceException
import com.smartassistantdrive.dmvservice.businessLayer.exception.LicenceNotFoundException
import com.smartassistantdrive.dmvservice.domainLayer.Licence
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LicenceUseCase(
	private var licenceDataSourceGateway: LicenceDataSourceGateway,
) : LicenceInputBoundary {

	val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

	override fun addLicence(
		name: String,
		surname: String,
		birthDate: String,
		licenceCountry: String,
		residence: String,
	): Result<LicenceResponseModel> {
		val releaseDate = LocalDate.now()
		val expireDate = releaseDate.plusYears(10)

		if (residence.isEmpty() || name.isEmpty() || surname.isEmpty() || licenceCountry.isEmpty()) {
			return Result.failure(InvalidLicenceException())
		}

		try {
			val birthDateConverted = LocalDate.parse(birthDate, formatter)

			val licence = Licence.create(
				name,
				surname,
				birthDateConverted,
				licenceCountry,
				expireDate,
				releaseDate,
				residence,
				licenceDataSourceGateway.getNewId().toString()
			)
			val result = licenceDataSourceGateway.save(licence)
			return Result.success(
				LicenceResponseModel(
					result.getOrNull()!!,
					name,
					surname,
					birthDateConverted,
					licenceCountry,
					expireDate,
					releaseDate,
					residence
				)
			)
		} catch (e: Exception) {
			return Result.failure(e)
		}
	}

	override fun updateLicence(
		licenceId: String,
		newExpireDate: String,
		newReleaseDate: String,
		newResidence: String,
	): Result<LicenceResponseModel> {
		licenceDataSourceGateway.update(licenceId, LocalDate.parse(newExpireDate, formatter), LocalDate.parse(newReleaseDate, formatter), newResidence)
		return getLicence(licenceId)
	}

	override fun getLicence(licenceId: String): Result<LicenceResponseModel> {
		try {
			val licence = licenceDataSourceGateway.getLicence(licenceId).getOrNull()!!

			return Result.success(
				LicenceResponseModel(
					licence.licenceId,
					licence.name,
					licence.surname,
					licence.birthDate,
					licence.licenceCountry,
					licence.expireDate,
					licence.releaseDate,
					licence.residence
				)
			)
		} catch (e: NullPointerException) {
			return Result.failure(LicenceNotFoundException())
		}
	}
}
