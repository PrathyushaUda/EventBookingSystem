package com.SpringFrame.EventBookingSystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class EmailService {
	 @Autowired
	    private JavaMailSender mailSender;

	    public void sendBookingConfirmation(String toEmail, String name, String eventName, int tickets, double amount) {

	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(toEmail);
	        message.setSubject("Booking Confirmed - Eventalyze 🎫");

	        message.setText(
	        	    "Dear " + name + ",\n\n" +

	        	    "We are pleased to inform you that your booking has been successfully confirmed.\n\n" +

	        	    "📌 Booking Details:\n" +
	        	    "Event Name   : " + eventName + "\n" +
	        	    "Number of Tickets : " + tickets + "\n" +
	        	    "Total Amount : ₹" + amount + "\n\n" +

	        	    "Please keep this email for your reference. We look forward to seeing you at the event.\n\n" +

	        	    "Best Re   gards,\n" +
	        	    "Event Booking Team"
	        	);

	        mailSender.send(message);
	    }
	    public void sendOtpEmail(String toEmail, String otp) {
	    	 System.out.println("OTP method triggered for: " + toEmail);
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(toEmail);
	        message.setSubject("Your OTP for Login 🔐");

	        message.setText(
	            "Dear User,\n\n" +
	            "Your One-Time Password (OTP) is: " + otp + "\n\n" +
	            "This OTP is valid for 5 minutes.\n\n" +
	            "Please do not share this OTP with anyone.\n\n" +
	            "Best Regards,\nEvent Booking Team"+otp
	        );

	        mailSender.send(message);
	    }
}
