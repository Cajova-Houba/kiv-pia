package cz.zcu.pia.valesz.core.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * A message sent by one user to another.
 */
@Entity
@Table(name = "message")
public class Message {

    /**
     * Database id.
     */
    private Long id;

    /**
     * User who sent the message.
     */
    private User sender;

    /**
     * User who received the message.
     */
    private User receiver;

    /**
     * Message content.
     */
    private String text;

    /**
     * Timestamp created when the message is sent.
     */
    private Date timestamp;

    /**
     * Current state of the message.
     */
    private MessageState state;

    public Message() {
        state = MessageState.CREATED;
    }

    public Message(Long id, User sender, User receiver, String text, Date timestamp, MessageState state) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
        this.timestamp = timestamp;
        this.state = state;
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

    @Column(length = 500)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Enumerated(EnumType.STRING)
    public MessageState getState() {
        return state;
    }

    public void setState(MessageState state) {
        this.state = state;
    }

    /**
     * Returns true if the message state is SENT (receiver haven't seen it yet).
     * @return
     */
    @Transient
    public boolean isNew() {
        return getState() == MessageState.SENT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        return id.equals(message.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
