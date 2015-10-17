package com.sasanka.a;

public class TestClassToBeInstrumented {

	public static void main(String[] args) {
		ClassToBeInstrumented classToBeInstrumented = new ClassToBeInstrumented();
		System.out.println("===================");
		System.out.println("Executing test class.");
		classToBeInstrumented.method1();
		classToBeInstrumented.method2();
		System.out.println("===================");
	}

}
