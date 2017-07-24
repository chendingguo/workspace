package com.samples;

import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;

class Room<T> {

	private T t;

	public void add(T t) {
		this.t = t;
	}

	public T get() {
		return t;
	}
}

public class Main {
	public static void main(String[] args) {
//		Room<Integer> room = new Room<Integer>();
//		room.add(60);
//		Integer i = room.get();
//		System.out.println(i);
		
//		int a=8;
//		int b=2;
//		System.out.println(Integer.toBinaryString(a));
//		System.out.println(Integer.toBinaryString(b));
//		
//		 System.out.println(a>>b);
		DecimalFormat df = new DecimalFormat("#.##");
		double d = 9800 / 100d;
		System.out.println(df.format(d));
	}
}