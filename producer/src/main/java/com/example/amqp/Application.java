package com.example.amqp;



import java.util.concurrent.Executors;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.amqp.message.Job;





public class Application {

	private final static int MESSAGE_LIMIT = 1_000;

	
	@Autowired
	RabbitTemplate amqpTemplate;
	
	@Value("${jobs.exchange:'jobs.exchange'}")
	private String exchange;
	
	@Value("${jobs.routingkey:'job'}")
	private String routingKey;
	
	/**
     * Starts the application
     *
     * @param  String[] args
     *
     */
	public static void main(String[] args) {
		Application app = new Application();
		app.start();
	}

	private void start() {

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		amqpTemplate = context.getBean(RabbitTemplate.class);
		exchange = "jobs.exchange";
		routingKey = "job";
		
		Executors.newFixedThreadPool(10).execute(new Runnable() {
			@Override
			public void run() {
				int firstIndex = 1;
				while(firstIndex <= MESSAGE_LIMIT) {
					//Message<List<Job>> message = MessageBuilder.withPayload(getOrderList(firstIndex, lastIndex)).build();
					Job j = new Job(firstIndex, "ProcessData", "STARTED", "Job "+firstIndex,System.nanoTime(), "HIGH");
					long timestamp = System.currentTimeMillis();
					amqpTemplate.convertAndSend(j , new MessagePostProcessor() {
						
						@Override
						public org.springframework.amqp.core.Message postProcessMessage(
								org.springframework.amqp.core.Message message) throws AmqpException {
							 	message.getMessageProperties().setPriority(0);
							 	message.getMessageProperties().setCorrelationId(String.valueOf(timestamp));
				                return message;
						}
				    },new CorrelationData(String.valueOf(timestamp)));
					firstIndex ++;
					
				}
			}

			
		});
		
	
		
	}

	
	private void processingJob() {
		try {
			Thread.sleep((long) (1000 * (Math.random()%10)));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
