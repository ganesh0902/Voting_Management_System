package com.vot.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vot.entities.Candidate;
import com.vot.entities.List_of_vote;
import com.vot.entities.User;
import com.vot.repository.CandidateRepository;
import com.vot.repository.UserRepository;
import com.vot.service.ServiceDao;

@Service
public class ServiceImpl implements ServiceDao{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CandidateRepository candidateRepo;
		
	@Override
	public User save(User user) {
				
		return userRepo.save(user);
	}

	@Override
	public User checkEmailIdAlredyExistOrNot(String email) {
		
		return userRepo.findByEmail(email);		
	}

	@Override
	public List<User> getAllUser(){
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public List<Candidate> getAll() {
		// TODO Auto-generated method stub
		return candidateRepo.findAll();
	}

	@Override
	public User checkPasswordAlredyExistOrNot(String password) {
		// TODO Auto-generated method stub
		return userRepo.findByPassword(password);
	}

	@Override
	public void saveCandidate(Candidate candidate) {
				
		System.out.println("Record save successfully");
		
		candidateRepo.save(candidate);
		
	}

	@Override
	public User updateStatus(Long userId, Long candidateId) {
		// TODO Auto-generated method stub
		
		Optional<User> user = userRepo.findById(userId);
		Optional<Candidate> candidate = candidateRepo.findById(candidateId);
		
		if(user.isPresent())
		{
			user.get().setStatus(true);
			user.get().setCandidateId(candidateId);
			userRepo.save(user.get());
			
			if(candidate.isPresent())
			{
				int number_of_vote = candidate.get().getNumber_of_vote();
				
				System.out.println("Number of vote is "+number_of_vote);
				int count=number_of_vote=number_of_vote+1;
				
				candidate.get().setNumber_of_vote(count);
				Candidate save = candidateRepo.save(candidate.get());
				
				System.out.println("Record is "+save);
				System.out.println("Vote Added Successfully");
			}
		}		
		return null;
	}
	@Override
	public boolean checkuserStatus(Long userId) {
		// TODO Auto-generated method stub
		
		Optional<User> findById = userRepo.findById(userId);
		
		User user = findById.get();
		
		return user.isStatus();
	}
	@Override
	public String checkUsernameAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		String meesage="";
		try
		{
			User findByEmail = userRepo.findByEmail(email);
			
			if(findByEmail!=null)
			{
				if(!findByEmail.getPassword().equals(password))
				{
					meesage="Password is wrong";
				}				
			}
			else
			{				
				meesage="Invali Email";
			}
		}
		catch(Exception e)
		{System.out.println(e);
		}		
		return meesage;
	}

	@Override
	public String checkAdminAuthentication(String email, String password) {
		
		String message="";
		if(email.equals("ganeshs2987@gmail.com"))
		{
			message="validEmail";
			if(password.equals("ganesh123"))
			{
				message="login";
			}
			else
			{
				message="Password is wrong";
			}
		}		
		return message;
	}
	
	@Override
	public List<List_of_vote> listOfVotes() {
				
		List<User> users = userRepo.findAll();
		
		List<List_of_vote> list=new ArrayList<>();		
		try
		{
		for (User user : users) {
			
			Candidate candidate = candidateRepo.findById(user.getCandidateId()).get();			
						
			list.add(new List_of_vote(user.getuId(),user.getUsername(),user.getEmail(),user.getPhone(),candidate.getUsername()));
									
		}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}	
}