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
    private Long id;

    /**
     * User who sent the request.
     */
    private User sender;

    /**
     * User who will either accept or reject the request.
     */
    private User receiver;

    /**
     * Date on which the request has been created (sent).
     */
    private Date createdDate;

    /**
     * Date on which the request has been accepted or rejected.
     */
    private Date respondedDate;

    /**
     * State of the request.
     */
    private FriendRequestState friendRequestState;

    public FriendRequest() {
    }

    public FriendRequest(Long id, User sender, User receiver, FriendRequestState friendRequestState) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.friendRequestState = friendRequestState;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    @ManyToOne
    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "responded_date")
    public Date getRespondedDate() {
        return respondedDate;
    }

    public void setRespondedDate(Date respondedDate) {
        this.respondedDate = respondedDate;
    }

    @Column(name = "friend_request_state")
    @Enumerated(EnumType.STRING)
    public FriendRequestState getFriendRequestState() {
        return friendRequestState;
    }

    public void setFriendRequestState(FriendRequestState friendRequestState) {
        this.friendRequestState = friendRequestState;
    }

    /**
     * Returns true if the request is new (receiver haven't responded yet).
     * @return
     */
    @Transient
    public boolean isNew() {
        return getFriendRequestState() == FriendRequestState.PENDING;
    }

    /**
     * Returns true if the request is accepted.
     * @return
     */
    @Transient
    public boolean isAccepted() {
        return getFriendRequestState() == FriendRequestState.ACCEPTED;
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
