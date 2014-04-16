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
import dao.SubjectDao;

@WebServlet("/users/update.bit")
@SuppressWarnings("serial")
public class UsersUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>사용자 변경</title></head><body style='text-align:center'>");
		out.println("<link rel='stylesheet' type='text/css' href='/home/common/form.css'>");
		try {
			int no = Integer.parseInt(request.getParameter("no"));
			UsersDao dao = (UsersDao)this.getServletContext()
					.getAttribute("usersDao");

			UsersVo vo = dao.detail(no);

			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset='UTF-8'>");
			out.println("<title>사용자 변경폼</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>사용자 입력</h1>");
			out.println("<form action='update.bit' method='post'>");
			out.println("<label>번호:</label><input type='text' name='no' value='"
					+ vo.getNo()
					+ "' readonly><br>");
			out.println("<label>이메일:</label><input type='text' name='email' value='"
					+ vo.getEmail()
					+ "'><br>");
			out.println("<label>암호:</label><input type='password' name='password' value='"
					+ vo.getPassword()
					+ "'><br>");
			out.println("<label>이름:</label><input type='text' name='name' value='"
					+ vo.getName()
					+ "'><br>");
			out.println("<label>전화번호:</label><input type='text' name='tel' value='"
					+ vo.getTel()
					+ "'><br>");
			out.println("<label>팩스번호:</label><input type='text' name='fax' value='"
					+ vo.getFax()
					+ "'><br>");
			out.println("<label>우편번호:</label><input type='text' name='postno' value='"
					+ vo.getPostno()
					+ "'><br>");
			out.println("<label>주소:</label><input type='text' name='addrs' value='"
					+ vo.getAddress()
					+ "'><br>");
			out.println("<label>사진주소:</label><input type='text' name='photopath' value='"
					+ vo.getPhotopath()
					+ "'><br>");
			out.println("<input type='submit' value='변경'>");
			out.println("<input type='reset' value='취소' onclick=\"location.href='detail.bit?no="
					+ vo.getNo()
					+ "'\">");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");

		}catch(Throwable e){
			out.println("오류발생");
			e.printStackTrace();
		}
		out.println("</body></html>");
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException ,IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>과정등록</title></head><body>");

		try{
			out.println("<h1>과정 변경 결과</h1>");
			UsersDao dao = (UsersDao)this.getServletContext()
					.getAttribute("usersDao");

			UsersVo vo = new UsersVo();
			vo.setNo(Integer.parseInt(request.getParameter("no")));
			vo.setEmail(request.getParameter("email"));
			vo.setPassword(request.getParameter("password"));
			vo.setName(request.getParameter("name"));
			vo.setTel(request.getParameter("tel"));
			vo.setFax(request.getParameter("fax"));
			vo.setPostno(request.getParameter("postno"));
			vo.setAddress(request.getParameter("addrs"));
			vo.setPhotopath(request.getParameter("photopath"));
			

			dao.update(vo);

			out.println("변경 성공!");

			response.sendRedirect("detail.bit?no=" + vo.getNo());

		} catch (Throwable e) {
			out.println("오류 발생 했음!");
			e.printStackTrace();
		}
		out.println("</body></html>");
	}
}	





















