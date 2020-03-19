package com.game.template.service;

import java.util.Scanner;

public class Main {

	private static int REST_VALUE = 300;

	public static boolean validParam(int price, int planDay, int factDay) {
		if (price < 0 || planDay < 0 || factDay < 0) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param price
	 *            圖書價格
	 * @param planDay
	 *            預期的借書時間
	 * @param factDay
	 *            實際的借書時間
	 * @return
	 */
	public static int restCal(int price, int planDay, int factDay) {
		if (!validParam(price, planDay, factDay)) {
			return REST_VALUE;
		}
		int rent = 0; // 租金
		if (REST_VALUE < price) { // 小於價格則不能借
			return REST_VALUE;
		}
		if (price >= 100) {
			if (factDay <= 15) {
				rent = 5 * factDay;
			} else {
				rent = 5 * 15 + (factDay - 15) * 3;
			}
		}
		if (price >= 50 && price < 100) {
			if (factDay <= 15) {
				rent = 3 * factDay;
			} else {
				rent = 3 * 15 + (factDay - 15) * 2;
			}
		}
		if (price < 50) {
			rent = factDay;
		}
		if (factDay > planDay) {// 逾期
			rent = rent + factDay - planDay;
		}
		REST_VALUE = REST_VALUE - (rent > price ? price : rent);
		return REST_VALUE;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
			String values = in.nextLine();
			String[] params =  values.split(",");
			int price = Integer.parseInt( params[0] );
			int planDay = Integer.parseInt(params[1]);
			int factDay = Integer.parseInt(params[2]);
			restCal(price, planDay, factDay);
		}
		System.out.println(REST_VALUE);
//		restCal(120, 10, 10);
//		restCal(80, 10, 3);
//		restCal(30, 10, 12);
	}

	// public static void main(String[] args) {
	// Scanner sc = new Scanner(System.in);
	// int n = sc.nextInt();
	// int ans = 0, x;
	// for(int i = 0; i < n; i++){
	// for(int j = 0; j < n; j++){
	// x = sc.nextInt();
	// ans += x;
	// }
	// }
	// System.out.println(ans);
	// }
}
