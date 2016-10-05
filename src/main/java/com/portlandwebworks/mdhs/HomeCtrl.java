package com.portlandwebworks.mdhs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author nick
 */
@Controller
@RequestMapping("/")
public class HomeCtrl {
	
	@RequestMapping(method = RequestMethod.GET)
	public String redirect(){
		return "/index";
	}
	
}
