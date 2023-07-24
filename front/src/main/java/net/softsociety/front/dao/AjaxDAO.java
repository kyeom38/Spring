package net.softsociety.front.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AjaxDAO {

	//추천수 수정 
	int updateCnt(int boardnum);

	int selectCnt(int boardnum);

	int idcheck2(String memberid);

}
