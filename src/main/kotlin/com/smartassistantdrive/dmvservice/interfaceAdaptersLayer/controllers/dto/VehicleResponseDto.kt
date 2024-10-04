package com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.controllers.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.smartassistantdrive.dmvservice.domainLayer.Vehicle
import com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.controllers.Controller
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.http.HttpEntity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class VehicleResponseDto @JsonCreator constructor(
    @param:JsonProperty("vin") val vin: String,
    @param:JsonProperty("plate") val plate: String,
    @param:JsonProperty("model") val model: String,
    @param:JsonProperty("cv") val cv: String,
    @param:JsonProperty("cc") val cc: String,
    @param:JsonProperty("registrationDate") val registrationDate: String,
): RepresentationModel<VehicleResponseDto?>()

fun VehicleResponseDto.toDto(methodRequest: HttpEntity<VehicleResponseDto>): VehicleResponseDto {
    val responseTime = LocalDateTime.parse(registrationDate)
    val jsonTime = responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss"))

    return VehicleResponseDto(vin, plate, model, cv, cc, jsonTime).add(
        WebMvcLinkBuilder.linkTo(methodRequest)
            .withSelfRel()
    )
}