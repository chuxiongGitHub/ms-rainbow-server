package com.rainbow.ms.controller

import com.rainbow.ms.commons.SuperController
import com.rainbow.ms.entity.Driver
import com.rainbow.ms.service.DriverService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/driver")
class DriverController : SuperController<DriverService, Driver>()
