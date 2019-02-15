package com.dc.spring.utility.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DateController {

	@GetMapping(value="timestamp")
	public LocalDateTime getTimestamp() {
		return LocalDateTime.now();
	}
	
	@GetMapping(value="date")
	public LocalDate getDate() {
		return LocalDate.now();
	}
}
