package com.example.springboot.service;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.springboot.model.Employee;
import com.example.springboot.web.dto.UserRegistrationDto;

public interface EmployeeService extends UserDetailsService {
	Employee save(UserRegistrationDto registrationDto);
	Employee add(UserRegistrationDto registrationDto);
	List<Employee> getAllEmployees();
	//Employee addEmployee(UserRegistrationDto registrationDto);
	
	Employee getEmployeeById(long id);
	void deleteEmployeeById(long id);
	Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	 public List<Employee> getByKeyword(String keyword);
	
}
