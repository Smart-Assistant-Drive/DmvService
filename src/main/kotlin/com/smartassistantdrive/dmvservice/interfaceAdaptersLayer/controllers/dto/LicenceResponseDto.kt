package com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.controllers.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.smartassistantdrive.dmvservice.businessLayer.adapter.LicenceResponseModel
import java.time.format.DateTimeFormatter
import org.springframework.hateoas.Link
import org.springframework.hateoas.RepresentationModel

class LicenceResponseDto @JsonCreator constructor(
	@param:JsonProperty("licenceId") val vin: String,
	@param:JsonProperty("name") val plate: String,
	@param:JsonProperty("surname") val model: String,
	@param:JsonProperty("birthdate") val cv: String,
	@param:JsonProperty("country") val cc: String,
	@param:JsonProperty("registrationDate") val registrationDate: String,
	@param:JsonProperty("expireDate") val expireDate: String,
	@param:JsonProperty("residence") val residence: String,
) : RepresentationModel<LicenceResponseDto?>()

fun LicenceResponseModel.toDto(link: Link): LicenceResponseDto {
	val birthdateTime = birthDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
	val expiredateTime = expireDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
	val releasedateTime = releaseDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
	return LicenceResponseDto(
		licenceId,
		name,
		surname,
		birthdateTime,
		licenceCountry,
		releasedateTime,
		expiredateTime,
		residence
	).add(
		link
	)
}
