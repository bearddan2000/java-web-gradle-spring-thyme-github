package example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

import example.model.Repo;
import example.model.filter.*;
import example.postconstruct.Popdata;

@Service
public class DefaultService {

  	@Autowired
  	List<IFilter> filterList;

  	List<Repo> repoList;

  	@PostConstruct
  	private void init()
  	{
  		Popdata data = new Popdata();
  		data.readPortalFile();
  		repoList = data.repositoryList(filterList);
  	}

    public List<Repo> getRepoList(){ return repoList; }

}
