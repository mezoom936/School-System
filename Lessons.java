package hw3;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Lesson;

@WebServlet("/Lessons")
public class Lessons extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Lessons() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Database db = new Database();
		List<Lesson> lessonsGroup = db.getLessonsGroup();
		List<Lesson> lessonsStudent = db.getLessonsStudent();
		request.setAttribute("lessonsGroup", lessonsGroup);
		request.setAttribute("lessonsStudent", lessonsStudent);
		db.close();
		request.getRequestDispatcher("/WEB-INF/ListLessons.jsp").forward(request, response);	
	}


}
