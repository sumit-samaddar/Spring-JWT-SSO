package com.sam.sso.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sam.sso.util.CookieUtil;

/**
 * @author sumit
 *
 */
@Controller
public class ResourceController {
	
	/*JWT Token Cookie Name*/
	private static final String jwtTokenCookieName = "JWTTOKEN7891";

	@RequestMapping("/")
	public String home() {
		return "redirect:/protected-resource";
	}

	@RequestMapping("/protected-resource")
	public String protectedResource() {
		return "protected-resource";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletResponse httpServletResponse) {
		CookieUtil.clear(httpServletResponse, jwtTokenCookieName);
		return "redirect:/";
	}
}