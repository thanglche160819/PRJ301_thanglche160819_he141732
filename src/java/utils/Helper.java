/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Helper {

	/**
	 * Ensure that access only to authorized users
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @param minRole  minimum user's role to be passed
	 * @param maxRole  maximum user's role to be passed
	 * @param page     move to this page if user can not be passed
	 */
	public static boolean protectedRouter(HttpServletRequest request, HttpServletResponse response, int minRole,
			int maxRole, String page) throws Exception {

		if (!isLogin(request) || !correctRole(request, minRole, maxRole)) {
			request.setAttribute("errorMessage", "Action is not allow, please login first");
			request.getRequestDispatcher(page).forward(request, response);

			return false;
		}

		return true;
	}

	/**
	 * Check that user is login or not
	 *
	 * @param request servlet request
	 * @return true if logined false if not
	 */
	public static boolean isLogin(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return false;
		}
		String username = (String) session.getAttribute("username");

		return username != null;
	}

	/**
	 * Check that user's role is valid or invalid
	 *
	 * @param request servlet request
	 * @param minRole minimum user's role to be passed
	 * @param maxRole maximum user's role to be passed
	 * @return true if minimum role <= user's role <= maximum role
	 */
	public static boolean correctRole(HttpServletRequest request, int minRole, int maxRole) {
		HttpSession session = request.getSession(false);
		Integer roleR = (Integer) session.getAttribute("role");
		return roleR != null && roleR >= minRole && roleR <= maxRole;
	}

	/**
	 * convert data in String type into Integer
	 *
	 * @param date
	 * @return
	 */
	public static Integer convertStringDateToInteger(String date) {
		try {
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			Date dateTypeDate = formatter1.parse(date);
			SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMdd");
			return Integer.parseInt(formatter2.format(dateTypeDate));
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * Convert date in String type into date in Date type
	 *
	 * @param date
	 * @return date in Date type
	 */
	public static Date convertStringToDate(String date) {
		try {
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			return formatter1.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String convertDateToString(Date date) {
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		return formatter1.format(date);
	}

}
