package ru.lessons;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class Print {
	
	@Autowired
	private Cal calc;
	
	public void printMes(int a, int b){
		System.out.println(a+"+"+b+"=?");
		System.out.println("Ответ: "+this.calc.calculate(a, b));
	}
}
