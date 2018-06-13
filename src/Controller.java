import java.io.IOException;
import java.sql.*;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import user.User;
import model.DBHelper;


@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource (name="jdbc/project")
	private DataSource dataSource;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page;
		if (request.getParameterMap().containsKey("page")) {
			page = request.getParameter("page");
		}else {
			page = "index";
		}
		
		switch (page) {
		case "list_users":
			listUsers(request,response);
			break;
		case "add_user":
			addUser(request,response);
			break;
		case "index":
			homepage(request,response);
			break;
		case "submitUser":
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			User user = new User(name,email);
			submitUser(user,request,response);
			break;
		case "updateUser":
			int user_id = Integer.parseInt(request.getParameter("user_id"));
			updateUser(user_id,request,response);
			break;
		case "deleteUser":
			int user_id1 = Integer.parseInt(request.getParameter("user_id"));
			deleteUser(user_id1,request,response);
			break;
		default:
			error(request,response);
			break;
		}
	}

	
	private void deleteUser(int user_id, HttpServletRequest request, HttpServletResponse response) {
		try {
			new DBHelper().deleteUser(user_id,dataSource);
			listUsers(request,response);
		} catch (ServletException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private void updateUser(int user_id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameterMap().containsKey("action")) {
			if(request.getParameter("action").equals("submit")) {
				//Update record code
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				User tempUser = new User(user_id,name,email);
				try {
					new DBHelper().updateUser(tempUser,dataSource);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("message","Record updated");
				request.getRequestDispatcher("message.jsp").forward(request, response);
			}
		}
		//Create a user reference
		User user = null;
		//Read user from DBHelper
		try {
			user = new DBHelper().getUser(user_id,dataSource);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//Set the attribute
		request.setAttribute("User", user);
		request.getRequestDispatcher("updateUser.jsp").forward(request, response);
	}


	private void submitUser(User user, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			new DBHelper().addUser(user,dataSource);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("submittedUser.jsp").forward(request, response);
	}


	private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> usersList = null;
		try {
			usersList = new DBHelper().getUsers(dataSource);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("users", usersList);
		request.getRequestDispatcher("list_users.jsp").forward(request, response);	
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("add_user.jsp").forward(request,response);		
	}

	private void homepage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("index.jsp").forward(request,response);
	}
	
	private void error(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("error.jsp").forward(request,response);
	}

}
