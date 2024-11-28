package com.example.sign_up;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("person")
@CrossOrigin
public class PersonController {
	
//	@Autowired
//	private MailService mailService;
//	
//    @GetMapping
//	public String sendMail() throws Exception{
//		return mailService.testMail();
//	}
	
	@Autowired
	private PersonService personService;
	
	@PostMapping("save")
	public ResponseEntity<Person> save(@RequestBody Person  person) 
			 throws Exception{
		return ResponseEntity.ok(personService.save(person));
	}
	
	@PostMapping("login")
	public ResponseEntity<LoginVAO> login (@RequestBody Person person)
			throws Exception{
		return ResponseEntity.ok(personService.login(person));
	}
	// 
//	{
//		"firstName" : "arif",
//		"lastName" : "shaik",
//		"username" : "arif123004@gmail.com",
//		"password" : "123"	
//	}
//	
	@GetMapping("verifyEmail")
	public ResponseEntity<String> verifyEmail(@RequestParam String email,
			@RequestParam String verificationCode) throws Exception{
		return ResponseEntity.ok(personService.verifyEmail(email,verificationCode));
	}
	
	@GetMapping("forgottenPassword/{email}")
	public ResponseEntity<ForgottenPasswordVAO> forgottenPassword(@PathVariable String email)throws Exception{
		String results = personService.forgottenPassword(email);
		ForgottenPasswordVAO vao = new ForgottenPasswordVAO();
		vao.setResults(results);
		return ResponseEntity.ok(vao);
	}
	
	@PostMapping("resetPassword")
	public ResponseEntity<String> resetPassword(@RequestParam String email,@RequestParam 
			String password) throws Exception{
		return ResponseEntity.ok(personService.resetPassword(email,password));
	}
	@PostMapping("changePassword")
	public ResponseEntity<ChangePasswordVAO> changePassword(@RequestBody Person person){
		String results = personService.changePassword(person);
		ChangePasswordVAO vao = new ChangePasswordVAO();
		vao.setResults(results);
		return ResponseEntity.ok(vao);
	}
}
