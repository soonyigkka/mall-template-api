package com.mall.template.teacher.service;

import com.mall.template.teacher.pojo.Teacher;

/**
 * teacher manage service
 * @author csu
 *
 */
public interface ITeacherService {

	/**
	 * 查询
	 * @param paramTeacher
	 * @return
	 */
	public Teacher queryTeacher(Teacher paramTeacher);
	
	/**
	 * save
	 * @param teacher
	 */
	public boolean saveTeacher(Teacher teacher);

	/**
	 * delete
	 * @param teacher
	 */
	public boolean delTeacher(long teacherId);
	
	/**
	 * update
	 * @param teacher
	 */
	public boolean updateTeacher(Teacher teacher);
	
}
