package com.smartassistantdrive.dmvservice.businessLayer.boundaries

import com.smartassistantdrive.dmvservice.domainLayer.Licence
import com.smartassistantdrive.dmvservice.domainLayer.Vehicle
import java.time.LocalDateTime

interface InputBoundary {

    // car management
    fun addCar(vin: String, plate: String, model: String, cv: Int, cc: Int, registrationDate: LocalDateTime)

    fun updateCar(vin: String, newPlate: String, newRegistrationDate: LocalDateTime)

    fun getCarByPlate(plate: String): Vehicle

    fun getCarByVin(vin: String): Vehicle

    // licence management

    fun addLicence(licenceId: String,
                   name: String,
                   surname: String,
                   birthDate: LocalDateTime,
                   licenceCountry: String,
                   expireDate: LocalDateTime,
                   releaseDate: LocalDateTime,
                   residence: String)

    fun updateLicence(licenceId: String,
                      newExpireDate: LocalDateTime,
                      newReleaseDate: LocalDateTime,
                      newResidence: String): Licence

    fun getLicence(licenceId: String): Licence

}