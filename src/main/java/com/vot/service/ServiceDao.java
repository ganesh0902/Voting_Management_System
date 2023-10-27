package com.vot.service;

import java.util.List;
import java.util.Set;

import com.vot.entities.Candidate;
import com.vot.entities.List_of_vote;
import com.vot.entities.User;

public interface ServiceDao {
	
	public User save(User user);
	public User checkEmailIdAlredyExistOrNot(String email);
	public User checkPasswordAlredyExistOrNot(String password);
	public String checkUsernameAndPassword(String email,String password);
	public List<User> getAllUser();
	public List<Candidate> getAll();
	public Candidate saveCandidate(Candidate candidate);
	public User updateStatus(Long userId,Long candidateId);
	public boolean checkuserStatus(Long userId);
	public String checkAdminAuthentication(String email,String password);
	public List<List_of_vote> listOfVotes();
	public String passwordEncoder(String plainPassword);
		
}
