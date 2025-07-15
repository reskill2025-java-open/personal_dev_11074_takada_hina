package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Titles;

public interface TitlesRepository extends JpaRepository<Titles, Integer> {

	List<Titles> findByUserId(Integer userId);

	List<Titles> findByUserIdOrderById(Integer userId);

}
