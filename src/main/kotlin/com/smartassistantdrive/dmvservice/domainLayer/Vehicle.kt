package com.smartassistantdrive.dmvservice.domainLayer

import java.time.LocalDate

/**
 *
 */
interface Vehicle {

	/**
	 *
	 */
	val vin: String

	/**
	 *
	 */
	val plate: String

	/**
	 *
	 */
	val model: String

	/**
	 *
	 */
	val cv: Int

	/**
	 *
	 */
	val cc: Int

	/**
	 *
	 */
	val registrationDate: LocalDate

	/**
	 *
	 */
	fun isPlateValid(): Boolean

	/**
	 *
	 */
	companion object {
		/**
		 *
		 */
		fun create(vin: String, plate: String, model: String, cv: Int, cc: Int, registrationDate: LocalDate): Vehicle {
			return object : Vehicle {
				override val vin: String
					get() = vin
				override val plate: String
					get() = plate
				override val model: String
					get() = model
				override val cv: Int
					get() = cv
				override val cc: Int
					get() = cc
				override val registrationDate: LocalDate
					get() = registrationDate

				override fun isPlateValid(): Boolean {
					TODO("Not yet implemented")
				}
			}
		}
	}
}
