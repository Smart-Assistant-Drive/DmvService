package com.smartassistantdrive.dmvservice.businessLayer.adapter

private const val NA = "NA"

/**
 *
 */
data class VehicleUpdateModel(
	/**
	 *
	 */
	val newPlate: String = NA,

	/**
	 *
	 */
	val newRegistrationDate: String = NA,
)
