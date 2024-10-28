package com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.controllers

import com.smartassistantdrive.dmvservice.businessLayer.adapter.LicenceRequestModel
import com.smartassistantdrive.dmvservice.businessLayer.adapter.LicenceUpdateModel
import com.smartassistantdrive.dmvservice.businessLayer.boundaries.LicenceInputBoundary
import com.smartassistantdrive.dmvservice.businessLayer.exception.InvalidLicenceException
import com.smartassistantdrive.dmvservice.businessLayer.exception.LicenceExistsException
import com.smartassistantdrive.dmvservice.businessLayer.exception.LicenceNotFoundException
import com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.controllers.dto.LicenceResponseDto
import com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.controllers.dto.toDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
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
class LicenceController(licenceInput: LicenceInputBoundary) {

	val input = licenceInput

	companion object {
		private const val TEMPLATE = "Hello, %s!"
	}

	@GetMapping("/hello")
	fun hello(@RequestParam(value = "name", defaultValue = "World") name: String?): String {
		// Esempio dimostrativo CircuitBreaker
		// throw IllegalArgumentException()
		return "Hello World"
	}

	@PostMapping("/licences")
	@Operation(
		summary = "Create licence",
		description = "Create licence",
		requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
			content = [
				Content(
					mediaType = "application/json",
					schema = Schema(implementation = LicenceRequestModel::class)
				)
			],
			required = true
		),
		responses = [
			ApiResponse(
				responseCode = "201",
				description = "Created licence",
				content = [
					Content(
						mediaType = "application/json",
						schema = Schema(implementation = LicenceResponseDto::class)
					)
				]
			), ApiResponse(
				responseCode = "400",
				description = "Invalid licence",
				content = [Content()]
			), ApiResponse(
				responseCode = "409",
				description = "Valid licence already exists",
				content = [Content()]
			), ApiResponse(
				responseCode = "500",
				description = "Internal server error",
				content = [Content()]
			)
		]
	)
	fun newLicence(@RequestBody licence: LicenceRequestModel): HttpEntity<LicenceResponseDto> {
		val links =
			WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LicenceController::class.java).newLicence(licence))
				.withSelfRel()
		print("cavolol")
		val result = input.addLicence(
			licence.name,
			licence.surname,
			licence.birthDate,
			licence.licenceCountry,
			licence.residence
		)
		return if (result.isSuccess) {
			ResponseEntity(result.getOrNull()!!.toDto(links), HttpStatus.CREATED)
		} else {
			result.exceptionOrNull()!!.printStackTrace()
			when (result.exceptionOrNull()) {
				is InvalidLicenceException -> ResponseEntity.badRequest().build()
				is LicenceExistsException -> ResponseEntity.status(HttpStatus.CONFLICT).build()
				else -> ResponseEntity.internalServerError().build()
			}
		}
	}

	@GetMapping("/licence")
	@Operation(
		summary = "Get licence",
		description = "Get licence",
		parameters = [
			Parameter(
				name = "id",
				description = "Licence id to be retrieved"
			)
		],
		responses = [
			ApiResponse(
				responseCode = "200",
				description = "Licence retrieved successfully",
				content = [
					Content(
						mediaType = "application/json",
						schema = Schema(implementation = LicenceResponseDto::class)
					)
				]
			),
			ApiResponse(
				responseCode = "404",
				description = "Licence not found",
				content = [Content()]
			), ApiResponse(
				responseCode = "500",
				description = "Internal server error",
				content = [Content()]
			)
		]
	)
	fun getLicence(@RequestParam(value = "id", defaultValue = "") licenceId: String?): HttpEntity<LicenceResponseDto> {
		val links =
			WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LicenceController::class.java).getLicence(licenceId))
				.withSelfRel()

		if (licenceId == null) {
			return ResponseEntity.badRequest().build()
		}
		val result = input.getLicence(licenceId)

		return if (result.isSuccess) {
			ResponseEntity(result.getOrNull()!!.toDto(links), HttpStatus.OK)
		} else {
			when (result.exceptionOrNull()) {
				is LicenceNotFoundException -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()
				else -> ResponseEntity.internalServerError().build()
			}
		}
	}

	@PutMapping("/licences/{id}")
	@Operation(
		summary = "Update vehicle",
		description = "Update vehicle",
		parameters = [
			Parameter(
				name = "vin",
				description = "Licence id to be updated",
				`in` = ParameterIn.PATH
			)
		],
		requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
			content = [
				Content(
					mediaType = "application/json",
					schema = Schema(implementation = LicenceUpdateModel::class)
				)
			],
			required = true
		),
		responses = [
			ApiResponse(
				responseCode = "200",
				description = "Licence updated successfully",
				content = [
					Content(
						mediaType = "application/json",
						schema = Schema(implementation = LicenceResponseDto::class)
					)
				]
			),
			ApiResponse(
				responseCode = "400",
				description = "Invalid licence information",
				content = [Content()]
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
	fun updateLicence(
		@RequestBody licence: LicenceUpdateModel,
		@PathVariable id: String,
	): HttpEntity<LicenceResponseDto> {
		val links = WebMvcLinkBuilder.linkTo(
			WebMvcLinkBuilder.methodOn(LicenceController::class.java).updateLicence(licence, id)
		).withSelfRel()

		val result = input.updateLicence(id, licence.expireDate, licence.releaseDate, licence.residence)
		return if (result.isSuccess) {
			ResponseEntity(result.getOrNull()!!.toDto(links), HttpStatus.CREATED)
		} else {
			when (result.exceptionOrNull()) {
				is InvalidLicenceException -> ResponseEntity.badRequest().build()
				is LicenceExistsException -> ResponseEntity.status(HttpStatus.CONFLICT).build()
				is LicenceNotFoundException -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()
				else -> ResponseEntity.internalServerError().build()
			}
		}
	}
}
