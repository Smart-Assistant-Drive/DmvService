package com.smartassistantdrive.dmvservice.businessLayer.adapter

import java.time.LocalDate

private const val NA = "NA"

data class LicenceUpdateModel(
	val expireDate: LocalDate,
	val releaseDate: LocalDate,
	val residence: String,
)
