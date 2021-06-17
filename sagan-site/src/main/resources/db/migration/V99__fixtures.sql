-- Member Profiles

INSERT INTO member_profile
(avatar_url, bio, latitude, longitude, github_id,  github_username, gravatar_email, hidden, lanyrd_username,
 location, name, speakerdeck_username, twitter_username,  username, video_embeds, job_title)
 VALUES ('/images/icon-spring-boot.svg', 'Spring Boot team member and fixture user for the application.',
         37.781929, -122.4041, 123456, 'spring', 'spring@spring.io', FALSE, NULL, 'San Francisco, CA',
         'Emily Spring', NULL, 'springcentral', 'springcentral', NULL, 'Spring Team Member');

-- Blog Posts

INSERT INTO post (broadcast, category, created_at, draft, format, public_slug, publish_at, raw_content,
                  rendered_content, rendered_summary, title, author_id)
VALUES (FALSE,'RELEASES','2020-01-22 08:55:32.503',FALSE,'MARKDOWN','2020/01/22/spring-boot-2-3-0','2020-01-22 08:56:05.769',
'Dear Spring Community,

We are happy to announce the 2.3.0 release of Spring Boot.
Highlights from this release include:

* A great feature
* Another great feature
* Many bug fixes',
'<p>Dear Spring Community,</p>
<p>We are happy to announce the 2.3.0 release of Spring Boot.</p>
<p>Highlights from this release include:</p>
<ul>
<li>A great feature</li><li>Another great feature</li><li>Many bug fixes</li>
</ul>', '<p>Dear Spring Community,</p><p>We are happy to announce the 2.3.0 release of Spring Boot.</p>'
,'Spring Boot 2.3.0 released',1),

(FALSE,'NEWS_AND_EVENTS','2020-01-18 08:55:32.503',FALSE,'MARKDOWN','2020/01/18/this-week-in-spring-2020-01-18','2020-01-18 08:56:05.769',
'Hi, Spring fans!
There is a lot to cover this week, so let s get to it.

Links this week:

* [Spring Blog](https://spring.io/blog)
* [Spring Initializr](https://start.spring.io)
',
'<p>Hi, Spring fans!</p>
<p>There is a lot to cover this week, so let s get to it.</p>
<p>Links this week:</p><ul>
<li><a href="https://spring.io/blog">Spring Blog</a></li>
<li><a href="https://start.spring.io/">Spring Initializr</a></li></ul>
',
'<p>Hi Spring fans!</p><p>There is a lot to cover this week, so let s get to it.</p>',
'This Week in Spring - 2020-01-18',1);