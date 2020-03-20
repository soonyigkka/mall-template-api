package com.mall.template.teacher.dao;

import org.apache.ibatis.annotations.Param;

import com.mall.template.teacher.pojo.Teacher;

public interface ITeacherDao {

	/**
	 * 查询
	 * @param paramTeacher
	 * @return
	 */
	public Teacher queryTeacher(@Param("teacher")Teacher paramTeacher);
	
	/**
	 * save
	 * @param teacher
	 */
	public int saveTeacher(@Param("teacher") Teacher teacher);

	/**
	 * delete
	 * @param teacher
	 */
	public int delTeacher(long teacherId);
	
	/**
	 * update
	 * @param teacher
	 */
	public int updateTeacher(Teacher teacher);
}
