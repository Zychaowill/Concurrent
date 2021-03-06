package com.buildupchao.concurrent.utils;

import java.util.Random;

/**
 * @author buildupchao
 * @date 2019/1/9
 * @since JDK 1.8
 */
public class NumberFactory {

	public static Integer[] integers(Integer size) {
		size = (size == null ? 10: size);
		Integer[] numbers = new Integer[size];
		Random random = new Random();
		for (int i = 0; i < size.intValue(); i++) {
			if (i % 2 == 0) {
				numbers[i] = 0 - random.nextInt(1000);
				continue;
			}
			numbers[i] = random.nextInt(1000);
		}
		return numbers;
	}
}
