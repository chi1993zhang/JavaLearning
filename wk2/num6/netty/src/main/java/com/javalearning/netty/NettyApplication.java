package com.javalearning.netty;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class NettyApplication {
	static CloseableHttpClient httpClient = HttpClients.createDefault();

	public static void main(String[] args) throws IOException {
		SpringApplication.run(NettyApplication.class, args);
		HttpGet httpGet = new HttpGet("http://localhost:8801");
		CloseableHttpResponse response = httpClient.execute(httpGet);
		String content = EntityUtils.toString(response.getEntity(), "utf8");

		System.out.println(content);
	}

}
