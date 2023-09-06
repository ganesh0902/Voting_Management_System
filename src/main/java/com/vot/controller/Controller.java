package com.vot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vot.entities.List_of_vote;
import com.vot.entities.User;
import com.vot.serviceimpl.ServiceImpl;

import jakarta.servlet.http.HttpSession;

@org.springframework.stereotype.Controller
@RequestMapping("/vote")
public class Controller {

	@Autowired
	private ServiceImpl service;
		
	@GetMapping("/login")
	public String loginPage()
	{
		return "Login";
	}
	@PostMapping("/home")
	public String userHome(@RequestParam("username")String email,@RequestParam("password") String password,Model model,HttpSession session)
	{
										
		String checkUsernameAndPassword = service.checkUsernameAndPassword(email, password);
		String checkAdminAuthentication = service.checkAdminAuthentication(email,password);
		
		if(checkAdminAuthentication!="login")
		{
			session.setAttribute("message",checkAdminAuthentication);
		}
		else
		{
			model.addAttribute("candidates",service.getAll());
			model.addAttribute("records",service.getAll());
			session.setAttribute("username",email);
			
			return "AdminHome";	
		}		
		if(checkUsernameAndPassword!="")
		{	
			System.out.println("Email"+checkUsernameAndPassword);
			session.setAttribute("message",checkUsernameAndPassword);						
			return "redirect:/vote/login";
		}
		else
		{					  
			model.addAttribute("userId",service.checkEmailIdAlredyExistOrNot(email).getuId());
			model.addAttribute("status",service.checkEmailIdAlredyExistOrNot(email).isStatus());
			model.addAttribute("candidates",service.getAll());
			session.setAttribute("username",email);
			
			return "homePage";
		}				
	}			
	@GetMapping("/register")	
	public String Register()
	{		
		
		return "registration";
	}		
	@PostMapping("/save")	
	public String Save(@ModelAttribute User user,Model model,HttpSession session)
	{					
		User userexistOrNot = service.checkEmailIdAlredyExistOrNot(user.getEmail());
		
		if(userexistOrNot==null)
		{
			service.save(user);
			System.out.println("Record save");
			session.setAttribute("error","");
			return "redirect:/vote/login";			
		}		
		else
		{	System.out.println("Record not save");
		
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
		
		return "login";
	}	
	@GetMapping("/adminLogout")
	public String adminLogOut(HttpSession session)
	{		
		session.removeAttribute("username");
		session.removeAttribute("message");
		return "login";
	}			
	@GetMapping("/addrecords")	
	public String addrecords(Model model)
	{		
		//List<List_of_vote> listOfVote = service.listOfVote();
		
		List<List_of_vote> listOfVotes = service.listOfVotes();				
		model.addAttribute("records", listOfVotes);
				
		return "AllRecords";
	}
}