package com.winter.app.sample;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SampleTest {
	@Test
	void test() {
		// 0회차
		// 0
		// 1    =>    1 +0
		// 2    =>    2
		// 3
		// 1회차
		// 4    =>    10 +6
		// 5    =>    11
		// 6    =>    12
		// 7    =>    13
		// 2회차
		// 8    =>    20 +12
		// 9    =>    21 +12
		
		// 6*회차수 씩 증가
		// 4로 나눈 몫이 회차 수인듯 -> 8로 확인 가능
		
		for (int i = 0; i < 20; i++) {
			System.out.println(i + " -> " + (i + (6 * (i / 4))));
		}
		
		// i + (6 * (i / 4))
	}
	
	@Test
	void test2() {
		// 편의점
		int price = 34500;
		int money = 50000;
		
		int man = 0;
		int chon = 0;
		int bak = 0;
		
		man = (money - price) / 10000;
		chon = (money - price) % 10000 / 1000;
		bak = (money - price) % 1000 / 100;
		System.out.println("만원 " + man + "장");
		System.out.println("천원 " + chon + "장");
		System.out.println("백원 " + bak + "개");
	}
}
