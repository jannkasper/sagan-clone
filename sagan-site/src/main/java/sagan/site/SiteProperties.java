package sagan.site;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("sagan.site")
public class SiteProperties {

    private final Events events = new Events();

    private final Renderer renderer = new Renderer();

    public Events getEvents() {
        return this.events;
    }

    public Renderer getRenderer() {
        return this.renderer;
    }

    public static class Events {

        /**
         * URL to the ICS calendar for Spring events.
         */
        private String calendarUri;

        public String getCalendarUri() {
            return this.calendarUri;
        }

        public void setCalendarUri(String calendarUri) {
            this.calendarUri = calendarUri;
        }
    }

    public static class Renderer {

        /**
         * Remote service for rendering text markup as HTML content
         * and fetching guides content.
         */
        private String serviceUrl = "http://localhost:8081";

        public String getServiceUrl() {
            return this.serviceUrl;
        }

        public void setServiceUrl(String serviceUrl) {
            this.serviceUrl = serviceUrl;
        }
    }
}
