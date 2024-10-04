package com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.controllers

import com.smartassistantdrive.dmvservice.businessLayer.boundaries.InputBoundary
import com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.controllers.dto.VehicleResponseDto
import com.smartassistantdrive.dmvservice.interfaceAdaptersLayer.controllers.dto.toDto
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(input: InputBoundary) {

    val input = input

    companion object {
        private const val TEMPLATE = "Hello, %s!"
    }

    // API

    @CircuitBreaker(name = "CircuitBreakerService", fallbackMethod = "fallback")
    @GetMapping("/hello")
    fun hello(@RequestParam(value = "name", defaultValue = "World") name: String?): String {
        // Esempio dimostrativo CircuitBreaker
        val i = 4 / 0
        return String.format("Hello %s!", name)
    }

    @GetMapping("/addCar")
    fun addCar(@RequestParam(value = "name", defaultValue = "World") plate: String?): HttpEntity<VehicleResponseDto> {
        input.addCar()
        val method =  WebMvcLinkBuilder.methodOn(Controller::class.java).addCar(plate)
        val vehicle = input.getCarByPlate("RN1234OZ")
        return ResponseEntity(VehicleResponseDto(vehicle.vin, vehicle.plate, vehicle.model, vehicle.cv.toString(), vehicle.cc.toString(), vehicle.registrationDate.toString()).toDto(method), HttpStatus.CREATED)
    }

    fun fallback(name: String?, e: ArithmeticException): String {
        return "Some error occurred"
    }

}


/*
repository!!.deleteAll()


		// save a couple of customers
		repository!!.save(Customer("Alice", "Smith"))
		repository!!.save(Customer("Bob", "Smith"))


		// fetch all customers
		println("Customers found with findAll():")
		println("-------------------------------")
		for (customer in repository!!.findAll()) {
			println(customer)
		}
		println()


		// fetch an individual customer
		println("Customer found with findByFirstName('Alice'):")
		println("--------------------------------")
		println(repository!!.findByFirstName("Alice"))

		println("Customers found with findByLastName('Smith'):")
		println("--------------------------------")
		for (customer in repository!!.findByLastName("Smith")!!) {
			println(customer)
		}
 */