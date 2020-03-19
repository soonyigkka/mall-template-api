package com.game.template.service.second;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * 找最長數字串
 * @author csu
 *
 */
public class Main {

	public static String findMaxLenNumber(String orginalStr){
		String result = "";
		String sb = "";
		String param = "";
		boolean lastStrIsNumber = false;
		boolean currentStrIsDian = false;
		boolean currentStrIsNumber = false;
		for(int i=0;i<orginalStr.length();i++){
			currentStrIsNumber = false;
			currentStrIsDian = false;
			param = orginalStr.substring(i,i+1);
			if(isNumber(param)){//是否數字
				sb = sb + param;
				currentStrIsNumber = true;
			}
			if(isDian(param)){
				currentStrIsDian = true;
				if(lastStrIsNumber && !sb.contains(".")){//上一輪是數字且無點
					sb = sb + param;
				}else{
					if(result.length()<= sb.length()){
						result = sb;
					}
					if(sb.contains(".")){
						sb = sb.substring(sb.indexOf(".")+1)+".";
					}else{
						sb = "";
					}
					
				}
			}
			if(!currentStrIsNumber && !currentStrIsDian){
				if(sb.length()>1 && (".").equals(sb.substring(sb.length()-1))){
					sb = sb.substring(0,sb.length()-1);
				}
				if(result.length() <= sb.length()){ 
					result = sb;
				}
				sb = "";
			}
			if(i == orginalStr.length()-1 ){
				if(result.length() <= sb.length()){ 
					result = sb;
				}
			}
			lastStrIsNumber = currentStrIsNumber;
		}
		return result;
	}
	
	
	
	public static boolean isNumber(String original){
		return Pattern.matches("[0-9]",original);
	}
	
	public static boolean isDian(String original){
		return Pattern.matches("[.]",original);
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
			String values = in.nextLine();
			System.out.println(findMaxLenNumber(values));
		}
//		System.out.println(findMaxLenNumber("abcd123.4567.890.12321231"));
	}
}
