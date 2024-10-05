package com.smartassistantdrive.dmvservice.businessLayer

import com.smartassistantdrive.dmvservice.businessLayer.boundaries.VehicleDataSourceGateway
import com.smartassistantdrive.dmvservice.businessLayer.boundaries.InputBoundary
import com.smartassistantdrive.dmvservice.businessLayer.boundaries.LicenceDataSourceGateway
import com.smartassistantdrive.dmvservice.domainLayer.Licence
import com.smartassistantdrive.dmvservice.domainLayer.Vehicle
import java.time.LocalDateTime
import kotlin.math.exp

class UseCase(private var vehicleDataSourceGateway: VehicleDataSourceGateway, private var licenceDataSourceGateway: LicenceDataSourceGateway): InputBoundary {
    override fun addCar(vin: String, plate: String, model: String, cv: Int, cc: Int, registrationDate: LocalDateTime) {
        val vehicle: Vehicle = Vehicle.create(vin, plate, model, cv, cc, registrationDate)
        vehicleDataSourceGateway.saveNewVehicle(vehicle)
    }

    override fun updateCar(vin: String, newPlate: String, newRegistrationDate: LocalDateTime) {
        vehicleDataSourceGateway.updateVehicle(vin, newPlate, newRegistrationDate)
    }

    override fun getCarByPlate(plate: String): Vehicle {
        val vehicle = vehicleDataSourceGateway.existsByPlate(plate)
        return vehicle
    }

    override fun getCarByVin(vin: String): Vehicle {
        val vehicle = vehicleDataSourceGateway.existsByVin(vin)
        return vehicle
    }

    override fun addLicence(
        licenceId: String,
        name: String,
        surname: String,
        birthDate: LocalDateTime,
        licenceCountry: String,
        expireDate: LocalDateTime,
        releaseDate: LocalDateTime,
        residence: String
    ) {
        val licence = Licence.create(licenceId, name, surname, birthDate, licenceCountry, expireDate, releaseDate, residence)
        licenceDataSourceGateway.save(licence)
    }

    override fun updateLicence(
        licenceId: String,
        newExpireDate: LocalDateTime,
        newReleaseDate: LocalDateTime,
        newResidence: String
    ): Licence {
        licenceDataSourceGateway.update(licenceId, newExpireDate, newReleaseDate, newResidence)
        return getLicence(licenceId)
    }

    override fun getLicence(licenceId: String): Licence {
        return licenceDataSourceGateway.getLicence(licenceId)
    }

}