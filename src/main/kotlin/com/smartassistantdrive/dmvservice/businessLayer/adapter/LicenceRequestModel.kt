package com.smartassistantdrive.dmvservice.businessLayer.adapter

private const val NA = "NA"

data class LicenceRequestModel(
	val name: String = NA,
	val surname: String = NA,
	val birthDate: String = NA,
	val licenceCountry: String = NA,
	val residence: String = NA,
)
