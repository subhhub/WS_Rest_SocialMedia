package edu.subh.ws.rs.messenger.service;

public class CalculatorServiceImpl implements CalculatorService {

	@Override
	public int add(int a, int b) {
		return a+b;
	}

	@Override
	public int sub(int a, int b) {
		return a-b;
	}

}
