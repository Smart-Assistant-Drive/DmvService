package com.smartassistantdrive.dmvservice

import com.smartassistantdrive.dmvservice.businessLayer.UseCase
import com.smartassistantdrive.dmvservice.businessLayer.boundaries.InputBoundary
import com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.persistence.LicenceDataSourceGatewayImpl
import com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.persistence.LicenceRepository
import com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.persistence.VehicleDataSourceGatewayImpl
import com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.persistence.VehicleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {

    @Autowired
    private val vehicleRepository: VehicleRepository? = null

    @Autowired
    private val licenceRepository: LicenceRepository? = null

    @Bean
    fun userInput(): InputBoundary {
        val vehicleRepository: VehicleRepository = vehicleRepository!!
        val licenceRepository: LicenceRepository = licenceRepository!!
        val vehicleRegisterDataSourceGateway = VehicleDataSourceGatewayImpl(vehicleRepository)
        val licenceRegisterDataSourceGateway = LicenceDataSourceGatewayImpl(licenceRepository)
        val userRegisterUseCase = UseCase(vehicleRegisterDataSourceGateway, licenceRegisterDataSourceGateway)
        return userRegisterUseCase
    }

}