package pl.magisterka.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorControllerImpl implements ErrorController{
	
	private static final String PATH = "/error";

	@RequestMapping(value = PATH)
	public String error() {
		return "fragments/error";
	}
	
	
	@Override
	public String getErrorPath() {
		return PATH;
	}

}
