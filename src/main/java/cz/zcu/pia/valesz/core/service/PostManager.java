package cz.zcu.pia.valesz.core.service;

import cz.zcu.pia.valesz.core.domain.Post;
import cz.zcu.pia.valesz.core.domain.User;
import cz.zcu.pia.valesz.core.domain.Visibility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service for post management.
 */
public interface PostManager {

    /**
     * Returns the current post feed which should be displayed to user.
     *
     * @param user User whose post feed is about to be displayed.
     * @return Posts to be displayed.
     */
    List<Post> listPostsForUser(User user);

    /**
     * Returns one page of the current post feed for a user.
     * Posts returned by this method will also have users with their profile photos loaded.
     *
     * @param user User whose post feed is about to be displayed.
     * @param pageRequest Object containing paging details. If the requested page is out of bounds, the first one will
     *                    be returned.
     * @return One page of user's post feed.
     */
    Page<Post> listPostsForUser(User user, Pageable pageRequest);

    /**
     * Creates new post with given text and current date.
     * If the post's text is longer than allowed maximum, it will be cut to match the allowed length.
     *
     * @param text Text of the post.
     * @param visibility Visibility of the post.
     * @param creator Create post as this user.
     * @return Created post.
     */
    Post createNewPost(String text, Visibility visibility, User creator);

    /**
     * Returns true if the user has already like the post.
     * @param user User.
     * @param post Post.
     * @return True if the post was already liked by user.
     */
    boolean alreadyLiked(User user, Post post);

    /**
     * Returns a post by its id. Nothing is fetched with post.
     * @param id
     * @return
     */
    Post getById(long id);

    /**
     * Adds one like to post from user.
     * @param user User which likes the post.
     * @param post Post to be liked.
     */
    void likePost(User user, Post post);
}
