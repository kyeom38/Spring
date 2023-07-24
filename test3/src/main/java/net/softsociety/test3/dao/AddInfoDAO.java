package net.softsociety.test3.dao;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.test3.domain.AddInfo;

@Mapper
public interface AddInfoDAO 
{
	public int addinfo(AddInfo a);
}