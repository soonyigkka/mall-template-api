package com.mall.template.teacher.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@Api(value = "Teacher", tags = "Teacher Bean")
public class Teacher {

	private long teacherId;
	
	@ApiParam(name = "teacherName", value = "teacherName", defaultValue = "zhangsan")
	private String teacherName;
	
	private int teacherAge;
	
	private TeacherType teacherType;

	public long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(long teacherId) {
		this.teacherId = teacherId;
	}
	
	public Teacher setTeacherIdSelfCall(long teacherId) {
		this.teacherId = teacherId;
		return this;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public int getTeacherAge() {
		return teacherAge;
	}

	public void setTeacherAge(int teacherAge) {
		this.teacherAge = teacherAge;
	}

	public TeacherType getTeacherType() {
		return teacherType;
	}

	public void setTeacherType(TeacherType teacherType) {
		this.teacherType = teacherType;
	}

	enum TeacherType{
		
		chinese,math,english;
		
		private String value;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
		
	}
	
	
}
