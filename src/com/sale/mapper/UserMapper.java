package com.sale.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sale.pojo.User;

public interface UserMapper {

	User loginck(@Param("username") String username,@Param("password") String password);

	User findById(@Param("u_id") String u_id);

	int updateOne(User user);

	int updatePwd(@Param("u_id") String u_id,@Param("u_pwd") String u_pwd);

	int countAllUser(@Param("map") Map<String, Object> map);

	List<User> findAllUser(@Param("start") int start,@Param("size") int size,@Param("map") Map<String, Object> map);

	int insertOne(User user);

	int adminUpdateOne(User user);

	int adminDeleteOne(@Param("u_id") String u_id);
	
}
