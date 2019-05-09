package com.srmsproject.controller;

import com.srmsproject.model.Student;
import com.srmsproject.repository.SrmsHomeClient;
import com.srmsproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@Controller
@RequestMapping(value = "/")
public class MainController {
	SrmsHomeClient srmsHomeClient = new SrmsHomeClient();
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	public String finalString = null;
	@Autowired
	private StudentRepository studentRepository;
	@PostMapping("studReg")
	public String addAStudent(@ModelAttribute @Valid Student newStudent, BindingResult bindingResult, Model model){
		if (bindingResult.hasErrors()) {
			System.out.println("BINDING RESULT ERROR");
			return "thanks";
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
				srmsHomeClient.saveStudent(newStudent);
//				System.out.println(srmsHomeClient);
			}
			return "redirect:/";
		}
	}
	@GetMapping(value="thanks")
	public String thankYou(@ModelAttribute Student newStudent, Model model){
		model.addAttribute("student",newStudent);
		return "thanks";
	}

	@GetMapping(value="/index")
	public String viewTheForm(Model model){
		Student newStudent = new Student();
		model.addAttribute("student",newStudent);
		return "index";
	}
	@GetMapping(value="/studUpdate")
	public String viewTheUpdateForm(Model model, Student newStudent){
		System.out.println("Хуйня сработала!!");
		model.addAttribute("student", newStudent);
		return "studUpdate";
	}

	@GetMapping(value="/studUpdate/{id}")
	public String viewTheUpdateForm(@PathVariable ("id")long id, Model model){
		Student newStudent = studentRepository.getStudentById(id);
		model.addAttribute("student", newStudent);
		return "studUpdate";
	}
	@PostMapping(value = "/studUpdate/{id}")
	public String updateStudent(@PathVariable("id") long id, Model model, Pageable pageable, Student newStudent) {
		srmsHomeClient.saveStudent(newStudent);
		model.addAttribute("students", srmsHomeClient.getAllStudents(pageable));
		return "redirect:/";
	}

	@GetMapping(value="/")
	public String studList(@ModelAttribute Student newStudent, Model model, Pageable pageable){
		Page<Student> studPage = studentRepository.findAll(pageable);
		PageWrapper<Student> page = new PageWrapper<Student>(studPage, "/");
		model.addAttribute("students", page.getContent());
		model.addAttribute("page", page);
//		model.addAttribute("students", studentRepository.getAllStudents(pageable));
		return "studlist";
	}

	@GetMapping(value="/studDel/{id}")
	public String studDel(@PathVariable("id") long id, Model model){
		srmsHomeClient.deleteStudent(id);
		model.addAttribute("students", studentRepository.findAll());
		System.out.println("Studdelete client");
		return "redirect:/";
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
	@GetMapping(path = "/studInsert")
	public @ResponseBody String addStudent(@RequestParam String name, @RequestParam String email,@RequestParam String password,@RequestParam String comments){
		Student s = new Student();
		s.setName(name);
		s.setEmail(email);
		s.setPassword(password);
		s.setComments(comments);
		studentRepository.save(s);
		return "Saved";
	}

}
