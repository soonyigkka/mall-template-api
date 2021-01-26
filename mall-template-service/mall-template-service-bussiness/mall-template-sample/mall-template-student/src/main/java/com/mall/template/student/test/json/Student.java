package com.mall.template.student.test.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

public class Student {

	private String name;
	
	private int age;
	
	private int sex;
	
	private List<Teacher> teachers;

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}
	
	public static void main(String[] args) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", "tom");
		paramMap.put("age", "18");
		paramMap.put("sex", 1);
		
		Teacher teacher_yuwen = new Teacher("tea-1", 22, "yuwen");
		Teacher teacher_shuxue = new Teacher("tea-2", 25, "shuxue");
		List<Teacher> teachers = new ArrayList<Teacher>();
		teachers.add(teacher_shuxue);
		teachers.add(teacher_yuwen);
		paramMap.put("teachers", teachers);
		
		String jsonValue =JSONObject.toJSONString(paramMap);
		System.out.println(jsonValue);
		
		Student student =JSONObject.parseObject(jsonValue,Student.class);
		
		
		
	}
	
	
}
