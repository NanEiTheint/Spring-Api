package com.mmit.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mmit.entity.Employee;
import com.mmit.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@GetMapping()
	public String index(ModelMap model)
	{
		List<Employee> empList=service.employeeList();
		model.addAttribute("empList",empList );
		return ("index");
	}
	
	@GetMapping("/employee-create")
	public String employeeCreate(ModelMap model)
	{
		model.put("employee", new Employee());
		return "create-employee";
	}
	
	@ModelAttribute("position")
	public List<String> getPosition()
	{
		return List.of("Programmer","Developer","Designer","Accountant");
	}
	
	@PostMapping("/employee-save")
	public String saveEmployee(@Valid Employee employee,BindingResult bResult)
	{
		if(bResult.hasErrors())
			return "create-employee";
		else
		{
			service.save(employee);
			return "redirect:/";
		}
	}
	
	@GetMapping("/employee-edit/{id}")
	public String editEmployee(@PathVariable String id,ModelMap model)
	{
		if(id!=null || id!="")
		{
			Employee emp=service.findById(Integer.parseInt(id));
			model.put("employee", emp);
		}
		return "create-employee";
	}
	
	@GetMapping("/employee-delete/{id}")
	public String deleteEmployee(@PathVariable String id)
	{
		if(id!=null || id!="")
		{
			service.delete(Integer.parseInt(id));
			
		}
		return "redirect:/";
	}
	
	

}
