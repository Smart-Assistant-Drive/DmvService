package com.smartassistantdrive.dmvservice.businessLayer.boundaries

import com.smartassistantdrive.dmvservice.domainLayer.Vehicle

interface InputBoundary {

    fun addCar()

    fun getCarByPlate(plate: String): Vehicle

}