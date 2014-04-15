package sems;

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

@WebServlet(value = "/subject/delete.bit")
public class SubjectDelete extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Update</title></head><body>");

		try {
			out.println("<h1>과목삭제</h1>");
			SubjectDao dao = (SubjectDao)this.getServletContext().getAttribute("subjectDao");
			

	int no = Integer.parseInt(request.getParameter("no"));

			dao.delete(no);
			out.println("삭제성공!");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			out.println("오류발생");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			out.println("오류발생");
		}
		out.println("</body></html>");
	}

}
