package hw3;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteGroup")
public class DeleteGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteGroup() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int idg = Integer.parseInt(request.getParameter("idg"));
		Database db = new Database();
		db.deleteGroup(idg);
		db.close();
		response.sendRedirect("Groups");
		
	}


}
