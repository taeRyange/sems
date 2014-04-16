package sems.users;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.UsersVo;
import dao.UsersDao;

@WebServlet("/users/list.bit")
@SuppressWarnings("serial")
public class UsersListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><title>사용자 목록</title></head>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"/home/common/list.css\">");
		/*out.println("<style> table{width:500px; text-align:center; font-size:25px; border: 2px red;} "
				+ "th{background-color:yellow;} "
				+ "td{background-color:#ffcccc;} </style>");*/

		out.println("<body bgcolor=' #4E0085'>");
		try {
			out.println("<h1>사용자 목록</h1>");

			UsersDao dao = (UsersDao) this.getServletContext().getAttribute(
					"usersDao");

			int pageNo = Integer.parseInt(request.getParameter("pageNo"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));

			List<UsersVo> list = dao.list(pageNo, pageSize);

			out.println("<input type='button' value='사용자등록' onclick=\"location.href='form.html'\"><br>");
			out.println("<table align='center' style='margin-top:100px'>");
			out.println("<tr>");
			out.println("	<th style='border-right :1px; margin-right:10px'>번호</th>");
			out.println("	<th>이름</th>");
			out.println("</tr>");

			for (UsersVo users : list) {
				out.println("<tr>");
				out.println("	<td style='border-right :1px solid black'> " + users.getNo() + "</td>");
				out.println("	<td><a href='detail.bit?no="
						+ users.getNo()
						+ "'>" + users.getName() + "</td>");
				out.println("</tr>");
			}
			out.println("</table>");
		} catch (Throwable e) {
			out.println("오류 발생 했음!");
			e.printStackTrace();
		}
		out.println("</body></html>");
	}
}
