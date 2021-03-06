package sems.subject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SubjectDao;
import util.DBConnectionPool;
import vo.SubjectVo;

@WebServlet(value = "/subject/insert.bit")
public class SubjectInsert extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

		//request.setCharacterEncoding("UTF-8");

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>과목등록</title></head><body>");

		try {
			out.println("<h1>과목등록결과</h1>");
			SubjectDao dao = (SubjectDao)this.getServletContext()
					.getAttribute("subjectDao");
			
			SubjectVo vo = new SubjectVo();
			vo.setTitle(request.getParameter("title"));
			vo.setDescription(request.getParameter("description"));

			dao.insert(vo);
			out.println("등록성공!");

			response.setHeader("Refresh", "1;url=list.bit?pageNo=1&pageSize=10");
			
		} catch (Throwable e) {
			out.println("오류발생");
			e.printStackTrace();
		
		}
		out.println("</body></html>");
	}

}
