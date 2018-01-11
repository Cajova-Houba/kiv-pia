package cz.zcu.pia.valesz.core.service;

import cz.zcu.pia.valesz.core.domain.Post;
import cz.zcu.pia.valesz.core.domain.User;

import java.util.List;

/**
 * Service for post-related operations.
 */
public interface PostManager {

    /**
     * Returns the current post feed which should be displayed to user.
     * @param user User whose post feed is about to be displayed.
     * @return Posts to be displayed.
     */
    List<Post> listPostsForUser(User user);

    /**
     * Creates new post with given text and current date.
     * @param text Text of the post.
     * @param creator Create post as this user.
     * @return Created post.
     */
    Post createNewPost(String text, User creator);
}
