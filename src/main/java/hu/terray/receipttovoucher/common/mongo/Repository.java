package hu.terray.receipttovoucher.common.mongo;

import io.dropwizard.lifecycle.Managed;

/**
 * Repository to CRUD operations for instances of {@link T} class.
 *
 * @param <T>
 *            The Type of the objects which will be in this repository.
 * @param <K>
 *            The key type of the repository.
 */
public interface Repository<T, K> extends Managed {

    /**
     * Now Repositories are managed objects (they implement Managed interface)
     * Default stop implementation does not do anything.
     */
    @Override
    default void stop() {

    }
}
