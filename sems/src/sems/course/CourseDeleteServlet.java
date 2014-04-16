package sems.course;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CourseDao;

@WebServlet("/course/delete.bit")
@SuppressWarnings("serial")
public class CourseDeleteServlet extends HttpServlet {
	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>과정삭제</title></head><body bgcolor='green', style='text-align:center'>");
		
		try {
			out.println("<h1>과정 삭제 결과</h1>");
			
			CourseDao dao = (CourseDao)this.getServletContext()
					.getAttribute("courseDao");
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			dao.delete(no);
			
			out.println("삭제 성공!");
			
		} catch (Throwable e) {
			out.println("오류 발생 했음!");
			e.printStackTrace();
		}
		out.println("</body></html>");
	}
}
















