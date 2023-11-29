package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Employee;
import com.employee.service.EmployeeService;
@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping()
	public ResponseEntity<Employee> getEmployee(@RequestParam Integer id) {
		Employee employee = employeeService.getEmployee(id);
		return new ResponseEntity<Employee>(employee,HttpStatus.OK);
	}
	
	@PostMapping("add-employee")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		Employee addEmpoyee = employeeService.addEmpoyee(employee);
		return new ResponseEntity<Employee>(addEmpoyee,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("delete-employee/{id}")
	public void deleteEmployee(@PathVariable("id") Integer id) {
		employeeService.deleteEmployee(id);
	}
	
	@PutMapping("update-employee/{id}")
	public void updateEmployee(@PathVariable("id")Integer id,@RequestBody  Employee employee) {
		employeeService.updateEmployee(id, employee);
	}
	
	@GetMapping("getall-Employee")
	public ResponseEntity<List<Employee>> getAllEmployee(){
		return new ResponseEntity<List<Employee>>(employeeService.getAllEmployees(),HttpStatus.OK);
	}
}
