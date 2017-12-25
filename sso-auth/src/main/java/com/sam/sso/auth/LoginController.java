package com.sam.sso.auth;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sam.sso.util.CookieUtil;
import com.sam.sso.util.JwtUtil;

/**
 * @author sumit
 *
 */
@Controller
public class LoginController {
	private static final String jwtTokenCookieName = "JWTTOKEN7891";
	private static final String signingKey = "signingKey";
	private static final Map<String, String> credentials = new HashMap<>();

	public LoginController() {
		credentials.put("sumit", "sumit");
		credentials.put("sapta", "sapta");
	}

	@RequestMapping("/")
	public String home() {
		return "redirect:/login";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(HttpServletResponse httpServletResponse, String username, String password, String redirect,
			Model model) {
		if (username == null || !credentials.containsKey(username) || !credentials.get(username).equals(password)) {
			model.addAttribute("error", "Invalid username or password!");
			return "login";
		}

		/* Addtional Data */
		Map<String, Object> usr_data = new HashMap<String, Object>();
		usr_data.put("username", username);
		usr_data.put("id", 7891);
		/* Addtional Data */

		String token = JwtUtil.generateToken(signingKey, username, usr_data);
		CookieUtil.create(httpServletResponse, jwtTokenCookieName, token, false, -1, "localhost");

		return "redirect:" + redirect;
	}
}