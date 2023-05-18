package com.manish.madico.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manish.madico.dao.ServiceMedico;
import com.manish.madico.entity.UserEntity;

@WebServlet("/myprofile")
public class Myprofile extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("uname");
		System.out.println(name + "sts");
		try {
			List<UserEntity> getdata = ServiceMedico.getdata();
			for (Iterator iterator = getdata.iterator(); iterator.hasNext();) {

				UserEntity userEntity = (UserEntity) iterator.next();
				if (userEntity.getUsername().equals(name)) {
					req.setAttribute("userdetails", userEntity);
					System.out.println(userEntity);
				}

			}

			RequestDispatcher dispatcher = req.getRequestDispatcher("/Profile.jsp");
			dispatcher.forward(req, resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
