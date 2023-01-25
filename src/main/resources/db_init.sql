
//STANDARD_USERS
insert into STANDARD_USERS values('1', 'Jan', 'Kowalski', 'jan.kowalski@mail.com', '#$fvxy8&df$%dfg24$#%', '1');
insert into STANDARD_USERS values('2', 'Anna', 'Kowalska', 'anna.kowalska@mail.com', '34534#$#%df$%dfg24$#%', '1');
insert into STANDARD_USERS values('3', 'Stanisław', 'Nowak', 'stan.kowal@mail.com', '#$fvxy8&854dfg24$#%', '1');
insert into STANDARD_USERS values('4', 'Władysław', 'Kowal', 'wladyslaw.k@mail.com', '#$fvxy345345$%dfg24$#%', '1');
insert into STANDARD_USERS values('5', 'Jacek', 'Michaluk', 'jacek.michaluk@mail.com', '#U12376545$%dhf54$#%', '1');

//CATEGORIES
insert into CATEGORIES values('1', 'true', 'sport');
insert into CATEGORIES values('2', 'true', 'kulinaria');
insert into CATEGORIES values('3', 'true', 'programowanie');
insert into CATEGORIES values('4', 'false', 'taniec');

//COORDINATES
insert into COORDINATES values('1', '154541.2132', '484454545.23');

//SECTIONS
insert into SECTIONS values('1', 'first section', 'true', 'first section', '1');
insert into SECTIONS values('2', 'second section', 'true', 'second section', '2');
insert into SECTIONS values('3', 'third section', 'true', 'third section', '3');
insert into SECTIONS values('4', 'fourth section', 'false', 'fourth section', '4');

//SECTIONS EVENTS
insert into SECTIONS_EVENTS values('1', '1');
insert into SECTIONS_EVENTS values('2', '2');
insert into SECTIONS_EVENTS values('3', '3');

//SECTION JOINED USERS
insert into SECTION_JOINED_USERS values('1', '1');
insert into SECTION_JOINED_USERS values('2', '2');
insert into SECTION_JOINED_USERS values('3', '3');

//EVENT_RESPONSES
INSERT INTO event_responses VALUES (1 , 'Accepted');
INSERT INTO event_responses VALUES (2 , 'Declined');
INSERT INTO event_responses VALUES (3 , 'Undecided');

//EVENTS
insert into EVENTS values('1', '2023-03-12 18:47:52.69', '2023-03-12 20:47:52.69', 'first event', 'link', 'first event', 1, 'false', 'false', 'false', 'false', 'location', '50', '1', '1', '1');
insert into EVENTS values('2', '2023-02-12 17:47:52.69', '2023-02-12 19:47:52.69', 'second event', 'link', 'second event', 2, 'false', 'false', 'false', 'false', 'location', '20', '2', '1', '1');
insert into EVENTS values('3', '2023-09-12 16:47:52.69', '2023-09-12 18:47:52.69', 'third event', 'link', 'third event', 2, 'false', 'false', 'false', 'false', 'location', '40', '3', '1', '1');
insert into EVENTS values('4', '2023-01-12 15:47:52.69', '2023-01-12 17:47:52.69', 'fourth event', 'link', 'fourth event', 1, 'false', 'false', 'false', 'false', 'location', '20', '1', '1', '1');
insert into EVENTS values('5', '2022-09-10 14:47:52.69', '2022-09-10 16:47:52.69', 'fifth event', 'link', 'fifth event', 1, 'false', 'false', 'false', 'true', 'location', '35', '2', '1', '1');
insert into EVENTS values('6', '2022-08-10 13:47:52.69', '2022-08-10 15:47:52.69', '6 event', 'link', '6 event', 1, 'false', 'false', 'false', 'false', 'location', '35', '2', '1', '1');
insert into EVENTS values('7', '2022-07-10 12:47:52.69', '2022-07-10 14:47:52.69', '7 event', 'link', '7 event', 1, 'false', 'false', 'false', 'false', 'location', '35', '2', '1', '1');

//events_x_users_responses
INSERT INTO events_x_users_responses VALUES (1 ,1 ,1);
INSERT INTO events_x_users_responses VALUES (1 ,3 ,2);
INSERT INTO events_x_users_responses VALUES (2 ,3 ,1);
INSERT INTO events_x_users_responses VALUES (2 ,2 ,2);
INSERT INTO events_x_users_responses VALUES (3 ,3 ,1);
INSERT INTO events_x_users_responses VALUES (3 ,2 ,2);
INSERT INTO events_x_users_responses VALUES (4 ,3 ,1);
INSERT INTO events_x_users_responses VALUES (4 ,1 ,2);
INSERT INTO events_x_users_responses VALUES (5 ,1 ,1);
INSERT INTO events_x_users_responses VALUES (5 ,2 ,2);

//ADMINS
insert into ADMINS values('1001','Marek','Głowienka','maro.mareczek@mail.com','234KJDdddJKSW23@@','2');
insert into ADMINS values('1002','Agnieszka','Górka','aga.gorka@mail.com','532##ddSSAe4QQ','2');

//HASHTAGS
insert into HASHTAGS values('2','71','false','Nudy');
insert into HASHTAGS values('1','53','true','AleDymy');
insert into HASHTAGS values('3','53','true','Celebrujmy');

//ATTACHMENTS
insert into ATTACHMENTS values('1','www.wbijajnatenlink.pl');
insert into ATTACHMENTS values('2','www.alekozackilink.pl');

//REQUEST_STATUSES
insert into REQUEST_STATUSES values('1','accepted');
insert into REQUEST_STATUSES values('2','declined');

//REQUEST_BOXES
insert into REQUEST_BOXES values('1');
insert into REQUEST_BOXES values('2');
insert into REQUEST_BOXES values('3');

//REQUEST_BOXES_REQUESTS
insert into REQUEST_BOXES_REQUESTS values('1','1');
insert into REQUEST_BOXES_REQUESTS values('1','2');
insert into REQUEST_BOXES_REQUESTS values('1','3');
insert into REQUEST_BOXES_REQUESTS values('1','4');
insert into REQUEST_BOXES_REQUESTS values('2','5');
insert into REQUEST_BOXES_REQUESTS values('1','6');
insert into REQUEST_BOXES_REQUESTS values('1','7');
insert into REQUEST_BOXES_REQUESTS values('1','8');
insert into REQUEST_BOXES_REQUESTS values('1','9');
insert into REQUEST_BOXES_REQUESTS values('1','10');

//NOTIFICATION_TYPES
insert into NOTIFICATION_TYPES values('1','DELETE');
insert into NOTIFICATION_TYPES values('2','EDIT');
insert into NOTIFICATION_TYPES values('3','EDIT DATE');
insert into NOTIFICATION_TYPES values('4','EDIT LOCATION');
insert into NOTIFICATION_TYPES values('5','EDIT TIME');
insert into NOTIFICATION_TYPES values('6','CHANGE OWNERSHIP');
insert into NOTIFICATION_TYPES values('7','MENTIONED');
insert into NOTIFICATION_TYPES values('8','QUOTED');
insert into NOTIFICATION_TYPES values('9','ACCEPTED REQUEST');
insert into NOTIFICATION_TYPES values('10','DECLINE REQUEST');
insert into NOTIFICATION_TYPES values('11','RESPONSE REQUIRED');

//NOTIFICATION_CATEGORIES
insert into NOTIFICATION_CATEGORIES values('1','sport');
insert into NOTIFICATION_CATEGORIES values('2','impreza');
insert into NOTIFICATION_CATEGORIES values('3','modlitwa');

//NOTIFICATIONS_TYPES
insert into NOTIFICATIONS_TYPES values('1','1');
insert into NOTIFICATIONS_TYPES values('2','2');
insert into NOTIFICATIONS_TYPES values('3','3');
insert into NOTIFICATIONS_TYPES values('4','4');
insert into NOTIFICATIONS_TYPES values('5','5');
insert into NOTIFICATIONS_TYPES values('6','6');
insert into NOTIFICATIONS_TYPES values('7','7');
insert into NOTIFICATIONS_TYPES values('8','8');
insert into NOTIFICATIONS_TYPES values('9','9');
insert into NOTIFICATIONS_TYPES values('10','10');
insert into NOTIFICATIONS_TYPES values('11','11');

//EVENT_HASHTAGS
insert into EVENT_HASHTAGS values('1','1');
insert into EVENT_HASHTAGS values('4','2');

//NOTIFICATIONS_EVENT
insert into NOTIFICATIONS_EVENT values('1','2023-07-11 20:47:52.69','true','true','1','1','1');
insert into NOTIFICATIONS_EVENT values('2','2023-07-11 20:46:52.69','true','false','2','3','2');
insert into NOTIFICATIONS_EVENT values('3','2023-07-11 20:45:52.69','false','false','3','4','3');

//NOTIFICATIONS_GENERAL
insert into NOTIFICATIONS_GENERAL values('1','2023-07-11 20:45:52.69','true','true','1','1');
insert into NOTIFICATIONS_GENERAL values('2','2023-07-11 20:45:52.69','true','false','2','3');
insert into NOTIFICATIONS_GENERAL values('3','2023-07-11 20:45:52.69','false','false','3','4');

//NOTIFICATIONS_GROUP
insert into NOTIFICATIONS_GROUP values('1','2023-07-11 20:45:52.69','true','true','1','1','1');
insert into NOTIFICATIONS_GROUP values('2','2023-07-11 20:45:52.69','true','false','2','3','2');
insert into NOTIFICATIONS_GROUP values('3','2023-07-11 20:45:52.69','false','false','3','4','3');

//GUESTS
insert into GUESTS values('1','Norbert','Gierczak','distork@mail.pl');
insert into GUESTS values('2','Marcin','Majkut','xayoo777@mail.com');
insert into GUESTS values('3','Krzysztof','Sauć','golemztopa@mail.pl');
insert into GUESTS values('4','Jakub','Turewicz','hardstuckwd2@mail.com');
insert into GUESTS values('5','Jakub','Cinkrof','toniezlylec@mail.com');

//RECURRING EVENTs
insert into RECURRING_EVENTS values('1','15','2');
insert into RECURRING_EVENTS values('2','0','3');
insert into RECURRING_EVENTS values('3','32','0');

//RECURRING EVENTS EVENTS
insert into RECURRING_EVENTS_X_EVENTS values('1','1');
insert into RECURRING_EVENTS_X_EVENTS values('2','3');
insert into RECURRING_EVENTS_X_EVENTS values('3','5');

//SECTION RECURRING EVENTS
insert into SECTION_RECURRING_EVENTS values('1', '1');
insert into SECTION_RECURRING_EVENTS values('2', '2');
insert into SECTION_RECURRING_EVENTS values('3', '3');

//EVENTs ATTACHMENTS
insert into EVENTS_X_ATTACHMENTS values('1', '1');
insert into EVENTS_X_ATTACHMENTS values('2', '2');

//EVENTs INVITED USERS
insert into EVENTS_X_INVITED_USERS values('1', '1');
insert into EVENTS_X_INVITED_USERS values('2', '2');
insert into EVENTS_X_INVITED_USERS values('3', '3');

//EVENTs JOINED GUESTS
insert into EVENTS_X_JOINED_GUESTS values('1', '1');
insert into EVENTS_X_JOINED_GUESTS values('2', '2');
insert into EVENTS_X_JOINED_GUESTS values('3', '3');

//SURVEYS
insert into SURVEYS values('1', '1','QUESTION1','1');
insert into SURVEYS values('2', '2','QUESTION2','2');
insert into SURVEYS values('3', '3','QUESTION3','3');

//SURVEY CHOICES
insert into SURVEY_CHOICES values('1', 'ANKIETA1');
insert into SURVEY_CHOICES values('2', 'ANKIETA2');
insert into SURVEY_CHOICES values('3', 'ANKIETA3');

//SURVEYS CHOICES
insert into SURVEYS_CHOICES values('1', '1');
insert into SURVEYS_CHOICES values('2', '2');
insert into SURVEYS_CHOICES values('3', '3');
insert into SURVEYS_CHOICES values('1', '2');
insert into SURVEYS_CHOICES values('1', '3');

//SURVEY RESPONSES
insert into SURVEY_RESPONSES values('1', '1');
insert into SURVEY_RESPONSES values('2', '2');
insert into SURVEY_RESPONSES values('3', '3');
insert into SURVEY_RESPONSES values('4', '2');
insert into SURVEY_RESPONSES values('5', '3');

//SURVEYS RESPONSES
insert into SURVEYS_RESPONSES values('1', '1');
insert into SURVEYS_RESPONSES values('2', '2');
insert into SURVEYS_RESPONSES values('1', '4');
insert into SURVEYS_RESPONSES values('1', '5');

//SURVEY RESPONSES ANSWERS
insert into SURVEY_RESPONSES_ANSWERS values('1', '1');
insert into SURVEY_RESPONSES_ANSWERS values('2', '2');
insert into SURVEY_RESPONSES_ANSWERS values('3', '3');
insert into SURVEY_RESPONSES_ANSWERS values('4', '2');
insert into SURVEY_RESPONSES_ANSWERS values('5', '2');

//EVENTS SURVEYS
insert into EVENTS_X_SURVEYS values('1', '1');
insert into EVENTS_X_SURVEYS values('2', '2');
insert into EVENTS_X_SURVEYS values('3', '3');

//EVENT COMMENTS
insert into EVENT_COMMENTS(COMMENT_ID,ADDING_DATE_TIME, COMMENT, OWNER_ID, REPLIED_COMMENT_ID) values('1','2023-02-15 17:45:52.69','comment1','1','1');
insert into EVENT_COMMENTS values('2', '2023-01-02 20:45:52.69','comment2','2','2','1');
insert into EVENT_COMMENTS values('3', '2023-01-05 20:45:52.69','comment3','3','3','2');
insert into EVENT_COMMENTS(COMMENT_ID,ADDING_DATE_TIME, COMMENT, OWNER_ID, REPLIED_COMMENT_ID) values('4','2023-02-15 17:45:52.69','comment4','1','1');

//EVENT COMMENTS ATTACHMENTS
insert into EVENT_COMMENTS_ATTACHMENTS  values('1', '1');
insert into EVENT_COMMENTS_ATTACHMENTS  values('1', '2');

//REQUEST COMMENTS
insert into  REQUEST_COMMENTS values('1', '2023-01-01 16:45:52.69','request comment1','1','1');
insert into  REQUEST_COMMENTS values('2', '2023-01-02 17:45:52.69','request comment2','2','2');
insert into  REQUEST_COMMENTS values('3', '2023-01-03 18:45:52.69','request comment3','3','3');

//SINGLE EVENT COMMENTS
--insert into  SINGLE_EVENT_COMMENTS values('1','1');
--insert into  SINGLE_EVENT_COMMENTS values('2','2');

//users_x_events_responses
//BUG/BUG/BUG
insert into  users_x_events_responses  values('1','1','1');
insert into  users_x_events_responses  values('2','2','2');

//USERS JOINED GROUPS
--insert into  USERS_JOINED_GROUPS   values('1','1');
--insert into  USERS_JOINED_GROUPS   values('2','1');
--insert into  USERS_JOINED_GROUPS   values('3','2');

//USERS NOTIFICATIONS
--insert into  USERS_NOTIFICATIONS    values('1','1');
--insert into  USERS_NOTIFICATIONS    values('2','2');
--
//USERS OWNED EVENTS
--insert into  USERS_OWNED_EVENTS     values('1','1');
--insert into  USERS_OWNED_EVENTS     values('2','2');
--
//USERS OWNED GROUPS
--insert into  USERS_OWNED_GROUPS      values('1','1');
--insert into  USERS_OWNED_GROUPS      values('2','2');

//REQUESTS_CATEGORY
insert into REQUESTS_CATEGORY (REQUEST_ID, REQUESTER_ID, NAME) values ('1', '1', 'taniec')
insert into REQUESTS_CATEGORY (REQUEST_ID, REQUESTER_ID, NAME) values ('2', '1', 'sport')
insert into REQUESTS_CATEGORY (REQUEST_ID, REQUESTER_ID, NAME) values ('3', '1', 'kółko różańcowe')
insert into REQUESTS_CATEGORY (REQUEST_ID, REQUESTER_ID, NAME) values ('4', '2', 'programowanie')
insert into REQUESTS_CATEGORY (REQUEST_ID, REQUESTER_ID, NAME) values ('5', '2', 'muzyka')
insert into REQUESTS_CATEGORY (REQUEST_ID, REQUESTER_ID, NAME) values ('6', '1', 'kuchnia')
insert into REQUESTS_CATEGORY (REQUEST_ID, REQUESTER_ID, NAME) values ('7', '1', 'książki')
insert into REQUESTS_CATEGORY (REQUEST_ID, REQUESTER_ID, NAME) values ('8', '2', 'teatr')
insert into REQUESTS_CATEGORY (REQUEST_ID, REQUESTER_ID, NAME) values ('9', '1', 'kino')
insert into REQUESTS_CATEGORY (REQUEST_ID, REQUESTER_ID, NAME) values ('10', '1', 'ogród')
