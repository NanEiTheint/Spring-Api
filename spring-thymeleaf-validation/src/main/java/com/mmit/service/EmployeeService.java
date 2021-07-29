package com.mmit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmit.entity.Employee;
import com.mmit.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repo;

	public void save(Employee employee) {
		repo.save(employee);
		
	}

	public List<Employee> employeeList() {
		List<Employee> list=repo.findAll();
		return list;
	}

	public Employee findById(int id) {
		Employee emp=repo.getById(id);
		return emp;
	}

	public void delete(int id) {
		repo.deleteById(id);
		
	}

	

}
