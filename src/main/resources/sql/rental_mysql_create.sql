CREATE TABLE `car_status` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `user` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(50) NOT NULL,
	`firstname` VARCHAR(50) NOT NULL,
	`surname` VARCHAR(50) NOT NULL,
	`email` VARCHAR(100) NOT NULL,
	`password` VARCHAR(255) NOT NULL,
	`passport_number` VARCHAR(13) NOT NULL,
	PRIMARY KEY (`id`)
);


CREATE TABLE `order_status` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `repair_payment` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`value` DOUBLE NOT NULL,
	`description` VARCHAR(1000) NOT NULL,
	`order_id` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `car` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`mark` VARCHAR(255) NOT NULL,
	`model` VARCHAR(255) NOT NULL,
	`car_status_id` INT NOT NULL,
	`cost_for_one_hour` INT NOT NULL,
	PRIMARY KEY (`id`)
);


CREATE TABLE `order` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`order_date` DATETIME NOT NULL,
	`rental_start` DATETIME NOT NULL,
	`rental_end` DATETIME NOT NULL,
	`order_status_id` INT NOT NULL,
	`payment_id` INT NOT NULL,
	`user_id` INT NOT NULL,
	`car_id` INT NOT NULL,
	`payment_value` DOUBLE NOT NULL,
	`order_info` VARCHAR(1000) NOT NULL,
	`employee_name` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`id`)
);

  CREATE TABLE `role` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`));

    CREATE TABLE `user_role` (
      `id` INT NOT NULL AUTO_INCREMENT,
      `user_id` INT NOT NULL,
      `role_id` INT NULL,
      PRIMARY KEY (`id`));



ALTER TABLE `repair_payment` ADD CONSTRAINT `repair_payment_fk0` FOREIGN KEY (`order_id`) REFERENCES `order`(`id`);

ALTER TABLE `car` ADD CONSTRAINT `car_fk0` FOREIGN KEY (`car_status_id`) REFERENCES `car_status`(`id`);

ALTER TABLE `order` ADD CONSTRAINT `order_fk0` FOREIGN KEY (`order_status_id`) REFERENCES `order_status`(`id`);

ALTER TABLE `order` ADD CONSTRAINT `order_fk2` FOREIGN KEY (`user_id`) REFERENCES `user`(`id`);

ALTER TABLE `order` ADD CONSTRAINT `order_fk4` FOREIGN KEY (`car_id`) REFERENCES `car`(`id`);

ALTER TABLE `user_role` ADD CONSTRAINT `user_role_fk0` FOREIGN KEY (`user_id`) REFERENCES `user`(`id`);

ALTER TABLE `user_role` ADD CONSTRAINT `user_role_fk1` FOREIGN KEY (`role_id`) REFERENCES `role`(`id`);









