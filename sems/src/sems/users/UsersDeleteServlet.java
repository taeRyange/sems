package sems.users;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsersDao;

@WebServlet("/users/delete.bit")
@SuppressWarnings("serial")
public class UsersDeleteServlet extends HttpServlet {
	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>사용자 삭제</title></head><body bgcolor='#4E0085', style='text-align:center'>");
		
		try {
			out.println("<h1>사용자 삭제 결과</h1>");
			
			UsersDao dao = (UsersDao)this.getServletContext()
					.getAttribute("usersDao");
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			dao.delete(no);
			
			out.println("삭제 성공!");
			
			response.sendRedirect("list.bit?pageNo=1&pageSize=10");
			
		} catch (Throwable e) {
			out.println("오류 발생 했음!");
			e.printStackTrace();
		}
		out.println("</body></html>");
	}
}
















