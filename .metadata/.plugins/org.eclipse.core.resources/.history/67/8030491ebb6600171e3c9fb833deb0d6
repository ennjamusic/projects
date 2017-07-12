package ru.lessons;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

@Configuration 
@ComponentScan
public class Application {
	
	@Bean
	Cal calc(){
		return new Cal(){
			public int calculate(int a, int b){
				return a+b;
			}
		};
	}

	public static void main(String[] args) {
		ApplicationContext context =
			    new AnnotationConfigApplicationContext(Application.class);
		
		Print printer = context.getBean(Print.class);
		printer.printMes(2,4);
	}

}