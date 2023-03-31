package com.promineotech.jeep.controller;

import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

public @interface openAPIDefinition {

	Info info();

	Server[] servers();

	
}
