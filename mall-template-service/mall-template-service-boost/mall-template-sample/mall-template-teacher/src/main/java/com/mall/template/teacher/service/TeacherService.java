package com.mall.template.teacher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mall.template.teacher.dao.ITeacherDao;
import com.mall.template.teacher.pojo.Teacher;

@Service
public class TeacherService implements ITeacherService {

	@Autowired
	private ITeacherDao teacherDao;

	@Override
	public Teacher queryTeacher(Teacher paramTeacher) {
		return teacherDao.queryTeacher(paramTeacher);
	}

	@Override
	public boolean saveTeacher(Teacher teacher) {
		return teacherDao.saveTeacher(teacher) == 1 ? true : false;
	}

	@Override
	public boolean delTeacher(long teacherId) {
		return teacherDao.delTeacher(teacherId) == 1 ? true : false;
	}

	@Override
	public boolean updateTeacher(Teacher teacher) {
		return teacherDao.updateTeacher(teacher) == 1 ? true : false;
	}

}
