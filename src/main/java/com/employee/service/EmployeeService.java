package com.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.Exception.EmployeeNotAvailable;
import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee addEmpoyee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public void deleteEmployee(Integer id) {
		employeeRepository.deleteById(id);
	}

	public void updateEmployee(Integer id, Employee employee) {
		Optional<Employee> findEmp = employeeRepository.findById(id);
		if (findEmp.isPresent()) {
			Employee employee2 = findEmp.get();
			employee2.setId(id);
			employee2.setName(employee.getName());
			employee2.setSalary(employee.getSalary());
			employeeRepository.save(employee2);
		}
	}

	public Employee getEmployee(Integer id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if(employee.isPresent()) {
			Employee employee2 = employee.get();
			return employee2;
		}
		throw new EmployeeNotAvailable("Employee is not available with ID "+id);
		
	}

	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}
}
