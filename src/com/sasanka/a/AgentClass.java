package com.sasanka.a;

import java.lang.instrument.Instrumentation;

public class AgentClass {
	
	public static void premain(String agentArgs, Instrumentation instrumentation) {
		System.out.println("Executing premain method.....");
		instrumentation.addTransformer(new TransformerClass());
	}

}
