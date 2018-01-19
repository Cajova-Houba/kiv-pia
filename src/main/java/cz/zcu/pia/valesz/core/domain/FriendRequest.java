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
     * User who sent the request. Probably won't be needed every time - so lazy fetched.
     */
    private User sender;

    /**
     * User who will either accept or reject the request.  Probably won't be needed every time - so lazy fetched.
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
        setFriendRequestState(FriendRequestState.CREATED);
        createdDate = new Date();
    }

    /**
     * Initializes friend request with sender and receiver.
     * createDate is set to the current date.
     *
     * @param sender Request sender.
     * @param receiver Request receiver
     */
    public FriendRequest(User sender, User receiver) {
        this();
        this.sender = sender;
        this.receiver = receiver;
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
    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    @ManyToOne(fetch = FetchType.LAZY)
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

    @Column(name = "friend_request_state", nullable = false)
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

    /**
     * Sets the state of this request to ACCEPTED.
     */
    public void accept() {
        setFriendRequestState(FriendRequestState.ACCEPTED);
    }

    /**
     * Sets the state of this request to REJECTED.
     */
    public void reject() {
        setFriendRequestState(FriendRequestState.REJECTED);
    }

    /**
     * Returns the other user from provided one. If user == sender, receiver is returned, ...
     * @param user User.
     * @return Other user.
     */
    @Transient
    public User getOtherUser(User user) {
        if(getSender() != null && getSender().equals(user)) {
            return getReceiver();
        } else if (getReceiver() != null && getReceiver().equals(user)) {
            return getSender();
        } else {
            return null;
        }
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
