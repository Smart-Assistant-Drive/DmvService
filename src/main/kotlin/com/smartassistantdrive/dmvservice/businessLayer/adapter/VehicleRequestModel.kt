package com.smartassistantdrive.dmvservice.businessLayer.adapter

private const val NA = "NA"

data class VehicleRequestModel(
	val vin: String,

	val plate: String,

	val model: String,

	val cv: Int,

	val cc: Int,

	val registrationDate: String,
)
