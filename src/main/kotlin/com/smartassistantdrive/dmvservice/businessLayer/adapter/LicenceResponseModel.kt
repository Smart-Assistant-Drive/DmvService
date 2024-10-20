package com.smartassistantdrive.dmvservice.businessLayer.adapter

import java.time.LocalDate

data class LicenceResponseModel(
	val licenceId: String,

	val name: String,

	val surname: String,

	val birthDate: LocalDate,

	val licenceCountry: String,

	val expireDate: LocalDate,

	val releaseDate: LocalDate,

	val residence: String,
)
