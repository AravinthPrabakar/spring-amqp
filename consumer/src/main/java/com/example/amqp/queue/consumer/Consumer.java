package com.example.amqp.queue.consumer;

import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

public class Consumer implements ChannelAwareMessageListener{

	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
