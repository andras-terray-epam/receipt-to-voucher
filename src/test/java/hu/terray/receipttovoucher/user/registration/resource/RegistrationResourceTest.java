package hu.terray.receipttovoucher.user.registration.resource;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import hu.terray.receipttovoucher.common.response.ResponseFactory;
import hu.terray.receipttovoucher.user.registration.resource.domain.RegistrationRequest;
import hu.terray.receipttovoucher.user.registration.resource.domain.RegistrationResponse;
import hu.terray.receipttovoucher.user.registration.service.RegistrationService;

/**
 * Test for {@link RegistrationResource}
 */
@RunWith(MockitoJUnitRunner.class)
public class RegistrationResourceTest {

    @Mock
    private RegistrationService registrationService;

    @Mock
    private ResponseFactory responseFactory;

    @Mock
    private RegistrationResponse registrationResponse;

    private RegistrationRequest registrationRequest;
    private RegistrationResource underTest;

    @Before
    public void setUp() {
        underTest = new RegistrationResource(registrationService, responseFactory);
    }

    @Test
    public void retrieveClubsShouldCreateResponseFromServiceResponseAndStaticUri() throws Exception {
        // GIVEN
        given(registrationService.register(registrationRequest)).willReturn(registrationResponse);

        // WHEN
        underTest.register(registrationRequest);

        // THEN
        verify(registrationService).register(registrationRequest);
        verify(responseFactory).responseWithCreatedStatus(RegistrationResource.GET_ACTUAL_USER_ENDPOINT, registrationResponse);

    }

}
