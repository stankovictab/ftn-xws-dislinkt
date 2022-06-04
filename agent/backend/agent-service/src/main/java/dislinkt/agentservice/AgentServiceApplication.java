package dislinkt.agentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "dislinkt")
@ComponentScan(basePackages = "dislinkt")
public class AgentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgentServiceApplication.class, args);
	}

}
