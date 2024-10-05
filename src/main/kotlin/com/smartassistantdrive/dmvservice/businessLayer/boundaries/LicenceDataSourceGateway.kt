package com.smartassistantdrive.dmvservice.businessLayer.boundaries

import com.smartassistantdrive.dmvservice.domainLayer.Licence
import java.time.LocalDateTime

interface LicenceDataSourceGateway {

    fun save(licence: Licence): Boolean

    fun update(licenceId: String, newExpireDate: LocalDateTime, newReleaseDate: LocalDateTime, newResidence: String): Boolean

    fun getLicence(licenceId: String): Licence

}