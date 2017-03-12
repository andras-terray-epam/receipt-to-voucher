package hu.terray.receipttovoucher.user.registration.resource.domain;

/**
 * DTO to hold user id.
 */
public class RegistrationResponse {
    private String userId;

    /**
     * Registration response cannot be created with missing user id.
     *
     * @param userId id of the created user.
     */
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
