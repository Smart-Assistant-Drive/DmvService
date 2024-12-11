package com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.persistence

import com.smartassistantdrive.dmvservice.businessLayer.boundaries.LicenceDataSourceGateway
import com.smartassistantdrive.dmvservice.domainLayer.Licence
import com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.persistence.entity.LicenceDataSourceModel
import java.time.LocalDate

/**
 *
 */
@Suppress("TooGenericExceptionCaught")
class LicenceDataSourceGatewayImpl(private val licenceRepository: LicenceRepository) : LicenceDataSourceGateway {
	override fun save(licence: Licence): Result<String> {
		val result = licenceRepository.save(LicenceDataSourceModel(licence))
		return Result.success(result.licenceId!!)
	}

	override fun update(
		licenceId: String,
		newExpireDate: LocalDate,
		newReleaseDate: LocalDate,
		newResidence: String,
	): Result<String> {
		val licence = licenceRepository.findByLicenceId(licenceId)!!
		licence.expireDate = newExpireDate
		licence.releaseDate = newReleaseDate
		licence.residence = newResidence
		return save(licence.getLicenceModel())
	}

	override fun getLicence(licenceId: String): Result<Licence> {
		try {
			val licence = licenceRepository.findByLicenceId(licenceId)!!.getLicenceModel()
			return Result.success(licence)
		} catch (e: Exception) {
			return Result.failure(e)
		}
	}

	override fun getNewId(): Long {
		return licenceRepository.count()
	}
}
