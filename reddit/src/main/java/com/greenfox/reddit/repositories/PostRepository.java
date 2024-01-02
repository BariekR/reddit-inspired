package com.greenfox.reddit.repositories;

import com.greenfox.reddit.models.Post;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends ListCrudRepository<Post, Long> {
}
