package com.smartassistantdrive.dmvservice

import com.smartassistantdrive.dmvservice.businessLayer.LicenceUseCase
import com.smartassistantdrive.dmvservice.businessLayer.VehicleUseCase
import com.smartassistantdrive.dmvservice.businessLayer.boundaries.LicenceInputBoundary
import com.smartassistantdrive.dmvservice.businessLayer.boundaries.VehicleInputBoundary
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
	fun licenceInput(): LicenceInputBoundary {
		val licenceRepository: LicenceRepository = licenceRepository!!

		val licenceRegisterDataSourceGateway = LicenceDataSourceGatewayImpl(licenceRepository)
		val licenceUseCase = LicenceUseCase(licenceRegisterDataSourceGateway)
		return licenceUseCase
	}

	@Bean
	fun vehicleInput(): VehicleInputBoundary {
		val vehicleRepository: VehicleRepository = vehicleRepository!!
		val vehicleRegisterDataSourceGateway = VehicleDataSourceGatewayImpl(vehicleRepository)
		val vehicleUseCase = VehicleUseCase(vehicleRegisterDataSourceGateway)
		return vehicleUseCase
	}
}
