package sagan.site;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("sagan.site")
public class SiteProperties {

    private final Events events = new Events();

    private final Renderer renderer = new Renderer();

    private final GitHub github = new GitHub();


    public Events getEvents() {
        return this.events;
    }

    public Renderer getRenderer() {
        return this.renderer;
    }

    public GitHub getGithub() {
        return github;
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

    public static class GitHub {

        /**
         * GitHub org that holds the team admin users should belong to.
         */
        private String org;

        /**
         * GitHub team admin users should belong to.
         * @see <a href="https://developer.github.com/v3/teams/members/#get-team-membership-for-a-user">GitHub API team membership</a>
         */
        private String team;

        /**
         * Token configured in GitHub webhooks for this application.
         */
        private String webhookToken = "changeme";

        public String getOrg() {
            return this.org;
        }

        public void setOrg(String org) {
            this.org = org;
        }

        public String getTeam() {
            return this.team;
        }

        public void setTeam(String team) {
            this.team = team;
        }

        public String getWebhookToken() {
            return this.webhookToken;
        }

        public void setWebhookToken(String webhookToken) {
            this.webhookToken = webhookToken;
        }
    }
}
