package com.example.amqp;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Application {

	

	public static void main(String[] args) {
		Application app = new Application();
		app.start();
	}

	private void start() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
	}

	
}
