package com.example.demo_todo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoTodoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void generateList() {
		var myList = List.of("e0Z7loQ, jNgHfPBt, d2IojrOQ, HjO2DtI, cHitcESl7, DPZrC3C8");
	}

}
