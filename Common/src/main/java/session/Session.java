package session;

import lombok.Data;

@Data
public class Session {
    private String userId;
    private String email;

    public Session(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Session [userId=" + userId + ", email=" + email + "]";
    }
}
