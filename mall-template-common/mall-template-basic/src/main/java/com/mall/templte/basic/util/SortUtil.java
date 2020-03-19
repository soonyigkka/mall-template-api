package com.mall.templte.basic.util;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SortUtil {

	public static void sortList(List<Integer> orignal){
		orignal = orignal.stream().sorted((a,b)-> a.compareTo(b)).filter(f-> f < 13).distinct().collect(Collectors.toList());
		System.out.println(orignal);
	}
	
//	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		while(in.hasNext()){
//			String nums = in.nextLine();
//			List<String> inputs = Arrays.asList(nums.split(","));
//			System.out.println(inputs);
//		}
//		int[] nums = {1,2,3,4,5,7,8,8,10,2,5};
//		List<Integer> orignal = IntStream.of(nums).boxed().sorted(SortUtil::myCompare).filter(f-> f < 13).distinct().collect(Collectors.toList());
////		sortList(orignal);
//		System.out.println(orignal);
//		System.out.println(Integer.toBinaryString(100));
//	}
	
	public static int myCompare(Integer a, Integer b){
		return a > b ? 1 : -1;
	}
	
	
	 public static Stack<Integer> stack = new Stack<Integer>();
	    public static void main(String[] args) {
	        int shu[] = {1,2,3,4};
	        f(shu,4,0);
	    }
	    /**
	     *
	     * @param shu   待选择的数组
	     * @param targ  要选择多少个次
	     * @param cur   当前选择的是第几次
	     */
	    private static void f(int[] shu, int targ, int cur) {
	        // TODO Auto-generated method stub
	        if(cur == targ) {
	            System.out.println(stack);
	            return;
	        }
	         
	        for(int i=0;i<shu.length;i++) {
	            stack.add(shu[i]);
	            f(shu, targ, cur+1);
	            stack.pop();
	             
	        }
	    }
}
