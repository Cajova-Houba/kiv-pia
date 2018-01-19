package cz.zcu.pia.valesz.core.dao;

import cz.zcu.pia.valesz.core.domain.Like;
import cz.zcu.pia.valesz.core.domain.Post;
import cz.zcu.pia.valesz.core.domain.User;

/**
 * DAO methods for likes.
 */
public interface LikeDao extends GenericDao<Like, Long> {

    /**
     * Returns true if the user has already liked the post
     *
     * @return True or false.
     */
    boolean existsByUserAndPost(User user, Post post);
}
