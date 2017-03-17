package hu.terray.receipttovoucher.auth;

import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.UnsupportedEncodingException;

public class MyConfiguration extends Configuration {

    @NotEmpty
    private String jwtTokenSecret = "dfwzsdzwh823zebdwdz772632gdsbd";

    public byte[] getJwtTokenSecret() throws UnsupportedEncodingException {
        return jwtTokenSecret.getBytes("UTF-8");
    }
}
