package hu.terray.receipttovoucher.common.uuid;

import javax.inject.Singleton;
import java.util.UUID;

/**
 * {@inheritDoc}
 */
@Singleton
public class DefaultUUIDGenerator implements UUIDGenerator {

    @Override
    public String createRandomUUID() {
        return UUID.randomUUID().toString();
    }
}
