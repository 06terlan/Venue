package bll.model;

public final class User {

    private final String FirstName;
    private final String LastName;
    private final String PhoneNo;
    private final String UserName;
    private final String Password;

    public User(String firstName, String lastName, String phoneNo, String userName, String password) {
        FirstName = firstName;
        LastName = lastName;
        PhoneNo = phoneNo;
        UserName = userName;
        Password = password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

}
