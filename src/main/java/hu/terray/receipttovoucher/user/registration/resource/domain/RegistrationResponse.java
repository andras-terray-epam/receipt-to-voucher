package hu.terray.receipttovoucher.user.registration.resource.domain;

/**
 * DTO to hold user id.
 */
public class RegistrationResponse {
    private String userId;

    public RegistrationResponse(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
