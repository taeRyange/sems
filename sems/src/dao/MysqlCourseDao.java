package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBConnectionPool;
import vo.CourseVo;

// SubjectVo의 setter/getter 사용
public class MysqlCourseDao implements CourseDao {
	DBConnectionPool dbConnectionPool;

	public void setDBConnectionPool(DBConnectionPool dbConnectionPool) {
		this.dbConnectionPool = dbConnectionPool;
	}
	
	public void insert(CourseVo course) throws Throwable {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = dbConnectionPool.getConnection();
			stmt = con.prepareStatement(
					"insert SE_COURS(TITLE, DEST, HOURS) values(?,?,?)");
			stmt.setString(1, course.getTitle());
			stmt.setString(2, course.getDescription());
			stmt.setInt(3, course.getHours());
			stmt.executeUpdate();
		} catch (Throwable e) {
			throw e;
		} finally { 
			try {stmt.close();} catch (Throwable e2) {}
			dbConnectionPool.returnConnection(con);
		}
	}
	
	public List<CourseVo> list(int pageNo, int pageSize) 
			throws Throwable {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = dbConnectionPool.getConnection();
			stmt = con.prepareStatement(
					"select CNO, TITLE from SE_COURS"
							+ " order by CNO desc"
							+ " limit ?, ?");
			stmt.setInt(1, (pageNo - 1) * pageSize);
			stmt.setInt(2, pageSize);
			rs = stmt.executeQuery();
			
			ArrayList<CourseVo> list = new ArrayList<CourseVo>();
			while(rs.next()) {
				list.add(new CourseVo()
													.setNo(rs.getInt("CNO"))
													.setTitle(rs.getString("TITLE")));
			}
			return list;
		} catch (Throwable e) {
			throw e;
		} finally { 
			try {rs.close();} catch (Throwable e2) {}
			try {stmt.close();} catch (Throwable e2) {}
			dbConnectionPool.returnConnection(con);
		}
	}
	
	public CourseVo detail(int no) throws Throwable {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = dbConnectionPool.getConnection();
			stmt = con.prepareStatement(
					"select CNO, TITLE, DEST, HOURS from SE_COURS"
							+ " where CNO=?");
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				return new CourseVo()
										.setNo(rs.getInt("CNO"))
										.setTitle(rs.getString("TITLE"))
										.setDescription(rs.getString("DEST"))
										.setHours(rs.getInt("HOURS"));
			} else {
				throw new Exception("해당 과목을 찾을 수 없습니다.");
			}
		} catch (Throwable e) {
			throw e;
		} finally { 
			try {rs.close();} catch (Throwable e2) {}
			try {stmt.close();} catch (Throwable e2) {}
			dbConnectionPool.returnConnection(con);
		}
	}
	
	public void update(CourseVo course) throws Throwable {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = dbConnectionPool.getConnection();
			stmt = con.prepareStatement(
					"update SE_COURS set"
							+ " TITLE=?" 
							+ ", DEST=?"
							+ ", HOURS=?"
							+ " where CNO=?");
			stmt.setString(1, course.getTitle());
			stmt.setString(2, course.getDescription());
			stmt.setInt(3, course.getHours());
			stmt.setInt(4, course.getNo());
			stmt.executeUpdate();
		} catch (Throwable e) {
			throw e;
		} finally { 
			try {stmt.close();} catch (Throwable e2) {}
			dbConnectionPool.returnConnection(con);
		}
	}
	
	public void delete(int no) throws Throwable {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = dbConnectionPool.getConnection();
			stmt = con.prepareStatement(
					"delete from SE_COURS where CNO=?"	);
			stmt.setInt(1, no);
			stmt.executeUpdate();
		} catch (Throwable e) {
			throw e;
		} finally { 
			try {stmt.close();} catch (Throwable e2) {}
			dbConnectionPool.returnConnection(con);
		}
	}
}



















