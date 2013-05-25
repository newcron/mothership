package de.mh.mothership;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.mh.mothership.http.WebServer;

public class Mothership {

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(Mothership.class);
    private Injector injector;

    public static void main(String... args) {
        final Mothership mothership = new Mothership();

        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                mothership.stop();
            }
        });

        mothership.start();
    }

    public void start() {
        LOG.info("Starting Mothership");
        injector = Guice.createInjector(new MothershipModule());
        injector.getInstance(WebServer.class);
        LOG.info("Startup Complete");
    }

    public void stop() {
        LOG.info("Landing Mothership");
        injector.getInstance(WebServer.class).stop();
        LOG.info("Shutdown complete");
        injector = null;
    }
}
