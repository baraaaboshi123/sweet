package software;

public class UserDetails {
    private String username;
    private String city;
    // Add more fields if needed, such as email, phone number, etc.

    // Constructor
    public UserDetails(String username, String city) {
        this.username = username;
        this.city = city;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // You can add more getters and setters if additional fields are added

    @Override
    public String toString() {
        return "UserDetails{" +
                "username='" + username + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
