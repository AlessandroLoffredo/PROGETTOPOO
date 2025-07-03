package dao;

public interface AuthInterface {
    int logIn(String username, String password);
    int signUp(String username, String password, String fName, String lName);
    int changePassword(String username, String password);
    int changeUsername(String newUsername, String oldUsername);
}
