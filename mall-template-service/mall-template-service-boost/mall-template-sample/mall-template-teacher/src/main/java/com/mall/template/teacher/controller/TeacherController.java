package com.mall.template.teacher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mall.template.teacher.pojo.Teacher;
import com.mall.template.teacher.service.ITeacherService;
import com.mall.templte.basic.response.helper.ResponseHelper;
import com.mall.templte.basic.response.pojo.ResponseInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="教师管理 restful sample")
@RestController
public class TeacherController {

	@Autowired
	private ITeacherService teacherService;

	@ApiOperation(value = "teacher query", httpMethod = "GET", notes = "query teacher by teacher-id")
	@RequestMapping(method = RequestMethod.GET, value = "/teacher-info/{teacherId}")
	public ResponseInfo<Teacher> queryTeacher(@PathVariable("teacherId")long teacherId) throws Exception {
		return ResponseHelper
				.productSuccessResponse(teacherService.queryTeacher(new Teacher().setTeacherIdSelfCall(teacherId)));
	}

	@ApiOperation(value = "Teacher insert", httpMethod = "POST", notes = "insert Teacher")
	@RequestMapping(method = RequestMethod.POST, value = "/teacher-info")
	public ResponseInfo<?> insertTeacher(@Validated Teacher teacher) throws Exception {
		return ResponseHelper.productSuccessResponse(teacherService.saveTeacher(teacher));
	}
	
	@ApiOperation(value = "Teacher delete", httpMethod = "DELETE", notes = "delete Teacher")
	@RequestMapping(method = RequestMethod.DELETE, value = "/teacher-info/{teacherId}")
	public ResponseInfo<?> deleteTeacher(@PathVariable("teacherId") Teacher teacher) throws Exception {
		return ResponseHelper.productSuccessResponse(teacherService.saveTeacher(teacher));
	}
	
	@ApiOperation(value = "Teacher update", httpMethod = "PUT", notes = "update Teacher")
	@RequestMapping(method = RequestMethod.PUT, value = "/teacher-info")
	public ResponseInfo<?> updateTeacher(@Validated Teacher teacher) throws Exception {
		return ResponseHelper.productSuccessResponse(teacherService.updateTeacher(teacher));
	}
}
