package com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.controllers

import com.smartassistantdrive.dmvservice.businessLayer.adapter.VehicleRequestModel
import com.smartassistantdrive.dmvservice.businessLayer.adapter.VehicleUpdateModel
import com.smartassistantdrive.dmvservice.businessLayer.boundaries.VehicleInputBoundary
import com.smartassistantdrive.dmvservice.businessLayer.exception.InvalidLicenceException
import com.smartassistantdrive.dmvservice.businessLayer.exception.InvalidVehicleException
import com.smartassistantdrive.dmvservice.businessLayer.exception.VehicleExistsException
import com.smartassistantdrive.dmvservice.businessLayer.exception.VehicleNotFoundException
import com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.controllers.dto.VehicleResponseDto
import com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.controllers.dto.toDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import java.time.LocalDate
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class VehicleController(val vehicleInput: VehicleInputBoundary) {

	@PostMapping("/addCar")
	@Operation(
		summary = "Add vehicle",
		description = "Add vehicle",
		requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
			content = [
				Content(
					mediaType = "application/json",
					schema = Schema(implementation = VehicleRequestModel::class)
				)
			],
			required = true
		),
		responses = [
			ApiResponse(
				responseCode = "200",
				description = "Vehicle added successfully",
				content = [
					Content(
						mediaType = "application/json",
						schema = Schema(implementation = VehicleResponseDto::class)
					)
				]
			),
			ApiResponse(
				responseCode = "400",
				description = "Invalid vehicle",
				content = [Content()]
			),
			ApiResponse(
				responseCode = "409",
				description = "Valid vehicle already exists",
				content = [Content()]
			),
			ApiResponse(
				responseCode = "500",
				description = "Internal server error",
				content = [Content()]
			)
		]
	)
	fun addCar(@RequestBody vehicleRequestModel: VehicleRequestModel): HttpEntity<VehicleResponseDto> {
		val links = WebMvcLinkBuilder.linkTo(
			WebMvcLinkBuilder.methodOn(VehicleController::class.java).addCar(vehicleRequestModel)
		).withSelfRel()

		val registrationDate = LocalDate.parse(vehicleRequestModel.registrationDate)
		val result = vehicleInput.addCar(
			vehicleRequestModel.vin,
			vehicleRequestModel.plate,
			vehicleRequestModel.model,
			vehicleRequestModel.cv,
			vehicleRequestModel.cc,
			registrationDate
		)

		return if (result.isSuccess) {
			ResponseEntity(result.getOrNull()!!.toDto(links), HttpStatus.CREATED)
		} else {
			when (result.exceptionOrNull()) {
				is InvalidVehicleException -> ResponseEntity.badRequest().build()
				is VehicleExistsException -> ResponseEntity.status(HttpStatus.CONFLICT).build()
				else -> ResponseEntity.internalServerError().build()
			}
		}
	}

	@GetMapping("/carByVin")
	@Operation(
		summary = "Get vehicle",
		description = "Get vehicle with VIN code",
		parameters = [
			Parameter(
				name = "vin",
				description = "Vehicle vin code to be retrieved"
			)
		],
		responses = [
			ApiResponse(
				responseCode = "200",
				description = "Vehicle retrieved successfully",
				content = [
					Content(
						mediaType = "application/json",
						schema = Schema(implementation = VehicleResponseDto::class)
					)
				]
			),
			ApiResponse(
				responseCode = "404",
				description = "Licence not found",
				content = [Content()]
			),
			ApiResponse(
				responseCode = "500",
				description = "Internal server error",
				content = [Content()]
			)
		]
	)
	fun getCarByVin(@RequestParam(value = "vin", defaultValue = "") vin: String?): HttpEntity<VehicleResponseDto> {
		val links = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VehicleController::class.java).getCarByVin(vin))
			.withSelfRel()

		val result = vehicleInput.getCarByVin(vin!!)

		return if (result.isSuccess) {
			ResponseEntity(result.getOrNull()!!.toDto(links), HttpStatus.CREATED)
		} else {
			when (result.exceptionOrNull()) {
				is VehicleNotFoundException -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()
				else -> ResponseEntity.internalServerError().build()
			}
		}
	}

	@GetMapping("/carByPlate")
	@Operation(
		summary = "Get vehicle",
		description = "Get vehicle with plate code",
		parameters = [
			Parameter(
				name = "vin",
				description = "Vehicle plate code to be retrieved"
			)
		],
		responses = [
			ApiResponse(
				responseCode = "200",
				description = "Vehicle retrieved successfully",
				content = [
					Content(
						mediaType = "application/json",
						schema = Schema(implementation = VehicleResponseDto::class)
					)
				]
			),
			ApiResponse(
				responseCode = "404",
				description = "Licence not found",
				content = [Content()]
			),
			ApiResponse(
				responseCode = "500",
				description = "Internal server error",
				content = [Content()]
			)
		]
	)
	fun getCarByPlate(
		@RequestParam(
			value = "plate",
			defaultValue = ""
		) plate: String?,
	): HttpEntity<VehicleResponseDto> {
		val links =
			WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VehicleController::class.java).getCarByPlate(plate))
				.withSelfRel()

		val result = vehicleInput.getCarByPlate(plate!!)

		return if (result.isSuccess) {
			ResponseEntity(result.getOrNull()!!.toDto(links), HttpStatus.CREATED)
		} else {
			when (result.exceptionOrNull()) {
				is VehicleNotFoundException -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()
				else -> ResponseEntity.internalServerError().build()
			}
		}
	}

	@PutMapping("/cars/{vin}")
	@Operation(
		summary = "Update vehicle",
		description = "Update vehicle",
		parameters = [
			Parameter(
				name = "vin",
				description = "Vehicle plate code to be updated",
				`in` = ParameterIn.PATH
			)
		],
		requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
			content = [
				Content(
					mediaType = "application/json",
					schema = Schema(implementation = VehicleUpdateModel::class)
				)
			],
			required = true
		),
		responses = [
			ApiResponse(
				responseCode = "200",
				description = "Vehicle updated successfully",
				content = [
					Content(
						mediaType = "application/json",
						schema = Schema(implementation = VehicleResponseDto::class)
					)
				]
			),
			ApiResponse(
				responseCode = "400",
				description = "Invalid vehicle information",
				content = [Content()]
			),
			ApiResponse(
				responseCode = "404",
				description = "Vehicle not found",
				content = [Content()]
			),
			ApiResponse(
				responseCode = "500",
				description = "Internal server error",
				content = [Content()]
			)
		]
	)
	fun updateCar(
		@RequestBody car: VehicleUpdateModel,
		@PathVariable vin: String,
	): HttpEntity<VehicleResponseDto> {
		val links =
			WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VehicleController::class.java).updateCar(car, vin))
				.withSelfRel()

		val registrationDate = LocalDate.parse(car.newRegistrationDate)

		val result = vehicleInput.updateCar(vin, car.newPlate, registrationDate)

		return if (result.isSuccess) {
			ResponseEntity(result.getOrNull()!!.toDto(links), HttpStatus.CREATED)
		} else {
			when (result.exceptionOrNull()) {
				is InvalidLicenceException -> ResponseEntity.badRequest().build()
				is VehicleNotFoundException -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()
				else -> ResponseEntity.internalServerError().build()
			}
		}
	}
}
