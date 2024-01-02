package com.greenfox.reddit.services;

import com.greenfox.reddit.models.Post;
import com.greenfox.reddit.models.User;
import com.greenfox.reddit.repositories.PostRepository;
import com.greenfox.reddit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AppService {
    private PostRepository postRepository;
    private UserRepository userRepository;
    @Autowired
    public AppService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Post savePost(Post post, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) return null;

        post.setUser(user.get());
        return postRepository.save(post);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public List<Post> findPosts() {
        List<Post> posts = postRepository.findAll();
        Collections.reverse(posts);
        return posts;
    }

    public void incrementLikes(Long id, boolean increase) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()) return;

        Post post = optionalPost.get();
        if (increase) {
            post.incrementLikes();
        } else {
            post.decrementLikes();
        }
        postRepository.save(post);
    }

    public User saveUser(String name, String password, String avatar) {
        User user = new User(name, password, avatar);
        return userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User login(String name, String password) {
        boolean match = userRepository.findAll().stream()
                            .anyMatch(x -> x.getName().equals(name) && x.getPassword().equals(password));

        if (!match) return null;
        return userRepository.findByNameAndPassword(name,password);
    }
}
