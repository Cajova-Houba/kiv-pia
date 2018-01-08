package cz.zcu.pia.valesz.core.dao;

import cz.zcu.pia.valesz.core.domain.Post;
import cz.zcu.pia.valesz.core.domain.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Methods for fetching posts-related data.
 */
public interface PostDao extends GenericDao<Post, Long> {

    /**
     * Returns the current post feed which should be displayed to user.
     * @param user User to whom posts will be displayed.
     * @return Posts to be displayed.
     */
    @Query("SELECT p FROM Post p LEFT JOIN FETCH p.owner")
    List<Post> listPostsForUser(User user);
}
