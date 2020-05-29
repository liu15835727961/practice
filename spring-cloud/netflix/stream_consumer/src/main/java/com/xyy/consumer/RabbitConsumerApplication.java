package com.xyy.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@EnableBinding(Sink.class)
public class RabbitConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitConsumerApplication.class, args);
    }

    @StreamListener(Sink.INPUT)
    public void rabbitListener(String message){
        System.out.println("获取到消息:"+message);
    }
}
