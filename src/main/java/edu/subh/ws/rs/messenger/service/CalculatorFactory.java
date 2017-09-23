package edu.subh.ws.rs.messenger.service;

public class CalculatorFactory {

	private static CalculatorService calculatorService = null;
	
	private CalculatorFactory() {
	}
	
	public static CalculatorService getCalculatorService() {
		
		if(calculatorService!=null)
			calculatorService = new CalculatorServiceImpl();
		
		return calculatorService;
	}
	
	
}
