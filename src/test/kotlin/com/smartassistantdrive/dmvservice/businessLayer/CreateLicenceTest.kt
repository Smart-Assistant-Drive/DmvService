package com.smartassistantdrive.dmvservice.businessLayer

import com.smartassistantdrive.dmvservice.businessLayer.boundaries.LicenceDataSourceGateway
import com.smartassistantdrive.dmvservice.domainLayer.Licence
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import java.time.LocalDate
import kotlin.test.assertEquals
import org.junit.runner.RunWith
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@RunWith(Cucumber::class)
@CucumberOptions(
	plugin = ["pretty", "html:target/cucumber-report.html"],
	features = ["src/test/resources"]
)
class CreateLicenceTest {

	private lateinit var name: String
	private lateinit var surname: String
	private lateinit var birthDate: String

	private lateinit var licenceId: String
	private lateinit var licenceCountry: String
	private lateinit var expireDate: LocalDate
	private lateinit var releaseDate: LocalDate

	private lateinit var residence: String

	private var interaction: LicenceUseCase

	val db = ArrayList<Licence>()

	private val licenceDataSourceGateway = mock<LicenceDataSourceGateway>()

	init {
		whenever(licenceDataSourceGateway.save(any<Licence>())).thenAnswer {
			val licenceArgument = it.arguments[0] as Licence
			db.add(licenceArgument)
			licenceArgument.licenceId
		}
		whenever(licenceDataSourceGateway.getLicence(any<String>())).thenAnswer { request ->
			val idArg = request.arguments[0] as String
			val returnValue: Licence = db.first { it.licenceId == idArg }
			returnValue
		}
		whenever(licenceDataSourceGateway.getNewId()).thenAnswer {
			123L
		}
		interaction = LicenceUseCase(licenceDataSourceGateway)
	}

	@Given("the licence to create is for {string} {string}")
	fun the_user_inserts_its_name(name: String, surname: String) {
		this.name = name
		this.surname = surname
		this.licenceId = "123"
		this.licenceCountry = "Italy"
		this.releaseDate = LocalDate.now()
		this.expireDate = LocalDate.now().plusYears(10)
	}

	@Given("the driver lives in {string}")
	fun the_user_inserts_its_residence(residence: String) {
		this.residence = residence
	}

	@Given("and he was born in {string}")
	fun the_user_inserts_its_name(birthdate: String) {
		this.birthDate = birthdate
	}

	@Then("the creation of the licence should be correct")
	fun `then the creation of the licence should be correct`() {
		val id = interaction.addLicence(name, surname, birthDate, licenceCountry, residence).getOrNull()?.licenceId!!
		val licenceAdded = interaction.getLicence(id)
		assertEquals(this.name, licenceAdded.getOrNull()?.name)
	}
}
