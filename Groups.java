package hw3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

@WebServlet("/Groups")
public class Groups extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public Groups() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Database db = new Database();
		request.setAttribute("groups", db.getGroups());
		db.close();
		request.getRequestDispatcher("/WEB-INF/Groups.jsp").forward(request, response);	
	}

}
