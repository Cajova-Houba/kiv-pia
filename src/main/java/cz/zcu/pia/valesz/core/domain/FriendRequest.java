package cz.zcu.pia.valesz.core.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Friend request sent by one user (sender) to another (receiver).
 */
@Entity
@Table(name = "friend_request")
public class FriendRequest {

    /**
     * Database id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * User who sent the request.
     */
    @ManyToOne
    private User sender;

    /**
     * User who will either accept or reject the request.
     */
    @ManyToOne
    private User receiver;

    /**
     * Date on which the request has been created (sent).
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    private Date createdDate;

    /**
     * Date on which the request has been accepted or rejected.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "responded_date")
    private Date respondedDate;

    /**
     * State of the request.
     */
    @Column(name = "friend_request_state")
    @Enumerated(EnumType.STRING)
    private FriendRequestState friendRequestState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getRespondedDate() {
        return respondedDate;
    }

    public void setRespondedDate(Date respondedDate) {
        this.respondedDate = respondedDate;
    }

    public FriendRequestState getFriendRequestState() {
        return friendRequestState;
    }

    public void setFriendRequestState(FriendRequestState friendRequestState) {
        this.friendRequestState = friendRequestState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendRequest that = (FriendRequest) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
