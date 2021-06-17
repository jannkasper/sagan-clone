CREATE TABLE member_profile (
  id                   INT AUTO_INCREMENT PRIMARY KEY,
  avatar_url           VARCHAR(255),
  bio                  VARCHAR(2048),
  latitude             FLOAT,
  longitude            FLOAT,
  github_id            BIGINT,
  github_username      VARCHAR(128),
  gravatar_email       VARCHAR(128),
  hidden               BOOLEAN,
  lanyrd_username      VARCHAR(128),
  location             VARCHAR(64),
  name                 VARCHAR(128),
  speakerdeck_username VARCHAR(128),
  twitter_username     VARCHAR(128),
  username             VARCHAR(128) NOT NULL,
  video_embeds         VARCHAR(255),
  job_title            VARCHAR(128)
);

CREATE TABLE post (
  id               INT AUTO_INCREMENT PRIMARY KEY,
  broadcast        BOOLEAN                NOT NULL,
  category         VARCHAR(128) NOT NULL,
  created_at       TIMESTAMP              NOT NULL,
  draft            BOOLEAN                NOT NULL,
  format           VARCHAR(128),
  public_slug      VARCHAR(255) UNIQUE,
  publish_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  raw_content      LONGTEXT                NOT NULL,
  rendered_content LONGTEXT                NOT NULL,
  rendered_summary LONGTEXT                NOT NULL,
  title            VARCHAR(255) NOT NULL,
  author_id        INTEGER                NOT NULL REFERENCES member_profile (id)
);