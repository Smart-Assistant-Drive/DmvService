package com.smartassistantdrive.dmvservice.businessLayer.adapter

private const val NA = "NA"

/**
 *
 */
data class LicenceUpdateModel(
	/**
	 *
	 */
	val expireDate: String = NA,

	/**
	 *
	 */
	val releaseDate: String = NA,

	/**
	 *
	 */
	val residence: String = NA,
)
