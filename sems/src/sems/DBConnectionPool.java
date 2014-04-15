package sems;
//주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석주석
//test 현주 
/*
 * 후후~ 충돌이다!!!!!!!!!!!!!!!!!!!!!!! CRUSH~~!!!!!!!!!!
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

// Pooling 기법
// - 적은 자원을 효율적으로 사용
// - 미리 자원을 준비한 후 필요한 곳에 빌려줌
// - 자원을 사용하고 난 다음에는 반납함.

// DB Connection 풀링
// - Connection이 필요할 때 생성 후 대여해 줌
// - 사용 후 닫지 않고 반납함.
// - 기존 커넥션이 있으면 기존 커넥션을 대여해줌.
// => DBMS와 연결하는 시간을 줄일수있다.
 class DBConnectionPool {

	ArrayList<Connection> list = new ArrayList<Connection>();

	public Connection getConnection() throws Exception {
		if (list.size() > 0) {
			return list.remove(0); // remove는 0번째것을 리턴하면서 지운다.
		}

		Class.forName("com.mysql.jdbc.Driver");

		return DriverManager.getConnection("jdbc:mysql://localhost:3306/studydb",
		    "study", "study");

	}

	public void returnConnection(Connection con) {
		try {
			if (!con.isClosed()) {
				list.add(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		}

	}

	public void closeAll() {
		for (Connection con : list) {
			try {	con.close();	} catch (Exception e) {	}
			System.out.println("Bye~!! 꽝~");
		}
	}

}
