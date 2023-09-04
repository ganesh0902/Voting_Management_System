package com.vot.entities;

import java.util.Collection;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="User")
public class User{

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long uId;
		private String username;
		private String password;
		private String email;
		private String phone;
		private boolean status;
		private long candidateId;
		
		public User() {
			super();
			// TODO Auto-generated constructor stub
		}
		public User(long uId, String username, String password, String email, String phone, boolean status,long candidateId) {
			super();
			this.uId = uId;
			this.username = username;
			this.password = password;
			this.email = email;
			this.phone = phone;
			this.status = status;
			this.candidateId=candidateId;
		}
		public long getuId() {
			return uId;
		}
		public void setuId(long uId) {
			this.uId = uId;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public boolean isStatus() {
			return status;
		}
		public void setStatus(boolean status) {
			this.status = status;
		}		
		public long getCandidateId() {
			return candidateId;
		}
		public void setCandidateId(long candidateId) {
			this.candidateId = candidateId;
		}
		@Override
		public String toString() {
			return "User [uId=" + uId + ", username=" + username + ", password=" + password + ", email=" + email
					+ ", phone=" + phone + ", status=" + status + ", candidateId=" + candidateId + "]";
		}					
}