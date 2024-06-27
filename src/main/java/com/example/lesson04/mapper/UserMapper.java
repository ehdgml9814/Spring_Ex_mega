package com.example.lesson04.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.lesson04.domain.User;

@Mapper
public interface UserMapper {

	public int insertUser(
			@Param("name") String name, 
			@Param("yyyymmdd") String yyyymmdd, // 리턴타입 자유
			@Param("email") String email, 
			@Param("introduce") String introduce);
	
	public User selectLatestUser();
	
	public boolean isDuplicationByName(String name);
}
