package cz.zcu.pia.valesz.core.dao;

import cz.zcu.pia.valesz.core.domain.Post;
import cz.zcu.pia.valesz.core.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Methods for fetching posts-related data.
 */
public interface PostDao extends GenericDao<Post, Long>, PostDaoCustom {

    /**
     * Returns posts which are visible for user. Note that this method also joins user's profile photos
     * so they're accessible outside of session.
     *
     * @param user User for which posts will be returned.
     * @param usersFriends User's friends - to filter via visibility.
     * @param pageRequest Object containing page info.
     * @return Page with posts.
     */
    @Query(value = "SELECT p FROM Post p  " +
            " LEFT JOIN FETCH p.owner o " +
            " LEFT JOIN FETCH o.profilePhoto " +
            " WHERE " +
            " p.visibility IN ('EVERYONE', 'REGISTERED_USERS')" +
            " OR ( " +
            "       p.visibility = 'OWNER_FRIENDS' " +
            "       AND p.owner IN :usersFriends " +
            " ) OR (" +
            "       p.owner = :user" +
            " )",
           countQuery = "SELECT COUNT(p) FROM Post p  " +
           " WHERE " +
           " p.visibility IN ('EVERYONE', 'REGISTERED_USERS')" +
           " OR ( " +
           "       p.visibility = 'OWNER_FRIENDS' " +
           "       AND p.owner IN :usersFriends " +
           " ) OR (" +
           "       p.owner = :user" +
           " )"
    )
    Page<Post> getPostFeedForUser(@Param("user")User user, @Param("usersFriends") List<User> usersFriends, Pageable pageRequest);

    /**
     * Returns posts which are visible for user.
     *
     * @param user User for which posts will be returns.
     * @param pageRequest Object containing page info.
     * @return Page with posts
     */
    @Query(value = "SELECT p FROM Post p " +
            " LEFT JOIN FETCH p.owner o " +
            " LEFT JOIN FETCH o.profilePhoto " +
            " WHERE " +
            " p.visibility IN ('EVERYONE', 'REGISTERED_USERS')" +
            " OR (" +
            "       p.owner = :user" +
            " )",
            countQuery = "SELECT COUNT(p) FROM Post p " +
            " WHERE " +
            " p.visibility IN ('EVERYONE', 'REGISTERED_USERS')" +
            " OR (" +
            "       p.owner = :user" +
            " )"
    )
    Page<Post> getPostFeedForFriendlessUser(@Param("user")User user, Pageable pageRequest);
}
