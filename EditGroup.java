package hw3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Group;
import models.Student;

@WebServlet("/EditGroup")
public class EditGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditGroup() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int idg = Integer.parseInt(request.getParameter("idg"));
		Database db = new Database();
		Group group = db.getGroup(idg);
		List<Student> members = db.getMembers(idg);		
		request.setAttribute("group", group);	
		request.setAttribute("members", members);	
		db.close();
		request.getRequestDispatcher("/WEB-INF/EditGroup.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String groupName = request.getParameter("groupName");
		int idg = Integer.parseInt(request.getParameter("idg"));
		Database db = new Database();
		Group group = db.getGroup(idg);
		db.editGroup(idg, groupName);
		db.close();
		response.sendRedirect("Groups");
	}

}
