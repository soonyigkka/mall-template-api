package com.mall.test;

import java.util.Random;

public final class TestValue {
    
    public static final String TEST_GOOD_ID = "good2018120301";
    
    public static void createSSQ(){
    	for(int i=0;i<6;i++){
    		System.out.println(new Random().nextInt(33)+1);
    	}
    	System.out.println(new Random().nextInt(16)+1);
    }
    
    
    public static void createDLT(){
    	for(int i=0;i<5;i++){
    		System.out.println(new Random().nextInt(35)+1);
    	}
    	System.out.println(new Random().nextInt(12)+1);
    	System.out.println(new Random().nextInt(12)+1);
    }
    
    public static int indexMask = 16 * 1024 - 1 ;
    
    private static int getIndex(long sequcnce) {
        return  (int) (sequcnce & indexMask);
    }
    
    public static void main(String[] args) {
    	
    	for(long i=16384;i<17100;i++){
    		System.out.println("i: " +i + " value : "  + getIndex(i));
    	}
//    	createSSQ();
//    	createDLT();
	}
}
