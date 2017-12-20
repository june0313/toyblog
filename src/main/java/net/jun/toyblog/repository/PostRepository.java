package net.jun.toyblog.repository;

import net.jun.toyblog.domain.Post;
import net.jun.toyblog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
	Page<Post> findAllByUser(User user, Pageable pageable);
}
