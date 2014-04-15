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

@WebServlet(value = "/subject/delete.bit")
public class delete extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// 요청 정보에서 파라미터 값 꺼내기
		int no = Integer.parseInt(request.getParameter("no"));

		Connection con = null;

		DBConnectionPool dbConnectionPool = new DBConnectionPool();
		try {
			con = dbConnectionPool.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		PrintWriter out = response.getWriter();

		out.println("<html><head><title>Delete</title></head>");

		try {
			PreparedStatement stmt = con
			    .prepareStatement("delete from SE_SUBJS where SNO=?");

			stmt.setInt(1, no);

			stmt.executeUpdate();

			out.println("<body><h1>삭제성공</h1>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			out.println("<body><h1>삭제실패</h1>");
			System.out.println(e);
		}

		// 클라이언트에게 출력하기
		out.println("</body></html>");

	}

}
