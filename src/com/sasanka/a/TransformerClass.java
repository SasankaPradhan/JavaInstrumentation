package com.sasanka.a;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class TransformerClass implements ClassFileTransformer {

	@Override
	public byte[] transform(ClassLoader classLoader, String className, Class classBeingRedefined,
			ProtectionDomain protectionDomain, byte[] classFileBuffer) throws IllegalClassFormatException {
		
		byte[] byteCode = classFileBuffer;
		
		if (className.equals("com/sasanka/a/ClassToBeInstrumented")) {
			System.out.println("Found class for instrumenting.....");
			
			try {
				ClassPool classPool = ClassPool.getDefault();
				CtClass ctClass = classPool.makeClass(new ByteArrayInputStream(classFileBuffer));
				CtMethod[] methods = ctClass.getDeclaredMethods();
				
				for (CtMethod method : methods) {
					if (method.getName().equals("method1")) {
						System.out.println(method.getName() + " method will be instrumented.");
//						method.insertAfter("System.out.println(\"In ClassToBeInstrumented : method1\");");
						method.setBody("System.out.println(\"contents of method1 changed. ha ha ha.\");");
					}
				}
				
				byteCode = ctClass.toBytecode();
				ctClass.detach();
				System.out.println("Instrumentation complete.");
				
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (RuntimeException e2) {
				e2.printStackTrace();
			} catch (CannotCompileException e3) {
				e3.printStackTrace();
			}
			
		}
		
		return byteCode;
	}

}
