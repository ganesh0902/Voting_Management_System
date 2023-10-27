package com.vot.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.vot.entities.Candidate;
import com.vot.entities.List_of_vote;
import com.vot.entities.User;
import com.vot.password.PasswordEncoder;
import com.vot.service.FileService;
import com.vot.serviceimpl.ServiceImpl;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@org.springframework.stereotype.Controller
@RequestMapping("/vote")
public class Controller {

	@Autowired
	private ServiceImpl service;
	
	@Value("${project.image}")
	private String path;
	
	@Autowired
	private FileService fileService;
	
	@GetMapping("/login")
	public String loginPage()
	{
		String encode2 = PasswordEncoder.encode("shubham");
		//System.out.println(encode2);
		
		//boolean encode = PasswordEncoder.matches("123","$2a$10$j3Lt5uT3NNDgJAZS8epYAOVZzUXD2TnAHGxEZ.GdIWBjxsP739VGe");
				
		//System.out.println(encode);
		return "Login";
	}
	@PostMapping("/home")
	public String userHome(@RequestParam("username")String email,@RequestParam("password") String password,Model model,HttpSession session)
	{										
		String checkUsernameAndPassword = service.checkUsernameAndPassword(email, password);
		String checkAdminAuthentication = service.checkAdminAuthentication(email,password);
		
		model.addAttribute("image","IMG_20190727_183838.jpg");
				
		if(checkAdminAuthentication!="login")
		{
			session.setAttribute("message",checkAdminAuthentication);
		}
		else
		{			
			model.addAttribute("records",service.getAll());
			session.setAttribute("username",email);
			
			return "AdminHome";	
		}		
		if(checkUsernameAndPassword!="")
		{				
			session.setAttribute("message",checkUsernameAndPassword);										
			return "redirect:/vote/login";
		}
		else
		{					  
			model.addAttribute("userId",service.checkEmailIdAlredyExistOrNot(email).getuId());
			model.addAttribute("status",service.checkEmailIdAlredyExistOrNot(email).isStatus());
			model.addAttribute("candidates",service.getAll());
			model.addAttribute("username",email);			
			
			return "homePage";
		}				
	}			
	@GetMapping("/register")	
	public String Register()
	{		
		
		return "registration";
	}		
	@PostMapping("/save")	
	public String Save(@ModelAttribute User user, Model model,HttpSession session)
	{					
		User userexistOrNot = service.checkEmailIdAlredyExistOrNot(user.getEmail());
				
		if(userexistOrNot==null)
		{
			service.save(user);			
			session.setAttribute("error","");
			return "redirect:/vote/login";			
		}		
		else
		{	
		
			session.setAttribute("error","Email Alredy Exist in database ");
			return "redirect:/vote/register";
		}
		
	}		
	@ResponseBody
	@PostMapping("/addVote")	
	public String addVote(@RequestParam("candidateId") String candidateId,@RequestParam("userId") String userId)
	{
		
		long userid = Long.parseLong(userId);
		long candidateid = Long.parseLong(candidateId);										
		
		service.updateStatus(userid,candidateid);
		
		return "Ok";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session)
	{
		session.invalidate();
		
		return "redirect:/vote/login";
	}	
	@GetMapping("/adminLogout")
	public String adminLogOut(HttpSession session)
	{		
		session.removeAttribute("username");
		session.removeAttribute("message");
		return "redirect:/vote/login";
	}			
	@GetMapping("/addrecords")	
	public String addrecords(Model model)
	{				
		List<List_of_vote> listOfVotes = service.listOfVotes();				
		model.addAttribute("records", listOfVotes);
				
		return "AllRecords";
	}
	@GetMapping("/addAdmin")
	public String addCandidate()
	{
		return "/addAdmin"; 
	}
	@PostMapping("/saveCandidate")
	public String saveCandidate(@ModelAttribute Candidate candidate,@RequestParam("file") MultipartFile file) throws IOException
	{			
			String uploadImage = fileService.uploadImage(path, file);		
			candidate.setFileName(uploadImage);												
			service.saveCandidate(candidate);
		
		return "addAdmin";	
	}
	@ResponseBody
	@GetMapping(value="/image/{filename}",produces=MediaType.IMAGE_JPEG_VALUE)
	public void saveFile(@PathVariable("filename") String filename,HttpServletResponse response) throws IOException
	{	
		InputStream resource = this.fileService.getResource(path, filename);
		
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		
		StreamUtils.copy(resource, response.getOutputStream());
	}
}