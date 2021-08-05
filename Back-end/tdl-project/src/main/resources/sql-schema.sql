
DROP TABLE IF EXISTS `to_dos`;

CREATE TABLE IF NOT EXISTS `to_dos` (
    `todos_id` BIGINT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(255) NOT NULL,
    `completed` BOOLEAN NOT NULL,
    PRIMARY KEY (`todos_id`)
);
