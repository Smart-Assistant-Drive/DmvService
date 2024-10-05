package com.smartassistantdrive.dmvservice.domainLayer

import java.time.LocalDateTime

interface Licence {

    val licenceId: String

    val name: String

    val surname: String

    val birthDate: LocalDateTime

    val licenceCountry: String

    val expireDate: LocalDateTime

    val releaseDate: LocalDateTime

    val residence: String

    companion object {
        fun create(
            licenceId: String,
            name: String,
            surname: String,
            birthDate: LocalDateTime,
            licenceCountry: String,
            expireDate: LocalDateTime,
            releaseDate: LocalDateTime,
            residence: String
        ): Licence {
            return object : Licence {
                override val licenceId: String
                    get() = licenceId
                override val name: String
                    get() = name
                override val surname: String
                    get() = surname
                override val birthDate: LocalDateTime
                    get() = birthDate
                override val licenceCountry: String
                    get() = licenceCountry
                override val expireDate: LocalDateTime
                    get() = expireDate
                override val releaseDate: LocalDateTime
                    get() = releaseDate
                override val residence: String
                    get() = residence
            }
        }
    }

}