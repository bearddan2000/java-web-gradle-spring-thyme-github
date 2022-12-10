package example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import example.model.Repo;
import example.service.DefaultService;

@RestController
@RequestMapping("/all")
public class DefaultRestController {

	@Autowired
	DefaultService defaultService;

	@RequestMapping(method=RequestMethod.GET)
	public Iterable<Repo> getAll(){
		return defaultService.getRepoList();
	}

}
