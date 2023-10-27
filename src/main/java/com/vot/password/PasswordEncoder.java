package com.vot.password;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

	 private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	 public static String encode(String password) {
	        return passwordEncoder.encode(password);
	    }
	 
	 public static boolean matches(String rawPassword, String encodedPassword) {
	        return passwordEncoder.matches(rawPassword, encodedPassword);
	    }
}
