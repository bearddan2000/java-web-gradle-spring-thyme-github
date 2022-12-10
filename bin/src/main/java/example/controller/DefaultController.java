package example.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class DefaultController {

	final String title = "Github Repository Table";
	final String dataTable = "portalTable";
	final List<String> columnNames = Arrays.asList("Language", "Platform", "Build", "Name", "Description", "Keywords");

	@GetMapping("/")
	public String goHome(Model model){
		model.addAttribute("title", title);
		model.addAttribute("dataTable", dataTable);
		model.addAttribute("columnNames", columnNames);
    return "/index";
  }

  @GetMapping("/403")
  public String error403() {
      return "/error/403";
  }
}
