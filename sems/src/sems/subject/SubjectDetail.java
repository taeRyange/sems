package sems.subject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SubjectDao;
import util.DBConnectionPool;
import vo.SubjectVo;

@WebServlet(value = "/subject/detail.bit")
public class SubjectDetail extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Detail</title></head><body>");

		try {
			out.println("<h1>과목 목록</h1>");
			SubjectDao dao = (SubjectDao)this.getServletContext().getAttribute("subjectDao");
			

			int no = Integer.parseInt(request.getParameter("no"));

			SubjectVo subject = dao.detail(no);

			out.println("	<table border ='1'>");
			out.println("	<tr> <th>번호</td><th> "+subject.getNo()+"</td></tr>");

			out.println("	<tr> <th>과목명</th><td>" + subject.getTitle() + "</td></tr>");
			
			out.println("	<tr> <th>내용</th><td><textarea rows='5' cols='60' readonly>" + subject.getDescription() + "</textarea></td></tr>");

		} catch (Exception e) {
			// TODO: handle exception
			out.println("오류발생");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println("오류발생");
		}
		out.println("</table></body></html>");
	}

}
