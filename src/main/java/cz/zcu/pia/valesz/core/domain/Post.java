package cz.zcu.pia.valesz.core.domain;

import java.util.Date;

/**
 * Post posted by user to his feed.
 */
public class Post {

    /**
     * Database id.
     */
    private Long id;

    /**
     * User who posted this.
     */
    private User owner;

    /**
     * Date (and time) when this was posted.
     */
    private Date posted;

    /**
     * Content of the post.
     */
    private String text;

    /**
     * Visibility of this post to the rest of the world.
     */
    private Visibility visibility;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Date getPosted() {
        return posted;
    }

    public void setPosted(Date posted) {
        this.posted = posted;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        return id.equals(post.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
