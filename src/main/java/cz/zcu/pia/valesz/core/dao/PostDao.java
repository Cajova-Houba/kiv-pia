package cz.zcu.pia.valesz.core.dao;

import cz.zcu.pia.valesz.core.domain.Post;

import java.util.List;

/**
 * Methods for fetching posts-related data.
 */
public interface PostDao extends GenericDao<Post, Long> {

    /**
     * Returns the current post feed which should be displayed to the currently logged user.
     * @return Posts to be displayed.
     */
    List<Post> listPostsForUser();
}