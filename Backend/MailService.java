package com.example.sign_up;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender emailSender;
	
	public String sendMail(String username,String verificationCode) 
			throws Exception{
		MimeMessage mimeMessage = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		StringBuffer htmlmsg = new StringBuffer();
		htmlmsg.append("<h1> pls activate your email by clicking below <h1>");
		htmlmsg.append("<a href='http://localhost:9090/person/verifyEmail?email=" + username + "&verificationCode=" + verificationCode + "'>Verify email Link</a>");
		helper.setText(htmlmsg.toString(),true);
		helper.setTo(username);
		helper.setSubject("verify/activate email id");
		emailSender.send(mimeMessage);
		return "success";
	}
	
	public String forgottenPassword(String email) throws Exception{
		MimeMessage mimeMessage = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		StringBuffer htmlmsg = new StringBuffer();
		htmlmsg.append("<h1> Reset Password<h1>");
		htmlmsg.append("<form action='http://localhost:9090/person/resetPassword' method = 'post'>");
		htmlmsg.append("New Password : <input type='password' name = 'password'> <br>");
		htmlmsg.append("Confirm Password : <input type='password' name = 'confirmPassword'> <br>");
		htmlmsg.append("<input type='hidden' name ='email' value='" + email + "'> ");
		htmlmsg.append("<input type='submit' value='change password'>");
		htmlmsg.append("</form>");
		helper.setText(htmlmsg.toString(),true);
		helper.setTo(email);
		helper.setSubject("reset password");
		emailSender.send(mimeMessage);
		return "Mail sent to" + email + "to reset the password";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public String testMail() throws Exception{
//		MimeMessage mimeMessage = emailSender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//		String htmlmsg = "<h3> <a href = 'https://youtube.com'>test Link</a></h3>";
//		helper.setText(htmlmsg,true);
//		helper.setTo("arif123004@gmail.com");
//		helper.setSubject("test mail from spring mail");
//		emailSender.send(mimeMessage);
//		return "success";
//	}

}
