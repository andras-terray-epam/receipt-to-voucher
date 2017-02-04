package hu.terray.receipttovoucher;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;

import javax.inject.Named;


/**
 * Created by andrasterray on 2/4/17.
 */
public class ServerModule implements Module {
    @Override
    public void configure(Binder binder) {
    }

    @Provides
    @Named("template")
    public String provideTemplate(AppConfiguration appConfiguration) {
        return appConfiguration.getTemplate();
    }

    @Provides
    @Named("defaultName")
    public String provideDefaultName(AppConfiguration appConfiguration) {
        return appConfiguration.getDefaultName();
    }
}
