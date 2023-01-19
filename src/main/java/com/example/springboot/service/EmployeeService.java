package com.example.springboot.service;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.springboot.model.Employee;
import com.example.springboot.web.dto.UserRegistrationDto;

public interface EmployeeService extends UserDetailsService {
	Employee save(UserRegistrationDto registrationDto);
	List<Employee> getAllEmployees();
	//void saveEmployee(Employee employee);
	Employee getEmployeeById(long id);
	void deleteEmployeeById(long id);
	
}
