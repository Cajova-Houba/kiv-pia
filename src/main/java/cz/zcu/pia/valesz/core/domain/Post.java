package cz.zcu.pia.valesz.core.domain;


import org.joda.time.DateTime;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

/**
 * Post posted by user to his feed.
 * Also acts as a model for post form.
 */
@XmlRootElement
@Entity
@Table(name = "post")
public class Post {

    public static final int MAX_POST_LENGTH = 1000;

    /**
     * Database id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * User who posted this.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="owner_id")
    private User owner;

    /**
     * Date when this was posted.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "date_posted", nullable = false)
    private Date datePosted;

    /**
     * Time when this was posted.
     */
    @Temporal(TemporalType.TIME)
    @Column(name = "time_posted", nullable = false)
    private Date timePosted;

    /**
     * Content of the post.
     */
    @Column(length = MAX_POST_LENGTH, nullable = false)
    private String text;

    /**
     * Visibility of this post to the rest of the world.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Visibility visibility;

    /**
     * Likes for this post;
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private List<Like> likes;

    /**
     * Default constructor sets the datePosted and timePosted to current date/time.
     */
    public Post() {
        this(new DateTime().toDate(), "", null);
    }


    public Post(Date dateTimePosted, String text, User owner) {
        setDatePosted(dateTimePosted);
        setTimePosted(dateTimePosted);
        setText(text);
        setOwner(owner);
        setVisibility(Visibility.REGISTERED_USERS);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the user who posted this post. Field is LAZY loaded.
     * @return Poster.
     */
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public Date getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(Date timePosted) {
        this.timePosted = timePosted;
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

    /**
     * Returns all likes for this post. Field is LAZY loaded.
     * @return Likes for this post.
     */
    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
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
