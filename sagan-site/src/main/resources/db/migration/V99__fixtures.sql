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

-- Spring Tools

INSERT INTO spring_tools_platform (id)
VALUES ('vscode'),
       ('eclipse'),
       ('theia');

INSERT INTO spring_tools_platform_downloads (spring_tools_platform_id, variant, label, download_url)
VALUES ('eclipse', 'windows', 'Windows 64-bit',
        'http://download.springsource.com/release/STS4/4.1.1.RELEASE/dist/e4.10/spring-tool-suite-4-4.1.1.RELEASE-e4.10.0-win32.win32.x86_64.zip'),
       ('eclipse', 'macos', 'macOS 64-bit',
        'http://download.springsource.com/release/STS4/4.1.1.RELEASE/dist/e4.10/spring-tool-suite-4-4.1.1.RELEASE-e4.10.0-macosx.cocoa.x86_64.dmg'),
       ('eclipse', 'linux', 'Linux 64-bit',
        'http://download.springsource.com/release/STS4/4.1.1.RELEASE/dist/e4.10/spring-tool-suite-4-4.1.1.RELEASE-e4.10.0-linux.gtk.x86_64.tar.gz'),
       ('vscode', 'marketplace', 'VSCode Marketplace',
        'https://marketplace.visualstudio.com/items?itemName=Pivotal.vscode-boot-dev-pack'),
       ('theia', 'package', 'Package for Theia',
        'https://registry.npmjs.org/@pivotal-tools/theia-spring-boot/-/theia-spring-boot-1.8.0.tgz');