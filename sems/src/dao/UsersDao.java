package dao;

import java.util.List;

import vo.UsersVo;

public interface UsersDao {
	void insert(UsersVo users) throws Throwable;
	List<UsersVo> list(int pageNo, int pageSize) throws Throwable;
	UsersVo detail(int no) throws Throwable;
	void update(UsersVo users) throws Throwable;
	void delete(int no) throws Throwable;
}
