package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBConnectionPool;
import vo.SubjectVo;

/* 1) DB 커넥션 풀 사용
 *  2) DI(Dependency Injection) : 의존객체를 외부에서 주입받기
 *  		- 의존객체를 주입할 setXXX() 메소드를 준비한다.
 *  		- setXXX() : 셋터(setter)
 */

public class MysqlSubjectDao implements SubjectDao {
	DBConnectionPool dbConnectionPool;

	// 외부에서 DBConnectionPool 객체를 주입할 수 있도록 함
	public void setDBConnectionPool(DBConnectionPool dbConnectionPool) {
		this.dbConnectionPool = dbConnectionPool;
	}

	PreparedStatement stmt = null;

	public void insert(SubjectVo subject) throws Throwable {
		Connection con = null;
		try {
			con = dbConnectionPool.getConnection();
			stmt = con.prepareStatement("insert SE_SUBJS(TITLE, DEST) values(?, ?)");
			stmt.setString(1, subject.getTitle());
			stmt.setString(2, subject.getDescription());
			stmt.executeUpdate();

		} catch (Throwable e) {
			throw e;

		} finally { // try.. catch 블록을 나가기 전에 반드시 수행되는 블록
			// try 블록에서 준비한 작업을 마무리하는 명령어를 놓는다.
			try {
				stmt.close();
			} catch (Throwable e2) {
			}
			dbConnectionPool.returnConnection(con);
		}
	}

	public List<SubjectVo> list(int pageNo, int pageSize) throws Throwable {
		ResultSet rs = null;
		Connection con = null;
		try {
			con = dbConnectionPool.getConnection();
			stmt = con.prepareStatement("select SNO, TITLE from SE_SUBJS"
			    + " order by SNO desc" + " limit ?, ?");

			stmt.setInt(1, (pageNo - 1) * pageSize);
			stmt.setInt(2, pageSize);

			rs = stmt.executeQuery();

			ArrayList<SubjectVo> list = new ArrayList<SubjectVo>();
			SubjectVo subject = null;

			while (rs.next()) {

				list.add( new SubjectVo().setNo( rs.getInt("SNO")).setTitle(rs.getString("TITLE")));

			}
			return list;

		} catch (Throwable e) {
			throw e;

		} finally {
			try {
				rs.close();
			} catch (Throwable e2) {
			}
			try {
				stmt.close();
			} catch (Throwable e2) {
			}
			dbConnectionPool.returnConnection(con);
		}
	}

	public SubjectVo detail(int no) throws Throwable {
		ResultSet rs = null;
		Connection con = null;
		try {
			con = dbConnectionPool.getConnection();
			stmt = con.prepareStatement("select SNO, TITLE, DEST from SE_SUBJS"
			    + " where SNO=?");

			stmt.setInt(1, no);

			rs = stmt.executeQuery();

			if (rs.next()) {
			return new SubjectVo().setNo( rs.getInt("SNO")).setTitle(rs.getString("TITLE")).setDescription(rs.getString("DEST"));
			} else {
				throw new Exception("해당 과목을 찾을 수 없습니다.");
			}

		} catch (Throwable e) {
			throw e;

		} finally {
			try {
				rs.close();
			} catch (Throwable e2) {
			}
			try {
				stmt.close();
			} catch (Throwable e2) {
			}
			dbConnectionPool.returnConnection(con);
		}
	}

	public void update(SubjectVo subject) throws Throwable {
		Connection con = null;
		try {
			con = dbConnectionPool.getConnection();
			stmt = con.prepareStatement("update SE_SUBJS set" + " TITLE=?"
			    + ", DEST=?" + " where SNO=?");

			stmt.setString(1, subject.getTitle());
			stmt.setString(2, subject.getDescription());
			stmt.setInt(3, subject.getNo());

			stmt.executeUpdate();

		} catch (Throwable e) {
			throw e;

		} finally {
			try {
				stmt.close();
			} catch (Throwable e2) {
			}
			dbConnectionPool.returnConnection(con);
		}
	}

	public void delete(int no) throws Throwable {
		Connection con = null;
		try {
			con = dbConnectionPool.getConnection();
			stmt = con.prepareStatement("delete from SE_SUBJS where SNO=?");
			stmt.setInt(1, no);
			stmt.executeUpdate();
		} catch (Throwable e) {
			throw e;
		} finally {
			try {
				stmt.close();
			} catch (Throwable e2) {
			}
			dbConnectionPool.returnConnection(con);

		}
	}

}
