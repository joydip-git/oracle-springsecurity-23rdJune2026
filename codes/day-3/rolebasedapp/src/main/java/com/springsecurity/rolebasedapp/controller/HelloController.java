package com.springsecurity.rolebasedapp.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.rolebasedapp.models.Student;

@RestController
public class HelloController {

	private List<Student> students = new ArrayList<>(List.of(new Student(1,"joy",45),
			new Student(2,"ram",56)));
	
	@GetMapping("/")
	public Collection<Student> getStudents() {
		return students;
	}
	
	@PostMapping("/")
	public Student addStudent(@RequestBody Student student) {
		students.add(student);
		return student;
	}
}
