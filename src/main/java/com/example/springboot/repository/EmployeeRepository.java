package com.example.springboot.repository;
import com.example.springboot.model.Employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Employee findByEmail(String email);
	 @Query(value = "SELECT * FROM employees s WHERE s.first_name like %:keyword% or s.last_name like %:keyword%", nativeQuery = true)
	    List<Employee> findByKeyword(@Param("keyword") String keyword);
}
