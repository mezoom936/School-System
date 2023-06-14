package hw3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Group;

@WebServlet("/NewGroup")
public class NewGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public NewGroup() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.getRequestDispatcher("/WEB-INF/NewGroup.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Database db = new Database();
		List<Group> groups = db.getGroups();
		String groupName = request.getParameter("groupName");
		db.addGroup(groupName);
		db.close();
		response.sendRedirect("Groups");
	}

}
