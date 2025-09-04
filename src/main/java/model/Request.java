package model;

/**
 * The type Request.
 */
public class Request {
    private String message;
    private String sender;

    /**
     * Instantiates a new Request.
     */
    public Request() {
        this.message = null;
        this.sender = null;
    }

    /**
     * Instantiates a new Request.
     *
     * @param message the message
     * @param user    the user
     */
    public Request(String message, String user) {
        this.message = message;
        this.sender = user;
    }

    /**
     * Gets sender.
     *
     * @return the sender
     */
    public String getSender() {
        return this.sender;
    }

    /**
     * Sets sender.
     *
     * @param sender the sender
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message + this.sender;
    }
}
