package hu.terray.receipttovoucher.user.registration.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import hu.terray.receipttovoucher.user.registration.dao.MongoRegistrationDao;
import hu.terray.receipttovoucher.user.registration.resource.domain.RegistrationRequest;
import hu.terray.receipttovoucher.user.registration.resource.domain.RegistrationResponse;

/**
 * Test for {@link RegistrationService}
 */
@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceTest {

    @Mock
    private MongoRegistrationDao mongoRegistrationDao;

    @Mock
    private RegistrationResponse registrationResponse;

    private RegistrationService underTest;

    private RegistrationRequest registrationRequest;

    @Before
    public void setUp() {
        underTest = new RegistrationService(mongoRegistrationDao);
    }

    @Test
    public void registerShouldCallRegistrationDaoAndForwardTheResponse() throws Exception {
        // GIVEN in setup
        given(mongoRegistrationDao.register(registrationRequest)).willReturn(registrationResponse);

        // WHEN
        RegistrationResponse response = underTest.register(registrationRequest);

        // THEN
        verify(mongoRegistrationDao).register(registrationRequest);
        assertEquals(registrationResponse, response);


    }

}
