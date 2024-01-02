package com.greenfox.reddit;

import com.greenfox.reddit.models.User;
import com.greenfox.reddit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedditApplication implements CommandLineRunner {
	private UserRepository userRepository;
	@Autowired
	public RedditApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(RedditApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*User admin = new User();
		admin.setName("Admin");
		admin.setAvatar("https://dokina.timg.cz/2019/05/09/1048145-1498216547-avatar-neytiri-653x367.jpg?1587596281.0");
		admin.setPassword("adminPass");
		userRepository.save(admin);*/
	}
}
