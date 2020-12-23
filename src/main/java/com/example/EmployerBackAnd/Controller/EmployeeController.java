package com.example.EmployerBackAnd.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.EmployerBackAnd.repository.EmployeeRepository;
import com.example.EmployerBackAnd.Model.Employee;
import com.example.EmployerBackAnd.exception.ResourceNotFoundException;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	
	@GetMapping("/employees")
	public List<Employee> getAllEmployee(){
		return employeeRepository.findAll();
		}
	
	//create employee rest api 
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee)
	{
		return employeeRepository.save(employee);
	}
	//get employee by id 
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("this user does not existe"));
		return ResponseEntity.ok(employee);
	}
	//Update employee by id 
		@PutMapping("/employees/{id}")
		public ResponseEntity<Employee> UpdateEmployeeById(@PathVariable Long id,@RequestBody Employee employeeDetails) {
			Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("this user does not existe"));
			employee.setFirstName(employeeDetails.getFirstName());
			employee.setLastName(employeeDetails.getLastName());
			employee.setEmail(employeeDetails.getEmail());
			Employee updateEmployee = employeeRepository.save(employee);
			return ResponseEntity.ok(updateEmployee);
		}
	//Delete employee by id 
		@DeleteMapping("/employees/{id}")
		public ResponseEntity<Map<String , Boolean>> deleteEmployee(@PathVariable Long id){
			Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("this user does not existe"));
				employeeRepository.delete(employee);
				Map<String,Boolean> response = new HashMap<>();
				response.put("deleted", Boolean.TRUE);
				return ResponseEntity.ok(response);
				
		}
}
