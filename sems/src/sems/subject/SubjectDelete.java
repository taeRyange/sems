package sems.subject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MysqlSubjectDao;
import dao.SubjectDao;
import util.DBConnectionPool;
import vo.SubjectVo;

/* Refresh
 * - 일정시간 후 서버에 지정된 URL을 요청하게 만듬.
 * - 1) 응답 헤더에 리프래시 정보 심기
 * - 2) HTML 헤더에 리프래시 정보 심기
 * 
 * Redirect
 * - 클라이언트에게 다시 요청할 주소를 알려줌.
 * - 경과시간 지정 불가!
 * - 콘텐츠를 보내지 않는다.
 */

@WebServlet("/subject/delete.bit")
@SuppressWarnings("serial")
public class SubjectDelete extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// POST 요청의 값에 대해 적용. GET요청의 값은?
		// - 서블릿 컨테이너의 안내에 따라 설정한다.
		// - getParameter()를 호출하기 전에 실행해야 한다.
		// 단, 한번이라도 getParameter()를 호출했다면 적용안됨.
		
	  // CharacterEncoding으로 대체함
		//request.setCharacterEncoding("UTF-8");
		
		// 1) 응답 헤더에 Refresh 명령어 심기 
		//response.setHeader("Refresh", "1;url=list.bit?pageNo=1&pageSize=10");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>과목삭제</title>");
		
		// 2) 응답 내용에 Refresh 명령어 심기 
		//out.println("<meta http-equiv='Refresh'"
		//		+ " content='1;url=list.bit?pageNo=1&pageSize=10'>"); 
		out.println("</head><body>");

		try{
			out.println("<h1>과목 삭제 결과</h1>");
			SubjectDao dao = (SubjectDao)this.getServletContext()
					 .getAttribute("subjectDao");
			
			SubjectVo vo = new SubjectVo();
			int no = Integer.parseInt(request.getParameter("no"));
		  
		  dao.delete(no);

			out.println("삭제성공");
			
			// 3) Redirect 처리
			// - 콘텐츠를 출력하지 않기 떄문에 => 이전에 출력한 내용은 취소된다.
			response.sendRedirect("list.bit?pageNo=1&pageSize=10");
			
		
		}catch(Throwable e){
			out.println("오류발생");
			e.printStackTrace();
		}
		out.println("</body></html>");
	}
}
