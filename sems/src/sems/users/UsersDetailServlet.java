package sems.users;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.CourseVo;
import vo.UsersVo;
import dao.CourseDao;
import dao.UsersDao;

@WebServlet("/users/detail.bit")
@SuppressWarnings("serial")
public class UsersDetailServlet extends HttpServlet {
	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>사용자 상세정보</title></head>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"/sems/common/list.css\">");

		out.println("<body bgcolor='#4E0085'>");
		try {
			out.println("<h1>사용자 상세정보</h1>");
			
			UsersDao dao = (UsersDao)this.getServletContext()
					.getAttribute("usersDao");
			
			int no = Integer.parseInt(request.getParameter("no")); 
			
			UsersVo users = dao.detail(no);
			
			out.println("<table align='center' style='margin-top:100px'>");
			out.println("<tr>");
			out.println("	<th>번호</th>");
			out.println("	<td>" + users.getNo() + "</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("	<th>이메일</th>");
			out.println("	<td>" + users.getEmail() + "</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("	<th>이름</th>");
			out.println("	<td>" + users.getName() + "</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("	<th>전화번호</th>");
			out.println("	<td>" + users.getTel() + "</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("	<th>주소</th>");
			out.println("	<td>" + users.getAddress() + "</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("	<th>사진</th>");
			out.println("	<td>" + users.getPhotopath() + "</td>");
			out.println("</tr>");
	
			out.println("</table>");
			
			out.println("<input id='listBtn' type='button' value='목록' onclick=\"location.href='list.bit?pageNo=1&pageSize=10'\">");
			out.println("<input type='button' value='삭제' onclick=\"location.href=\'delete.bit?no="+users.getNo()+"\'\">");
			out.println("<input type='button' value='변경' onclick=\"location.href=\'update.bit?no="+users.getNo()+"\'\">");

			
			out.println("</table>");
		} catch (Throwable e) {
			out.println("오류 발생 했음!");
			e.printStackTrace();
		}
		out.println("</body></html>");
	}
}


