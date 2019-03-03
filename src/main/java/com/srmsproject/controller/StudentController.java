package com.srmsproject.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.srmsproject.repository.StudentRepository;
import com.srmsproject.model.Student;

@Controller
public class StudentController {
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	public String finalString = null;
	@Autowired
	private StudentRepository studentRepository;
	@PostMapping(value="/")
	public String addAStudent(@ModelAttribute @Valid Student newStudent, BindingResult bindingResult, Model model){
		if (bindingResult.hasErrors()) {
			System.out.println("BINDING RESULT ERROR");
			return "index";
		} else {
			model.addAttribute("student", newStudent);

			if (newStudent.getName() != null) {
				try {
					String comments = checkNullString(newStudent.getComments());
					if (comments != "None") {
						System.out.println("nothing changes");
					} else {
						newStudent.setComments(comments);
					}
				} catch (Exception e) {
					System.out.println(e);
				}
				studentRepository.save(newStudent);
			}

			return "thanks";
		}
	}
	
	@GetMapping(value="thanks")
	public String thankYou(@ModelAttribute Student newStudent, Model model){
		model.addAttribute("student",newStudent);
		
		return "thanks";
	}
	@GetMapping(value="/")
	public String viewTheForm(Model model){
		Student newStudent = new Student();
		model.addAttribute("student",newStudent);
		return "index";
	}
	@GetMapping(value="/studlist")
	public String studList(@ModelAttribute Student newStudent, Model model){
		model.addAttribute("student", newStudent);
		return "studlist";
	}
	
	public String checkNullString(String str){
		String endString = null;
		if(str == null || str.isEmpty()){
			str = null;
			Optional<String> opt = Optional.ofNullable(str);
			endString = opt.orElse("None");
		}
		else{

		}
		return endString;
		
	}

}
