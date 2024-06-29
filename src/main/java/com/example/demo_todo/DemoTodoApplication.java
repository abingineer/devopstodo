package com.example.demo_todo;

import com.example.demo_todo.strategey.ImageStore;
import com.example.demo_todo.strategey.concrete.JpegCompressor;
import com.example.demo_todo.strategey.concrete.PngCompressor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoTodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoTodoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(TodoRepository todoRepository) {
		// Testing my private mvn package
		/*MyMain myMain = new MyMain();
		int result = myMain.sumWithTwoNumbers(20, 30);
		System.out.println("The result is " + result);*/

		var imageStorage = new ImageStore();
		//Compress JPEG and apply B&W filter
		imageStorage.store("image1.JPEG", new JpegCompressor());
		// Compress JPEG and apply High contrast filter
		imageStorage.store("image2.JPEG", new JpegCompressor());
		// Compress PNG and apply High contrast filter
		imageStorage.store("image.PGN", new PngCompressor());

		return args -> {
			  /*Todo todo = new Todo(
					  null,
					  "Backend",
					  "Spring boot project with JAVA 17"
			  );
			  todoRepository.save(todo);*/
		};
	}
}
