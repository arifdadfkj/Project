package com.example.sign_up;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private MailService mailService;
	
	private String generateVerificationCode() {
		String src = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		StringBuffer sb = new StringBuffer();
		int lengthSrc = src.length();
		Random random = new Random();
		for(int i = 0 ; i <= 15; i++) {
			sb.append(src.charAt((int)(random.nextDouble() * lengthSrc)));
			
		}
		return sb.toString();
		}
	
	public Person save(Person person)throws Exception {
		String verificationCode = generateVerificationCode();
		person.setVerificationCode(verificationCode);
		person.setSignupStatus(0);
		person = personRepository.save(person);
		
		String username = person.getUsername();
		mailService.sendMail(username,verificationCode);
		return person; 
	}
	
	public String verifyEmail(String emailId, String verificationCode){
		Person person = personRepository.findByUsername(emailId);
		String msg = "something went wroung..... pls try again";
		if(person.getVerificationCode().equals(verificationCode)) {
			msg = "successfully Verified and you can do login";
			person.setSignupStatus(1);
			personRepository.save(person);
		}
		return msg;
	}
	
	public String forgottenPassword(String email) throws Exception {
		return mailService.forgottenPassword(email);
	}
	public String resetPassword(String email,String password) throws Exception {
		Person person = personRepository.findByUsername(email);
		person.setPassword(password);
		personRepository.save(person);
		return "password changed successfully";	
	}
	
	public String changePassword(Person person) {
		Person dbperson = personRepository.findByUsername(person.getUsername());
		dbperson.setPassword(person.getPassword());
		personRepository.save(dbperson);
		return "password changed Successfully";
	}
	
	public LoginVAO login(Person person) {
		LoginVAO vao = new LoginVAO();
		vao.setMessage("Login failed pls try again...");
		Person dbperson = personRepository.findByUsername(person.getUsername());
		if(dbperson.getPassword().equals(person.getPassword())) {
			vao.setLoginStatus(true);
			vao.setMessage("login is Success");
		}
		return vao;
		
	}

}
