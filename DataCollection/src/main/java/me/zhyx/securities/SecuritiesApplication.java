package me.zhyx.securities;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableWebSocket
@MapperScan("me.zhyx.securities.dao")
@EnableScheduling
public class SecuritiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecuritiesApplication.class, args);
	}

}
