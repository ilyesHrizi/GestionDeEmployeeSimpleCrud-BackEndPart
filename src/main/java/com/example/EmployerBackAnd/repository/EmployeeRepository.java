package com.example.EmployerBackAnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.EmployerBackAnd.Model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{

	
}
