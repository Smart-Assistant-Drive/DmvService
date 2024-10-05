package com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.persistence

import com.smartassistantdrive.dmvservice.businessLayer.boundaries.LicenceDataSourceGateway
import com.smartassistantdrive.dmvservice.domainLayer.Licence
import com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.persistence.entity.LicenceDataSourceModel
import java.time.LocalDateTime

class LicenceDataSourceGatewayImpl(private val licenceRepository: LicenceRepository): LicenceDataSourceGateway {
    override fun save(licence: Licence): Boolean {
        val result = licenceRepository.save(LicenceDataSourceModel(licence))
        return result.licenceId == licence.licenceId
    }

    override fun update(
        licenceId: String,
        newExpireDate: LocalDateTime,
        newReleaseDate: LocalDateTime,
        newResidence: String
    ): Boolean {
        val licence = licenceRepository.findByLicenceId(licenceId)!!
        licence.expireDate = newExpireDate
        licence.releaseDate = newReleaseDate
        licence.residence = newResidence
        return save(licence.getLicenceModel())
    }

    override fun getLicence(licenceId: String): Licence {
        return licenceRepository.findByLicenceId(licenceId)!!.getLicenceModel()
    }
}