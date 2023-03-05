
drop table counsel;
CREATE TABLE `counsel` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`user_name`	varchar(30)	NULL,
	`phone_no`	varchar(13)	NULL,
	`email`	varchar(50)	NULL,
	`reservation_id`	bigint	NULL,
	`views`	int	NOT NULL,
	`counsel_status_code`	varchar(30)	NOT NULL,
	`counsel_type_code`	varchar(30)	NOT NULL,
	`visibled`	tinyint	NOT NULL	DEFAULT 0,
	`user_id`	bigint	NOT NULL,
	`contents`	text	NULL,
	`created_at`	datetime	NOT NULL,
	`modified_at`	datetime	NULL,
	`modified_user_id`	bigint	NOT NULL
);

drop table users;
CREATE TABLE `users` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`brand_id`	bigint	NOT NULL,
	`signup_case_code`	varchar(30)	NOT NULL,
	`user_status_code`	varchar(30)	NOT NULL,
	`allowed_marketting_code`	varchar(30)	NOT NULL,
	`user_id`	varchar(300)	NOT NULL,
	`password`	varchar(512)	NULL,
	`name`	varchar(30)	NULL,
	`first_name_eng`	varchar(30)	NULL,
	`last_name_eng`	varchar(30)	NULL,
	`phone_no`	varchar(13)	NULL,
	`mobile_phone_no`	varchar(13)	NULL,
	`email`	varchar(50)	NULL,
	`created_at`	datetime	NOT NULL,
	`deleted`	tinyint	NOT NULL	DEFAULT 0,
	`deleted_at`	datetime	NULL,
	`travel_corp_id`	bigint	NOT NULL,
	`corp_detail_id`	bigint	NOT NULL
);

drop table common_code;
CREATE TABLE `common_code` (
	`code`	varchar(30)	NOT NULL,
	`group_code`	varchar(30)	NOT NULL,
	`name`	varchar(30)	NOT NULL,
	`used`	tinyint	NOT NULL	DEFAULT 0,
	`orders`	int	NOT NULL	DEFAULT 0,
	`created_at`	datetime	NOT NULL,
	`modified_at`	datetime	NULL,
	`modifier_id`	bigint	NOT NULL
);

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

drop table brand;
CREATE TABLE `brand` (
	`id`	bigint	NOT NULL
);

drop table frequently_answer;
CREATE TABLE `frequently_answer` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`title`	varchar(300)	NOT NULL,
	`contents`	text	NULL,
	`usabled`	tinyint	NOT NULL	DEFAULT 0,
	`modified_at`	datetime	NULL,
	`modified_user_id`	bigint	NOT NULL
);

drop table faqs;
CREATE TABLE `faqs` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`faq_type_code`	varchar(30)	NOT NULL,
	`title`	varchar(300)	NOT NULL,
	`contents`	text	NULL,
	`created_at`	datetime	NOT NULL,
	`modified_at`	datetime	NULL,
	`modified_user_id`	bigint	NOT NULL,
	`orders`	int	NOT NULL	DEFAULT 0,
	`views`	int	NOT NULL,
	`visibled`	tinyint	NOT NULL	DEFAULT 0
);

drop table cars;
CREATE TABLE `cars` (
	`id`	bigint	NOT NULL
);

drop table reservations;
CREATE TABLE `reservations` (
	`id`	bigint	NOT NULL
);

drop table branchs;
CREATE TABLE `branchs` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`national_authems_code`	varchar(30)	NOT NULL,
	`stats_code`	varchar(30)	NOT NULL,
	`citys_code`	varchar(30)	NOT NULL,
	`visabled`	tinyint	NOT NULL	DEFAULT 0,
	`name_eng`	varchar(300)	NULL,
	`name`	varchar(300)	NULL,
	`pickup_eng`	varchar(300)	NULL,
	`pickup`	varchar(300)	NULL,
	`opened_times`	text	NULL
);

drop table main_contents;
CREATE TABLE `main_contents` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`main_contents_type_code`	varchar(30)	NOT NULL,
	`orders`	int	NOT NULL	DEFAULT 0,
	`modified_at`	datetime	NULL,
	`modified_user_id`	bigint	NOT NULL
);

drop table main_contents_detail;
CREATE TABLE `main_contents_detail` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`main_contents_id`	bigint	NOT NULL,
	`menu_name`	varchar(30)	NULL,
	`efective_started_at`	datetime	NULL,
	`efective_ended_at`	datetime	NULL,
	`image`	varchar(300)	NULL,
	`url`	varchar(300)	NULL,
	`alt`	tinytext	NULL,
	`orders`	int	NOT NULL	DEFAULT 0
);

drop table normal_contents;
CREATE TABLE `normal_contents` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`normal_contents_exhibition_type_code`	varchar(30)	NOT NULL,
	`orders`	int	NOT NULL	DEFAULT 0,
	`modified_at`	datetime	NULL,
	`modified_user_id`	bigint	NOT NULL,
	`visabled`	tinyint	NOT NULL	DEFAULT 0,
	`contents`	text	NULL
);

drop table news_events;
CREATE TABLE `news_events` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`news_contents_type_code`	varchar(30)	NOT NULL,
	`orders`	int	NOT NULL	DEFAULT 0,
	`modified_at`	datetime	NULL,
	`modified_user_id`	bigint	NOT NULL,
	`visabled`	tinyint	NOT NULL	DEFAULT 0,
	`title`	varchar(300)	NULL,
	`contents`	text	NULL,
	`fixed`	tinyint	NOT NULL	DEFAULT 0,
	`efective_started_at`	datetime	NULL,
	`efective_ended_at`	datetime	NULL
);

drop table attachments;
CREATE TABLE `attachments` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`contents_type`	tinyint	NULL,
	`contents_id`	bigint	NULL,
	`url`	varchar(300)	NULL
);

drop table terms;
CREATE TABLE `terms` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`terms_type_code`	varchar(30)	NOT NULL,
	`orders`	int	NOT NULL	DEFAULT 0,
	`modified_at`	datetime	NULL,
	`modified_user_id`	bigint	NOT NULL,
	`visabled`	tinyint	NOT NULL	DEFAULT 0,
	`contents`	text	NULL,
	`terms_case_code`	varchar(30)	NOT NULL
);

drop table popups;
CREATE TABLE `popups` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`popups_type_code`	varchar(30)	NOT NULL,
	`orders`	int	NOT NULL	DEFAULT 0,
	`modified_at`	datetime	NULL,
	`modified_user_id`	bigint	NOT NULL,
	`visabled`	tinyint	NOT NULL	DEFAULT 0,
	`title`	varchar(300)	NULL,
	`contents`	text	NULL,
	`fixed`	tinyint	NOT NULL	DEFAULT 0,
	`efective_started_at`	datetime	NULL,
	`efective_ended_at`	datetime	NULL,
	`check_started_at`	datetime	NULL,
	`check_ended_at`	datetime	NULL
);

drop table reservation_guides;
CREATE TABLE `reservation_guides` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`reservations_exhibition_type_code`	varchar(30)	NOT NULL,
	`reservation_guide_code`	varchar(30)	NOT NULL,
	`payment_type_code`	varchar(30)	NOT NULL,
	`visabled`	tinyint	NOT NULL	DEFAULT 0,
	`modified_at`	datetime	NULL,
	`modified_user_id`	bigint	NOT NULL,
	`contents`	text	NULL
);

drop table usage_guides;
CREATE TABLE `usage_guides` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`usage_guides_type_code`	varchar(30)	NOT NULL,
	`visabled`	tinyint	NOT NULL	DEFAULT 0,
	`modified_at`	datetime	NULL,
	`modified_user_id`	bigint	NOT NULL,
	`title`	varchar(300)	NOT NULL,
	`contents`	text	NULL,
	`orders`	int	NOT NULL	DEFAULT 0,
	`views`	int	NOT NULL
);

drop table citys;
CREATE TABLE `citys` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`national_authems_code`	varchar(30)	NOT NULL,
	`stats_code`	varchar(30)	NOT NULL,
	`visabled`	tinyint	NOT NULL	DEFAULT 0,
	`name_eng`	varchar(300)	NULL,
	`name`	varchar(300)	NULL,
	`code`	varchar(30)	NULL
);

drop table states;
CREATE TABLE `states` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`national_authems_code`	varchar(30)	NOT NULL,
	`visabled`	tinyint	NOT NULL	DEFAULT 0,
	`name_eng`	varchar(300)	NULL,
	`name`	varchar(300)	NULL,
	`code`	varchar(30)	NULL
);

drop table national_authems;
CREATE TABLE `national_authems` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`visabled`	tinyint	NOT NULL	DEFAULT 0,
	`name_eng`	varchar(300)	NULL,
	`name`	varchar(300)	NULL,
	`code`	varchar(30)	NULL
);

drop table login_history;
CREATE TABLE `login_history` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`userid`	varchar(300)	NOT NULL,
	`password`	varchar(512)	NULL,
	`successed`	tinyint	NOT NULL	DEFAULT 0,
	`created_at`	datetime	NOT NULL
);

drop table travel_corps;
CREATE TABLE `travel_corps` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`travel_no`	varchar(255)	NULL,
	`business_no`	varchar(11)	NULL,
	`name`	varchar(30)	NOT NULL,
	`director_name`	varchar(30)	NULL,
	`director_phone_no`	varchar(13)	NULL,
	`fee_rate`	int	NULL	COMMENT '10^4 으로 곱하여 int 저장'
);

drop table corp_details;
CREATE TABLE `corp_details` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`department_code`	varchar(30)	NOT NULL,
	`tax_issued_code`	varchar(30)	NOT NULL,
	`email`	varchar(50)	NULL,
	`bank_name`	varchar(30)	NULL,
	`account_name`	varchar(30)	NULL,
	`account_no`	varchar(300)	NULL
);

drop table settlements;
CREATE TABLE `settlements` (
	`id`	bigint	NOT NULL
);

drop table notifications_history;
CREATE TABLE `notifications_history` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`notification_id`	bigint	NOT NULL,
	`user_id`	bigint	NOT NULL,
	`created_at`	datetime	NOT NULL,
	`viewed`	tinyint	NOT NULL	DEFAULT 0
);

drop table notifications;
CREATE TABLE `notifications` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`title`	varchar(300)	NULL,
	`contents`	text	NULL,
	`created_at`	datetime	NULL,
	`sender_id`	bigint	NOT NULL
);

drop table files;
CREATE TABLE `files` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`name`	varchar(30)	NOT NULL,
	`origin_name`	varchar(300)	NOT NULL,
	`web_path`	varchar(300)	NOT NULL,
	`file_type`	varchar(30)	NOT NULL
);

drop table services;
CREATE TABLE `services` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`brand_id`	bigint	NOT NULL,
	`service_type_code`	varchar(30)	NOT NULL,
	`visabled`	tinyint	NOT NULL	DEFAULT 0,
	`reservate_efective_started_at`	datetime	NOT NULL,
	`reservate_efective_ended_at`	datetime	NULL,
	`rent_efective_started_at`	datetime	NULL,
	`rent_efective_ended_at`	datetime	NULL,
	`created_at`	datetime	NOT NULL,
	`contract_id`	varchar(300)	NULL,
	`product_code`	varchar(300)	NULL,
	`iata`	varchar(300)	NULL,
	`corp_diccount_number`	varchar(300)	NULL,
	`name`	varchar(30)	NULL
);

drop table service_options;
CREATE TABLE `service_options` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`service_id`	bigint	NOT NULL,
	`option_type`	tinyint	NOT NULL	DEFAULT 0,
	`option_code`	varchar(30)	NOT NULL,
	`used`	tinyint	NOT NULL	DEFAULT 0
);

drop table service_area;
CREATE TABLE `service_area` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`service_id`	bigint	NOT NULL,
	`national_authems_code`	varchar(30)	NOT NULL,
	`stats_code`	varchar(30)	NOT NULL,
	`citys_code`	varchar(30)	NOT NULL,
	`branch_code`	varchar(30)	NOT NULL
);

drop table service_detail;
CREATE TABLE `service_detail` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`brand_id`	bigint	NOT NULL,
	`name`	varchar(300)	NULL,
	`name_eng`	varchar(300)	NULL,
	`description`	text	NULL,
	`visibled_help`	tinyint	NOT NULL	DEFAULT 0,
	`etc`	text	NULL
);

drop table service_discount;
CREATE TABLE `service_discount` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`service_id`	bigint	NOT NULL,
	`service_type_code`	varchar(30)	NOT NULL,
	`sells_type_code`	varchar(30)	NOT NULL,
	`visibled`	tinyint	NOT NULL	DEFAULT 0,
	`event_name`	varchar(300)	NULL,
	`discount_type`	tinyint	NOT NULL	DEFAULT 0,
	`reservate_efective_started_at`	datetime	NULL,
	`reservate_efective_ended_at`	datetime	NULL,
	`rent_efective_started_at`	datetime	NULL,
	`rent_efective_ended_at`	datetime	NULL
);

drop table service_discount_area;
CREATE TABLE `service_discount_area` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`service_discount_id`	bigint	NOT NULL,
	`national_authems_code`	varchar(30)	NOT NULL,
	`stats_code`	varchar(30)	NOT NULL,
	`citys_code`	varchar(30)	NOT NULL,
	`branch_code`	varchar(30)	NOT NULL
);

drop table common_group_code;
CREATE TABLE `common_group_code` (
	`group_code`	varchar(30)	NOT NULL,
	`group_name`	varchar(300)	NOT NULL,
	`used`	tinyint	NOT NULL	DEFAULT 0,
	`created_at`	datetime	NOT NULL,
	`modified_at`	datetime	NOT NULL,
	`modifier_id`	bigint	NOT NULL
);

drop table auth;
CREATE TABLE `auth` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`code`	int	NULL,
	`group_code`	varchar(30)	NULL,
	`name`	varchar(30)	NULL
);

drop table user_auth;
CREATE TABLE `user_auth` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`auth_id`	bigint	NOT NULL,
	`user_id`	bigint	NOT NULL
);

drop table notice;
CREATE TABLE `notice` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`brand_id`	bigint	NOT NULL,
	`title`	varchar(300)	NOT NULL,
	`contents`	text	NULL,
	`visibled`	tinyint	NOT NULL	DEFAULT 0,
	`fixied`	tinyint	NOT NULL	DEFAULT 0,
	`modified_at`	datetime	NULL,
	`modified_user_id`	bigint	NOT NULL,
	`views`	int	NOT NULL
);

drop table error_board;
CREATE TABLE `error_board` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`brand_id`	bigint	NOT NULL,
	`large_category`	varchar(300)	NOT NULL,
	`medium_category`	varchar(300)	NULL,
	`small_category`	varchar(300)	NULL,
	`created_at`	datetime	NULL,
	`contents`	text	NULL,
	`checked`	tinyint	NOT NULL	DEFAULT 0,
	`checker_id`	bigint	NOT NULL
);

drop table coupons;
CREATE TABLE `coupons` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`brand_id`	bigint	NOT NULL,
	`name`	varchar(300)	NOT NULL,
	`used`	tinyint	NOT NULL	DEFAULT 0,
	`head_office_coupon_code`	varchar(300)	NULL,
	`modified_at`	datetime	NULL,
	`modified_user_id`	bigint	NOT NULL,
	`coupon_type_code`	varchar(30)	NOT NULL,
	`coupon_issued_type_code`	varchar(30)	NOT NULL,
	`coupon_issued_condition_code`	varchar(30)	NOT NULL,
	`coupon_issued_to_code`	varchar(30)	NOT NULL,
	`rent_min_date`	int	NULL	DEFAULT 0,
	`rent_max_date`	int	NULL	DEFAULT 0,
	`reservate_efective_started_at`	datetime	NULL,
	`reservate_efective_ended_at`	datetime	NULL,
	`pickup_efective_started_at`	datetime	NULL,
	`pickup_efective_ended_at`	datetime	NULL,
	`duplicate_issued`	tinyint	NULL	DEFAULT 0,
	`remind_msg_send_allowed`	tinyint	NULL	DEFAULT 0,
	`notice`	text	NULL
);

drop table coupon_users;
CREATE TABLE `coupon_users` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`coupon_id`	bigint	NOT NULL,
	`user_id`	bigint	NOT NULL,
	`user_name`	varchar(30)	NULL,
	`user_phone`	varchar(13)	NULL,
	`user_email`	varchar(50)	NULL,
	`used`	tinyint	NOT NULL	DEFAULT 0,
	`reservated_at`	datetime	NULL,
	`pickup_at`	datetime	NULL,
	`reservation_id`	bigint	NOT NULL
);

drop table news_letters;
CREATE TABLE `news_letters` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`brand_id`	bigint	NOT NULL,
	`user_id`	bigint	NOT NULL,
	`user_name`	varchar(30)	NULL,
	`user_phone`	varchar(13)	NULL,
	`user_email`	varchar(50)	NULL,
	`created_at`	datetime	NULL,
	`last_sended_at`	datetime	NULL,
	`newsletter_status_code`	varchar(30)	NOT NULL
);

drop table coupon_used_history;
CREATE TABLE `coupon_used_history` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`coupon_id`	bigint	NOT NULL,
	`user_id`	bigint	NOT NULL,
	`user_name`	varchar(30)	NULL,
	`user_phone`	varchar(13)	NULL,
	`user_email`	varchar(50)	NULL,
	`used_at`	datetime	NULL,
	`reservation_id`	bigint	NOT NULL
);

drop table news_letters_history;
CREATE TABLE `news_letters_history` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`brand_id`	bigint	NOT NULL,
	`user_id`	bigint	NOT NULL,
	`user_name`	varchar(30)	NULL,
	`user_phone`	varchar(13)	NULL,
	`user_email`	varchar(50)	NULL,
	`created_at`	datetime	NULL,
	`newsletter_status_code`	varchar(30)	NOT NULL
);

drop table alimtalk_history;
CREATE TABLE `alimtalk_history` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`brand_id`	bigint	NOT NULL,
	`user_id`	bigint	NOT NULL,
	`user_name`	varchar(30)	NULL,
	`user_phone`	varchar(13)	NULL,
	`user_email`	varchar(50)	NULL,
	`created_at`	datetime	NULL,
	`alimtalk_send_code`	varchar(30)	NOT NULL,
	`contents`	text	NULL,
	`button_name`	varchar(30)	NULL,
	`button_link`	varchar(300)	NULL,
	`reservate_id`	bigint	NOT NULL
);

drop table push;
CREATE TABLE `push` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`brand_id`	bigint	NOT NULL,
	`push_type_code`	varchar(30)	NOT NULL,
	`sended_at`	datetime	NULL,
	`title`	varchar(300)	NOT NULL,
	`created_at`	datetime	NULL,
	`modifier_id`	bigint	NOT NULL,
	`modified_at`	datetime	NULL,
	`contents`	text	NOT NULL,
	`image`	varchar(300)	NULL,
	`link`	varchar(300)	NULL,
	`os_type_code`	varchar(30)	NOT NULL,
	`push_target_type_code`	varchar(30)	NOT NULL
);

drop table push_users;
CREATE TABLE `push_users` (
	`id`	bigint	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`push_id`	bigint	NOT NULL,
	`brand_id`	bigint	NOT NULL,
	`brand_name`	varchar(300)	NULL,
	`user_id`	bigint	NOT NULL,
	`user_uid`	varchar(50)	NULL
);
