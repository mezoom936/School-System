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

@WebServlet("/Edit")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Edit() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		int id = Integer.parseInt(request.getParameter("id"));
		Database db = new Database();
		List<Group> groups = db.getGroups();
		Student student = db.getStudent(id);
		request.setAttribute("student", student);
		request.setAttribute("groups", groups);
		request.getRequestDispatcher("/WEB-INF/Edit.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("studentName");
		int age = 2022 - Integer.parseInt(request.getParameter("birthYear"));
		String parentName = request.getParameter("parentName");
		String email = request.getParameter("studentEmail");
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		Database db = new Database();	
		Student student = db.getStudent(id);
		db.edit(id, name, age, parentName, email, group_id);
		db.close();
		response.sendRedirect("ListStudents");
	}

}
