

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addCart
 */
@WebServlet("/addCart")
public class addCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		response.setContentType("text/html, charset=utf-8");
		request.setCharacterEncoding("utf-8");
		Cookie cookies[] = request.getCookies();
		Cookie cookie = new Cookie("id"+(cookies.length+1),id);
		cookie.setMaxAge(20000);
		response.addCookie(cookie);
		
	}
}