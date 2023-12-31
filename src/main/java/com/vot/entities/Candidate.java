package com.vot.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Candidate")
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long cid;
	private String username;
	private int number_of_vote;
	private String city;
	private String contact;
	private String income;
	private String fileName;
	
	public Candidate() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Candidate(long cid, String username, int number_of_vote, String city, String contact, String income,
			String fileName) {
		super();
		this.cid = cid;
		this.username = username;
		this.number_of_vote = number_of_vote;
		this.city = city;
		this.contact = contact;
		this.income = income;
		this.fileName = fileName;
	}


	public Candidate(String username, int number_of_vote) {
			
		this.username = username;
		this.number_of_vote = number_of_vote;
	}
		
	public long getCid() {
		return cid;
	}
	public void setCid(long cid) {
		this.cid = cid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getNumber_of_vote() {
		return number_of_vote;
	}
	public void setNumber_of_vote(int number_of_vote) {
		this.number_of_vote = number_of_vote;
	}
	
	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}


	public String getIncome() {
		return income;
	}


	public void setIncome(String income) {
		this.income = income;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	@Override
	public String toString() {
		return "Candidate [cid=" + cid + ", username=" + username + ", number_of_vote=" + number_of_vote + ", city="
				+ city + ", contact=" + contact + ", income=" + income + ", fileName=" + fileName + "]";
	}		
}