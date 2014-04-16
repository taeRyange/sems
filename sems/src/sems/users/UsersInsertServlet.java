package sems.users;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.UsersVo;
import dao.UsersDao;

@WebServlet("/users/insert.bit")
@SuppressWarnings("serial")
public class UsersInsertServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	
		//request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>유저등록</title></head><body bgcolor='#4E0085'style='text-align:center'>");
		
		try {
			out.println("<h1>유저등록 결과</h1>");
			
			UsersDao dao = (UsersDao)this.getServletContext()
					.getAttribute("usersDao");
			
			UsersVo vo = new UsersVo();
			vo.setEmail(request.getParameter("email"));
			vo.setPassword(request.getParameter("password"));
			vo.setName(request.getParameter("name"));
			vo.setTel(request.getParameter("tel"));
			vo.setFax(request.getParameter("fax"));
			vo.setPostno(request.getParameter("postno"));
			vo.setAddress(request.getParameter("addr"));
			vo.setPhotopath(request.getParameter("photopath"));
			
			dao.insert(vo);
			
			out.println("등록 성공!");
			
			response.setHeader("Refresh", "1;url=list.bit?pageNo=1&pageSize=10");
			
		} catch (Throwable e) {
			out.println("오류 발생 했음!");
			e.printStackTrace();
		}
		out.println("</body></html>");
	}
}

