package com.smartassistantdrive.dmvservice.businessLayer

import com.smartassistantdrive.dmvservice.businessLayer.boundaries.LicenceDataSourceGateway
import io.cucumber.java.en.Given
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import org.junit.runner.RunWith
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.mock

@RunWith(Cucumber::class)
@CucumberOptions(
	plugin = ["pretty", "html:target/cucumber-report.html"],
	features = ["src/test/resources"]
)
class CreateLicenceTest {

	private var name: String? = null
	private var surname: String? = null
	private var birthDate: LocalDate? = null

	/* private var licenceId: String? = null
	private var licenceCountry: String? = null
	private var expireDate: LocalDate? = null
	private var releaseDate: LocalDate? = null */

	private var residence: String? = null

	private val interaction: LicenceUseCase

	private val licenceDataSourceGateway = mock<LicenceDataSourceGateway> {
		on {
			save(any())
		} doAnswer { Result.success("OK") }
	}

	init {
		interaction = LicenceUseCase(licenceDataSourceGateway)
	}

	@Given("the licence to create is for {string} {string}")
	fun the_user_inserts_its_name(name: String, surname: String) {
		this.name = name
		this.surname = surname
	}

	@Given("the driver lives in {string}")
	fun the_user_inserts_its_residence(residence: String) {
		this.residence = residence
	}

	@Given("and he was born in {string}")
	fun the_user_inserts_its_name(birthdate: String) {
		this.birthDate = LocalDate.parse(birthdate, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
	}
}
