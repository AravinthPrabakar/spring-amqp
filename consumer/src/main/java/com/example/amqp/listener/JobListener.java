package com.example.amqp.listener;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.amqp.message.Job;
import com.rabbitmq.client.Channel;

@Component("consumer")
public class JobListener implements ChannelAwareMessageListener {

	@Autowired
	RabbitTemplate amqpTemplate;
	
	@Value("${jobs.queue}")
	private String queueName;
	
	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		Job msg = (Job) amqpTemplate.getMessageConverter().fromMessage(message);
		System.out.println("Received Message: " + msg);
		if(channel != null ) {
			long deliveryTag = message.getMessageProperties().getDeliveryTag();
			try {
	    	
				channel.basicAck(deliveryTag, false);
				System.out.println("Message: " + msg+ " ack is successful.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				try {
					channel.basicNack(deliveryTag, false, true);
					System.out.println("Message: " + msg+ " Nack is successful.");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}	
			channel.close();
		}
		
		
		
	}

}
