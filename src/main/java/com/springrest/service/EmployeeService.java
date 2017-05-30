package com.springrest.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.model.Employee;
@RestController
public class EmployeeService {
	private static Set<Employee> employees=new HashSet<Employee>();
	static{
		
		Employee emp1=new Employee(1, "Ashwani", 27);
		Employee emp2=new Employee(2, "Rahul", 25);
		Employee emp3=new Employee(3, "Rupesh", 25);
		Employee emp4=new Employee(4, "Suresh", 30);
		employees.add(emp1);
		employees.add(emp2);
		employees.add(emp3);
		employees.add(emp4);
		
	}
	@RequestMapping(value="/employees/",method = RequestMethod.GET)
	public ResponseEntity<Set<Employee>> getEmployees(){
		System.out.println("This is getEmployees method");
		return new ResponseEntity<Set<Employee>>(employees, HttpStatus.OK);
	}
	
	@RequestMapping(value="/employees/{id}",method = RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") int id){
		System.out.println("This is getEmployee method");
		for(Employee emp:employees){
			if(id==emp.getId()){
				return new ResponseEntity<Employee>(emp, HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		
	}
	
	@RequestMapping(value="/employees/create",method = RequestMethod.POST,produces= MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createEmployee(@RequestBody Employee emp){
		System.out.println("This is createEmployee method");		
		employees.add(emp);		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/employees/{id}",method = RequestMethod.PUT,produces= MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> updateEmployee(@PathVariable int id,@RequestBody Employee emp){
		System.out.println("This is updateEmployee method");
		for(Employee employee:employees){
			if(id==employee.getId()){
				employee.setAge(emp.getAge());
				employee.setName(emp.getName());
				return new ResponseEntity<Employee>(HttpStatus.CREATED);
			}
		}
			
		return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/employees/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Employee> deleteEmployee(@PathVariable int id){
		System.out.println("This is deleteEmployee method");
		for(Employee employee:employees){
			if(id==employee.getId()){
				employees.remove(employee);
				return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
			}
		}
			
		return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
	}
	
	
}
