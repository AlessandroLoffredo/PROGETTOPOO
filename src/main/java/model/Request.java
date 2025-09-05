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
}
