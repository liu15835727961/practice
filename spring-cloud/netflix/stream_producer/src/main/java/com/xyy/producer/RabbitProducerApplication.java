package com.xyy.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootApplication
@EnableBinding(Source.class)
public class RabbitProducerApplication implements CommandLineRunner {
    @Autowired
    private MessageChannel output;

    public static void main(String[] args) {
        SpringApplication.run(RabbitProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //发送消息
        //messageBuilder:工具类创建消息
        output.send(MessageBuilder.withPayload("消息队列生成者").build());
    }
}
