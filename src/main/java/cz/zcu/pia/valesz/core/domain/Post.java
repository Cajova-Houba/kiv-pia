package cz.zcu.pia.valesz.core.domain;


import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Date;

/**
 * Post datePosted by user to his feed.
 */
@Entity
@Table(name = "post")
public class Post {

    /**
     * Database id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * User who posted this.
     */
    @ManyToOne(fetch = FetchType.EAGER)
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
    @Column(length = 1000, nullable = false)
    private String text;

    /**
     * Visibility of this post to the rest of the world.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Visibility visibility;

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
