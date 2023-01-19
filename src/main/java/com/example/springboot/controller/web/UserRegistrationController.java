package com.example.springboot.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springboot.service.EmployeeService;
import com.example.springboot.web.dto.UserRegistrationDto;

@Controller

public class UserRegistrationController {
	private EmployeeService employeeService;

	public UserRegistrationController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;

	
	}
	@ModelAttribute("employee")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
	
	@GetMapping("/registration")
	public String showRegistrationForm() {
		return "registration";
	}
	
	
	@PostMapping("/registration")
	public String registerUserAccount(@ModelAttribute("employee") UserRegistrationDto registrationDto) {
		employeeService.save(registrationDto);
		return "redirect:/registration?success";
	}

}
