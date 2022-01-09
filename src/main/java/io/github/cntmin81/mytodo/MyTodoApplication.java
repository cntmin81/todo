package io.github.cntmin81.mytodo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.cntmin81.mytodo.entity.Task;
import io.github.cntmin81.mytodo.repository.TaskRepository;

@SpringBootApplication
public class MyTodoApplication {

	private static final Logger log = LoggerFactory.getLogger(MyTodoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MyTodoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(TaskRepository taskRepository) {
		return (args) -> {
			log.info("");
			log.info("=================================");
			log.info("Task Repository test");
//			
//			// Task저장
//			taskRepository.save(new Task("Todo어플리케이션 만들기", false));
//			taskRepository.save(new Task("청소하기", false));
//			taskRepository.save(new Task("운동하기", true));
//			
//			// 모든Task출력
//			Iterable<Task> taskList = taskRepository.findAll();
//			taskList.forEach(e -> log.info(e.toString()));
			log.info("=================================");
		};
	}

}