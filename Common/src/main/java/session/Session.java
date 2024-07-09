package session;

import lombok.Data;

@Data
public class Session {
    private Long userId;
    private String email;

    public Session(Long userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Session [userId=" + userId + ", email=" + email + "]";
    }
}
