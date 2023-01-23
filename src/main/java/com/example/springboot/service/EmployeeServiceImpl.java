package com.example.springboot.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springboot.model.Employee;
import com.example.springboot.model.Roles;
import com.example.springboot.repository.EmployeeRepository;
import com.example.springboot.web.dto.UserRegistrationDto;
import com.example.springboot.config.SecurityConfiguration;
@Service
public class EmployeeServiceImpl implements EmployeeService {
	 @Autowired 
    private EmployeeRepository employeeRepository;
   
    @Autowired
	private BCryptPasswordEncoder passwordEncoder;
    
    
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}


	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}


//	@Override
//	public void saveEmployee(Employee employee) {
//		// TODO Auto-generated method stub
//		this.employeeRepository.save(employee);
//		
//	}


	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		Employee employee = null;
		if (optional.isPresent()) {
			employee = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return employee;
		
	}
		


	@Override
	public void deleteEmployeeById(long id) {
		// TODO Auto-generated method stub
		this.employeeRepository.deleteById(id);
		
	}
	


	@Override
	public Employee save(UserRegistrationDto registrationDto) {
		Employee employee = new Employee(registrationDto.getFirstName(), 
				registrationDto.getLastName(), registrationDto.getEmail(),
				 passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Roles("ROLE_USER")));
		
		return employeeRepository.save(employee);
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		Employee employee = employeeRepository.findByEmail(username);
		if(employee == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(employee.getEmail(), employee.getPassword(), mapRolesToAuthorities(employee.getRoles()));		
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Roles> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

	}


	@Override
	public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.employeeRepository.findAll(pageable);
	}


	@Override
	public List<Employee> getByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return employeeRepository.findByKeyword(keyword);
	}


	@Override
	public Employee add(UserRegistrationDto registrationDto) {
		Employee employee = new Employee(registrationDto.getFirstName(), 
				registrationDto.getLastName(), registrationDto.getEmail(),
				 passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Roles("ROLE_USER")));
		
		return employeeRepository.save(employee);
	}


	


	


	

}
