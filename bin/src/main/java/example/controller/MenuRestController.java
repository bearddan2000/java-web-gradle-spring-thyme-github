package example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import example.model.menu.*;
import example.service.DefaultService;
import example.service.MenuService;

@RestController
@RequestMapping("/menu")
public class MenuRestController {

	@Autowired
	DefaultService defaultService;

	@Autowired
	MenuService menuService;

	@RequestMapping(path="/lang", method=RequestMethod.GET)
	public Iterable<LanguageMenu> getLanguageMenuItems(){
		return menuService.getLanguageMenuItems(defaultService.getRepoList());
	}

	@RequestMapping(path="/tech", method=RequestMethod.GET)
	public Iterable<TechMenu> getTechMenuItems(){
		return menuService.getTechMenuItems(defaultService.getRepoList());
	}
}
