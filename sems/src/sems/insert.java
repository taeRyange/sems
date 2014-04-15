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

@WebServlet(value = "/subject/insert.bit")
public class insert extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		// 요청 정보에서 파라미터 값 꺼내기
		String title = request.getParameter("title");
		String desc = request.getParameter("dest");
		Connection con = null;

		DBConnectionPool dbConnectionPool = new DBConnectionPool();
		try {
			con = dbConnectionPool.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		PrintWriter out = response.getWriter();

		out.println("<html><head><title>Insert</title></head>");

		try {
			PreparedStatement stmt = con
			    .prepareStatement("insert SE_SUBJS(TITLE, DEST) values(?,?)");

			// - XXX는 파라미터의 타입니다.
			stmt.setString(1, title);
			stmt.setString(2, desc);

			stmt.executeUpdate();

			out.println("<body><h1>입력성공</h1>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			out.println("<body><h1>입력실패</h1>");
			System.out.println(e);
		}

		// 클라이언트에게 출력하기
		out.println("</body></html>");

	}

}
