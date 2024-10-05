package com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.persistence.entity

import com.smartassistantdrive.dmvservice.domainLayer.Licence
import org.springframework.data.annotation.Id
import java.time.LocalDateTime

class LicenceDataSourceModel() {

    @Id
    var licenceId: String? = null

    var name: String? = null

    var surname: String? = null

    var birthDate: LocalDateTime? = null

    var licenceCountry: String? = null

    var expireDate: LocalDateTime? = null

    var releaseDate: LocalDateTime? = null

    var residence: String? = null

    constructor(licence: Licence) : this() {
        this.licenceId = licence.licenceId
        this.name = licence.name
        this.surname = licence.surname
        this.birthDate = licence.birthDate
        this.licenceCountry = licence.licenceCountry
        this.expireDate = licence.expireDate
        this.releaseDate = licence.releaseDate
        this.residence = licence.residence
    }

    fun getLicenceModel(): Licence {
        return Licence.create(licenceId!!, name!!, surname!!, birthDate!!, licenceCountry!!, expireDate!!, releaseDate!!, residence!!)
    }

    override fun toString(): String {
        return "LicenceDataSourceModel(licenceId=$licenceId, name=$name, surname=$surname, birthDate=$birthDate, licenceCountry=$licenceCountry, expireDate=$expireDate, releaseDate=$releaseDate, residence=$residence)"
    }


}