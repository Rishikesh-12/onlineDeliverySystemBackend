package com.cognizant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.model.MenuItem;

public interface MenuRepository extends JpaRepository<MenuItem, Integer> {

}
