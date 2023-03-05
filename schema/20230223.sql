drop table oneonone_counsel;
CREATE TABLE `oneonone_counsel` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`user_name`	varchar(30)	NULL,
	`phone_no`	varchar(13)	NULL,
	`email`	varchar(50)	NULL,
	`reservation_id`	bigint	NULL,
	`views`	int	NOT NULL,
	`counsel_status_code`	varchar(30)	NOT NULL,
	`oneonone_counsel_type_code`	varchar(30)	NOT NULL,
	`visibled`	tinyint	NOT NULL	DEFAULT 0,
	`user_id`	bigint	NOT NULL,
	`brand_id`	bigint	NOT NULL,
	`counsel_case_code`	varchar(30)	NOT NULL,
	`contents`	text	NULL,
	`title`	varchar(300)	NULL,
	`answer`	text	NULL,
	`answer_id`	bigint	NOT NULL,
	`created_at`	datetime	NULL,
	`modified_at`	datetime	NULL
);