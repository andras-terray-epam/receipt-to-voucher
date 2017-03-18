package hu.terray.receipttovoucher;

import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.UnsupportedEncodingException;

/**
 * Configuration file for Receipt To Voucher application.
 */
public class AppConfiguration extends Configuration {

    @NotEmpty
    private String jwtTokenSecret = "receiptToVoucherJWTsecretToEncryptJWTTokenMessage";

    public byte[] getJwtTokenSecret() throws UnsupportedEncodingException {
        return jwtTokenSecret.getBytes("UTF-8");
    }
}
