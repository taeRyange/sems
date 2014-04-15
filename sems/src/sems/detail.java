package sems;

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

@WebServlet(value = "/subject/detail.bit")
public class detail extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		Connection con = null;

		DBConnectionPool dbConnectionPool = new DBConnectionPool();
		try {
			con = dbConnectionPool.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<html><head><title>Detail</title></head>");

		try {

			PreparedStatement stmt = con
			    .prepareStatement("select  SNO,TITLE, DEST from SE_SUBJS"
			        + " where SNO=?");

			stmt.setInt(1, no);

			ResultSet rs = stmt.executeQuery();

			rs.next();

			out.println("<body><h1>과목 상세정보</h1>");

			out.println("<table border=1px solid ><tr><th>번호</th><td>"
			    + rs.getInt("SNO") + "</td></tr>");
			out.println("<tr><th>과목명</t><td>" + rs.getString("TITLE") + "</td></tr>");
			out.println("<tr><th>내용</t><td><textarea rows='5' cols='30'>"
			    + rs.getString("DEST") + "</textarea></td></tr>");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			out.println("<body><h1>해당과목이 없습니다</h1>");
			System.out.println(e);
		}

		// 클라이언트에게 출력하기
		out.println("</table></body></html>");

	}

}
