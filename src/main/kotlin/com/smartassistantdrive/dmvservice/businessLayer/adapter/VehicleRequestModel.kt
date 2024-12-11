package com.smartassistantdrive.dmvservice.businessLayer.adapter

/**
 *
 */
data class VehicleRequestModel(
	/**
	 *
	 */
	val vin: String,

	/**
	 *
	 */
	val plate: String,

	/**
	 *
	 */
	val model: String,

	/**
	 *
	 */
	val cv: Int,

	/**
	 *
	 */
	val cc: Int,

	/**
	 *
	 */
	val registrationDate: String,
)
