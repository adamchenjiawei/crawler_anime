CREATE TABLE `anime_images` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `current_page` int(11) DEFAULT '0' COMMENT '当前页数',
  `total_page` int(11) DEFAULT '0' COMMENT '总页数',
  `image_url` varchar(255) DEFAULT '' COMMENT '漫画图片',
  `chapter_id` int(11) NOT NULL DEFAULT '0' COMMENT '章节ID',
  `hash_url` varchar(64) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `hash_url_for_index` (`hash_url`)
) ENGINE=InnoDB AUTO_INCREMENT=2899 DEFAULT CHARSET=utf8;

CREATE TABLE `chapters` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `anime_name` varchar(255) DEFAULT NULL COMMENT '漫画名',
  `chapter_name` varchar(255) DEFAULT '' COMMENT '章节名称',
  `position` int(11) DEFAULT '0' COMMENT '位置',
  `url` varchar(255) DEFAULT '' COMMENT '章回的url',
  `hash_url` varchar(64) DEFAULT '' COMMENT 'Hash url',
  PRIMARY KEY (`id`),
  KEY `hash_url_for_index` (`hash_url`)
) ENGINE=InnoDB AUTO_INCREMENT=463 DEFAULT CHARSET=utf8;

CREATE TABLE `chuixue` (
  `id` varchar(64) NOT NULL,
  `anime_name` varchar(128) DEFAULT NULL COMMENT '漫画名',
  `chapter` varchar(64) DEFAULT NULL COMMENT '当前章节',
  `current_page` int(5) DEFAULT NULL COMMENT '当前页数',
  `total_page` int(5) DEFAULT NULL COMMENT '总页数',
  `image_url` varchar(256) DEFAULT NULL COMMENT '漫画图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;