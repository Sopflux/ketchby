package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ClassVO;

@Repository
public interface ClassDAO extends JpaRepository<ClassVO, Integer> {
	
	@Modifying
	@Query(value = "select clno, cltitle, cllevel, aid from class")
	public List<ClassVO> selectAll();
	
}