package dao;

import java.util.List;

import vo.CourseVo;

public interface CourseDao {
	void insert(CourseVo course) throws Throwable;
	List<CourseVo> list(int pageNo, int pageSize) throws Throwable;
	CourseVo detail(int no) throws Throwable;
	void update(CourseVo course) throws Throwable;
	void delete(int no) throws Throwable;
}









