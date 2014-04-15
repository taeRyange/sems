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

@WebServlet(value = "/subject/list.bit")
public class list extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		// 요청 정보에서 파라미터 값 꺼내기
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
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

		out.println("<html><head><title>List</title></head>");
		out.println("<style>body{background:yellow}</style>");
		out.println("<body><h1>과목정보</h1>");
		out.println("<table border = 1px solid><tr><th>번호</th><th>제목</th></tr>");

		try {

			PreparedStatement stmt = con
			    .prepareStatement("select SNO, TITLE, DEST from SE_SUBJS"
			        + " order by SNO " + " limit ?,?");

			stmt.setInt(1, (pageNo - 1) * pageSize);
			stmt.setInt(2, pageSize);

			ResultSet rs = stmt.executeQuery();

			// 서버에 결과요청하기
			while (rs.next()) {
				out.println("<tr><td>" + rs.getInt("SNO") + "</td><td>" + rs.getString("TITLE") + "</td></tr>");
			}
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

		// 클라이언트에게 출력하기
		out.println("</table></body></html>");

	}

}
