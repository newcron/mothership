package de.mh.mothership;

import com.google.inject.AbstractModule;
import de.mh.mothership.http.WebServer;

/** Guice module configuration for mothership */
class MothershipModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(WebServer.class);
    }

}
