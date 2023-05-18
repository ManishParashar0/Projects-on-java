package com.manish.madico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.manish.madico.controller.User;
import com.manish.madico.entity.UserEntity;

public class ServiceMedico {
	private static Connection con;

	public static boolean login(UserEntity bean, Connection connection, HttpServletRequest req) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet rs1 = statement.executeQuery("select * from NEWMEDICO where username='"+bean.getUsername()+"'");
		System.out.println(bean.getUsername());
		System.out.println(bean.getImage_name());
		while (rs1.next()) {
			String Username1 = rs1.getString(2);
			String image = rs1.getString(8);
			if (Username1.equals(bean.getUsername()) ) {
				System.out.println(Username1);
				System.out.println("++++++++++++++++++++++++++++++");
				System.out.println(image);
				req.setAttribute("profilename", image);
			}}
			
		boolean returntype = false;
		ResultSet rs = statement.executeQuery("select * from NEWMEDICO");
		System.out.println(bean.getUsername());
		System.out.println(bean.getPassword());
		while (rs.next()) {
			
			String Username = rs.getString(2);
			String password = rs.getString(6);
			if (Username.equals(bean.getUsername()) && password.equals(bean.getPassword())) {
				returntype = true;
				System.out.println(Username);
				System.out.println(password);
			}
			con = connection;
		}
		System.out.println(returntype + "heiiiiii");
		return returntype;

	}

	public static boolean register(UserEntity bean, Connection connection) throws SQLException {
		System.out.println("hello register");
		PreparedStatement statement = connection.prepareStatement(
				"insert into newmedico(username,name,phone,email,password,profiletype,imagename) values(?,?,?,?,?,?,?)");
		/*
		 * System.out.println(bean.getUsername()); System.out.println(bean.getName());
		 * System.out.println(bean.getPhone()); System.out.println(bean.getEmail());
		 * System.out.println(bean.getPassword());
		 */
		boolean retuntype = false;
		statement.setString(1, bean.getUsername());
		statement.setString(2, bean.getName());
		statement.setString(3, bean.getPhone());
		statement.setString(4, bean.getEmail());
		statement.setString(5, bean.getPassword());
		statement.setString(6, bean.getProfiletype());
		statement.setString(7, bean.getImage_name());
		int executeQuery = statement.executeUpdate();
		System.out.println(executeQuery);
		if (executeQuery <= 1) {
			retuntype = true;
		}
		con = connection;
		return retuntype;

	}

	static public boolean profile(String imagename, HttpServletRequest req, String name) throws SQLException {
		System.out.println(con);
		String value = null;
		HttpSession session = req.getSession(false);
		value = (String) session.getAttribute("uname");

		System.out.println(value);
		System.out.println(name);
		System.out.println(imagename);
		Boolean type = false;
		PreparedStatement statement = con.prepareStatement("UPDATE newmedico SET username=?, imagename=? WHERE name=?");

		statement.setString(1, name);
		statement.setString(2, imagename);
		statement.setString(3, value);
		int i = statement.executeUpdate();
		System.out.println(i);
		if (i <= 1) {
			type = true;
		}
		return type;

	}

	static public List<UserEntity> getdata() throws SQLException {
		System.out.println(con);
		List<UserEntity> list = new ArrayList<>();
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery("select * from NEWMEDICO");
		while (rs.next()) {
			UserEntity entity = new UserEntity();
			entity.setUsername(rs.getString(2));
			entity.setName(rs.getString(3));
			entity.setPhone(rs.getString(4));
			entity.setEmail(rs.getString(5));
			entity.setProfiletype(rs.getString(7));
			entity.setImage_name(rs.getString(8));
			list.add(entity);
		}
		return list;

	}

}
