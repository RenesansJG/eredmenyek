package hu.renesans.eredmenyek;

import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowLog;

public class TestHelper {
    public static void setTestInjector() {
        ShadowLog.stream = System.out;
        EredmenyekApplication application = (EredmenyekApplication) RuntimeEnvironment.application;
        EredmenyekApplicationComponent injector = DaggerTestComponent.builder().testModule(new TestModule(application.getApplicationContext())).build();
        application.setInjector(injector);
    }
}
