package com.vot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vot.entities.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate,Long>{

}
