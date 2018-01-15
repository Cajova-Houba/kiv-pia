package cz.zcu.pia.valesz.core.dao;

import cz.zcu.pia.valesz.core.domain.FriendRequest;
import cz.zcu.pia.valesz.core.domain.Post;
import cz.zcu.pia.valesz.core.domain.User;

import java.util.List;

/**
 * Dao for custom post-related queries
 */
public interface PostDaoCustom {

    /**
     * Returns the current post feed which should be displayed to user.
     * @param user User to whom posts will be displayed.
     * @param usersFriendships Users friendships. Used for visibility based displaying.
     * @return Posts to be displayed.
     */
    List<Post> listPostsForUser(User user, List<FriendRequest> usersFriendships);
}
