package cz.zcu.pia.valesz.core.service;

import cz.zcu.pia.valesz.core.domain.Post;

import java.util.List;

/**
 * Service for post-related operations.
 */
public interface PostManager {

    /**
     * Returns the current post feed which should be displayed to the currently logged user.
     * @return Posts to be displayed.
     */
    List<Post> listPostsForUser();

    /**
     * Creates new post with given text and current date as currently logged user.
     * @return Created post.
     */
    Post createNewPost(String text);
}
