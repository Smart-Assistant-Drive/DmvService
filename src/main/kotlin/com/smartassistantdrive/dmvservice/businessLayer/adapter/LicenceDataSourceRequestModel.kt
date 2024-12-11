package com.smartassistantdrive.dmvservice.businessLayer.adapter

/**
 *
 */
data class LicenceDataSourceRequestModel(
	/**
	 *
	 */
	val licenceId: String,

	/**
	 *
	 */
	val name: String,

	/**
	 *
	 */
	val surname: String,

	/**
	 *
	 */
	val birthDate: String,

	/**
	 *
	 */
	val licenceCountry: String,

	/**
	 *
	 */
	val expireDate: String,

	/**
	 *
	 */
	val releaseDate: String,

	/**
	 *
	 */
	val residence: String,
)
