package com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.controllers.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.smartassistantdrive.dmvservice.businessLayer.adapter.VehicleResponseModel
import java.time.format.DateTimeFormatter
import org.springframework.hateoas.Link
import org.springframework.hateoas.RepresentationModel

/**
 *
 */
class VehicleResponseDto @JsonCreator constructor(

	/**
	 *
	 */
	@param:JsonProperty("vin") val vin: String,

	/**
	 *
	 */
	@param:JsonProperty("plate") val plate: String,

	/**
	 *
	 */
	@param:JsonProperty("model") val model: String,

	/**
	 *
	 */
	@param:JsonProperty("cv") val cv: String,

	/**
	 *
	 */
	@param:JsonProperty("cc") val cc: String,

	/**
	 *
	 */
	@param:JsonProperty("registrationDate") val registrationDate: String,
) : RepresentationModel<VehicleResponseDto?>()

/**
 *
 */
fun VehicleResponseModel.toDto(link: Link): VehicleResponseDto {
	val registrationDate = vehicle.registrationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
	return VehicleResponseDto(
		vehicle.vin,
		vehicle.plate,
		vehicle.model,
		vehicle.cv.toString(),
		vehicle.cc.toString(),
		registrationDate
	).add(
		link
	)
}
