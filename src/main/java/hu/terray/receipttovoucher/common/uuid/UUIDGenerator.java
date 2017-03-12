package hu.terray.receipttovoucher.common.uuid;

/**
 * UUID related service.
 */
public interface UUIDGenerator {
    /**
     * Gives random UUID.
     *
     * @return random UUID.
     */
    String createRandomUUID();
}
