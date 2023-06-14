package hw3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Group;
import models.Student;

@WebServlet("/NewStudent")
public class NewStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public NewStudent() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Database db = new Database();
		List<Group> groups = db.getGroups();
		request.setAttribute("groups", groups);
		request.getRequestDispatcher("/WEB-INF/NewStudent.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Database db = new Database();
		String name = request.getParameter("studentName");
		int age = 2022 - Integer.parseInt(request.getParameter("birthYear"));
		String parentName = request.getParameter("parentName");
		String email = request.getParameter("studentEmail");
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		db.addStudent(name, age, parentName, email, group_id);
		db.close();
		response.sendRedirect("ListStudents");
		
	}

}
