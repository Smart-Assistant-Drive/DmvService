package com.smartassistantdrive.dmvservice.domainLayer

import java.time.LocalDate

/**
 *
 */
interface Licence {

	/**
	 *
	 */
	val licenceId: String

	/**
	 *
	 */
	val name: String

	/**
	 *
	 */
	val surname: String

	/**
	 *
	 */
	val birthDate: LocalDate

	/**
	 *
	 */
	val licenceCountry: String

	/**
	 *
	 */
	val expireDate: LocalDate

	/**
	 *
	 */
	val releaseDate: LocalDate

	/**
	 *
	 */
	val residence: String

	/**
	 *
	 */
	companion object {
		/**
		 *
		 */
		fun create(
			name: String,
			surname: String,
			birthDate: LocalDate,
			licenceCountry: String,
			expireDate: LocalDate,
			releaseDate: LocalDate,
			residence: String,
			licenceId: String = "",
		): Licence {
			return object : Licence {
				override val licenceId: String
					get() = licenceId
				override val name: String
					get() = name
				override val surname: String
					get() = surname
				override val birthDate: LocalDate
					get() = birthDate
				override val licenceCountry: String
					get() = licenceCountry
				override val expireDate: LocalDate
					get() = expireDate
				override val releaseDate: LocalDate
					get() = releaseDate
				override val residence: String
					get() = residence
			}
		}
	}
}
