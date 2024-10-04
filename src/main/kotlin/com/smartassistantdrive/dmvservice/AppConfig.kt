package com.smartassistantdrive.dmvservice

import com.smartassistantdrive.dmvservice.businessLayer.UseCase
import com.smartassistantdrive.dmvservice.businessLayer.boundaries.InputBoundary
import com.smartassistantdrive.dmvservice.greeting.repository.CustomerRepository
import com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.persistence.VehicleDataSourceGatewayImpl
import com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.persistence.VehicleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {

    @Autowired
    private val repository: VehicleRepository? = null

    @Bean
    fun userInput(): InputBoundary {
        val vehicleRepository: VehicleRepository = repository!!
        val userRegisterDataSourceGateway = VehicleDataSourceGatewayImpl(vehicleRepository)
        val userRegisterUseCase = UseCase(userRegisterDataSourceGateway)
        return userRegisterUseCase
    }

}