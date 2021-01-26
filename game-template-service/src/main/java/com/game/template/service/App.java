package com.game.template.service;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App {
    	public static void main(String[] args) {
    		String path = "D:\\Program Files (x86)\\apache-maven-3.3.9\\repo";		//要遍历的路径
    		File file = new File(path);		//获取其file对象
    		func(file);
    	}
    	
    	private static void func(File file){
    		File[] fs = file.listFiles();
    		for(File f:fs){
    			if(f.isDirectory())	//若是目录，则递归打印该目录下的文件
    				func(f);
    			if(f.isFile()&&f.getName().endsWith(".lastUpdated")){		//若是文件，直接打印
    				System.out.println(f);
    				f.delete();
    			}
    		}
    	}

}
