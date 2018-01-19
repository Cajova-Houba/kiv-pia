package cz.zcu.pia.valesz.core.domain;

import javax.persistence.*;

/**
 * Entity which allows users like various posts.
 *
 * In most cases getUser() or getPosts() won't be needed so lazy loading is used.
 *
 */
@Entity
@Table(name = "kivbook_like")
public class Like {

    /**
     * Database id.
     */
    private Long id;

    /**
     * User who liked the post.
     */
    private User user;

    /**
     * Post which was liked.
     */
    private Post post;

    public Like() {
    }

    public Like(User user, Post post) {
        this.user = user;
        this.post = post;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Like like = (Like) o;

        return id != null ? id.equals(like.id) : like.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
