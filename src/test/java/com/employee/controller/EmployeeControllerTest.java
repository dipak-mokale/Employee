package com.employee.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:/application-junit.properties")
public class EmployeeControllerTest {
	@LocalServerPort
	private int port;
	@Autowired
	private EmployeeRepository employeeRepository;

	private RequestSpecification requestSpecification;

	@PostConstruct
	public void initRequestSpec() {
		final RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
		requestSpecification = requestSpecBuilder.setBaseUri("http://localhost:" + port).build();
	}
	@BeforeEach
	public void setup() {
		employeeRepository.deleteAll();
	}

	@Test
	public void addEmployeeTest() {
		// given when then
		Employee employee = new Employee();
		employee.setName("deepak");
		employee.setSalary(35000);
		assertEquals(0, employeeRepository.count());

		ValidatableResponse validatableResponse = RestAssured.given(requestSpecification).contentType(ContentType.JSON)
				.body(employee).post("/add-employee").then();

		assertEquals(1, employeeRepository.count());
	}
	
	@Test
	public void getEmployeeTest() {
		//given when then
		assertEquals(0, employeeRepository.count());
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("deepak");
		employee.setSalary(35000);
		Employee save = employeeRepository.save(employee);
		assertEquals(1, employeeRepository.count());
		
		ValidatableResponse response = RestAssured.given(requestSpecification).get("/"+save.getId()).then();
		
		Employee employee2 = response.extract().as(Employee.class);
		
		assertEquals("deepak", employee2.getName());
	}
}
