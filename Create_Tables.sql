CREATE TABLE `campaigns` (
  `id` int(11) NOT NULL auto_increment,
  `campaign_name` varchar(128) NOT NULL,
  `country_id` int(4) NOT NULL,
  `client_id` int(4) NOT NULL,
  `current_entry_count` int(11) NOT NULL,
  `estimated_entry_count` int(11) DEFAULT NULL,
  `positive_responses` int(11) DEFAULT NULL,
  `negative_responses` int(11) NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `users_id` int(11) not null,
  PRIMARY KEY (`id`),
  foreign key (`country_id`)
  references `countries`(`country_id`),
  foreign key (`client_id`)
  references `clients`(`client_id`),
  foreign key (`users_id`)
  references `users`(`id`)
);

create table `countries`(
	`country_id` int(4) NOT NULL auto_increment,
    `country_name` varchar(128) not null,
    primary key (`country_id`)
);

create table `clients`(
	`client_id` int(128) not null auto_increment,
    `client_name` varchar(128) not null,
    primary key (`client_id`)
);

CREATE TABLE `campaign_profiles` (
  `id` int(11) not null auto_increment,
  `campaign_id` int(11) NOT NULL,
  `profile_id` int(11) NOT NULL,
  `valid_entry_count` int(11) not null,
  `invalid_entry_count` int(11) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  foreign key (`campaign_id`)
  references `campaigns`(`id`)
);

create table `campaign_entries`(
	`entry_id` int(11) not null auto_increment,
    `campaign_id` int(11) not null,
    `profile_id` int(11) not null,
    `valid_entry` bool not null,
    primary key (`entry_id`),
    foreign key (`campaign_id`)
    references `campaigns`(`id`)
);

create table `users`(
	`id` int(11) NOT NULL auto_increment,
    `username` varchar(128) NOT NULL,
    `email` varchar(128) NOT NULL,
    `is_Admin` bool null,
    PRIMARY KEY (`id`)
);