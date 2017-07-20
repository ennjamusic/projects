

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBCon;
/**
 * Servlet implementation class AddEmailTo
 */
public class AddEmailTo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String data;
	private DBCon db;
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("name");
		String pass = request.getParameter("pass");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String query = "INSERT INTO e-mails (mail_name, password)"
				+ " VALUES ("+email+", "+pass+")";
		try{
			db = new DBCon();
			db.getCon();
			if (db.update(query)==1){
				data = "успешна";
			}
			else{
				data = "не успешна";
			}
			out.println(data);
			db.getCon().close();
			db = null;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
