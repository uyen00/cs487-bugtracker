package com.gheewhiz.util;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.gheewhiz.Account;


public class JspUtil {
	public static Account getAccount(HttpServletRequest request) {
		return (Account) request.getSession().getAttribute("account");
	}

	@SuppressWarnings("unchecked")
	public static <T> T getAttribute(ServletRequest request, String name, Class<T> clazz) {
		Object obj = request.getAttribute(name);
		if (clazz.isInstance(obj)) {
			return (T) obj;
		}
		return null;
	}
}
