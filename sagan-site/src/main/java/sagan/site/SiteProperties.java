package sagan.site;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("sagan.site")
public class SiteProperties {

    private final Events events = new Events();

    public Events getEvents() {
        return this.events;
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
}
