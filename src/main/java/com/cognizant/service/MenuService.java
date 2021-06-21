package com.cognizant.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cognizant.model.MenuItem;
import com.cognizant.repository.MenuRepository;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class MenuService {

	private final MenuRepository menuRepository;

	public List<MenuItem> getMenuList() {
		List<MenuItem> menuList = menuRepository.findAll();
		return menuList;
	}

	@Transactional
	public void addMenuItem(MenuItem menuItem) {
		menuRepository.save(menuItem);
		log.info("added menu item");
	}
	
	
}
