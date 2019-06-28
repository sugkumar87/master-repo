package org.javatutor.AWSServices;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Reading from console");
	   for(int i=0;i<5; i++)
	   System.out.println(s.nextLine());

	   s.close();

	}

}
