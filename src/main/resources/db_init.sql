
//STANDARD_USERS
insert into STANDARD_USERS values('1', 'Jan', 'Kowalski', 'jan.kowalski@mail.com', '#$fvxy8&df$%dfg24$#%', '0');
insert into STANDARD_USERS values('2', 'Anna', 'Kowalska', 'anna.kowalska@mail.com', '34534#$#%df$%dfg24$#%', '0');
insert into STANDARD_USERS values('3', 'Stanisław', 'Nowak', 'stan.kowal@mail.com', '#$fvxy8&854dfg24$#%', '0');
insert into STANDARD_USERS values('4', 'Władysław', 'Kowal', 'wladyslaw.k@mail.com', '#$fvxy345345$%dfg24$#%', '0');
insert into STANDARD_USERS values('5', 'Jacek', 'Michaluk', 'jacek.michaluk@mail.com', '#U12376545$%dhf54$#%', '0');

//CATEGORIES
insert into CATEGORIES values('1', 'true', 'sport');
insert into CATEGORIES values('2', 'true', 'kulinaria');
insert into CATEGORIES values('3', 'false', 'meteopaci');
insert into CATEGORIES values('4', 'false', 'taniec');
insert into CATEGORIES values('5', 'false', 'teatr');

//COORDINATES
insert into COORDINATES values('1', '154541.2132', '484454545.23');

//SECTIONS
insert into SECTIONS values('1', 'first section 1234567890987654321', 'true', 'first section 1234567890987654321', '1');
insert into SECTIONS values('2', 'second section 1234567890987654321', 'false', 'second section 1234567890987654321', '1');
insert into SECTIONS values('3', 'third section 1234567890987654321', 'true', 'third section 1234567890987654321', '3');
insert into SECTIONS values('4', 'fourth section 1234567890987654321', 'true', 'fourth section 1234567890987654321', '4');

//SECTIONS_X_JOINED_USERS
insert into SECTIONS_X_JOINED_USERS values('1', '2');
insert into SECTIONS_X_JOINED_USERS values('2', '1');
insert into SECTIONS_X_JOINED_USERS values('3', '3');
insert into SECTIONS_X_JOINED_USERS values('1', '3');
insert into SECTIONS_X_JOINED_USERS values('2', '3');
insert into SECTIONS_X_JOINED_USERS values('5', '3');
insert into SECTIONS_X_JOINED_USERS values('5', '1');

//EVENT_RESPONSES
INSERT INTO EVENT_RESPONSES VALUES (1 , 'Accepted');
INSERT INTO EVENT_RESPONSES VALUES (2 , 'Rejected');
INSERT INTO EVENT_RESPONSES VALUES (3 , 'Undecided');

//EVENTS
insert into EVENTS values('1', '2024-12-30 18:47:52.69', '2023-12-30 20:47:52.69', 'first event', 'link', 'first event', 1, 'false', 'false', 'false', 'false', 'location', '50', '1', '1');
insert into EVENTS values('2', '2024-11-30 17:47:52.69', '2023-11-30 19:47:52.69', 'second event', 'link', 'second event', 2, 'false', 'false', 'false', 'false', 'location', '20', '2', '1');
insert into EVENTS values('3', '2024-10-30 16:47:52.69', '2023-10-30 18:47:52.69', 'third event', 'link', 'third event', 2, 'false', 'false', 'false', 'false', 'location', '40', '3', '1');
insert into EVENTS values('4', '2024-09-30 15:47:52.69', '2023-09-30 17:47:52.69', 'fourth event', 'link', 'fourth event', 1, 'false', 'false', 'false', 'false', 'location', '20', '1', '1');
insert into EVENTS values('5', '2021-03-28 14:47:52.69', '2022-03-28 16:47:52.69', 'fifth event', 'link', 'fifth event', 1, 'false', 'false', 'false', 'true', 'location', '35', '2', '1');
insert into EVENTS values('6', '2021-02-28 13:47:52.69', '2022-02-28 15:47:52.69', '6 event', 'link', '6 event', 1, 'false', 'false', 'false', 'false', 'location', '35', '2', '1');
insert into EVENTS values('7', '2021-01-28 12:47:52.69', '2022-01-28 14:47:52.69', '7 event', 'link', '7 event', 1, 'false', 'false', 'false', 'false', 'location', '35', '2', '1');
insert into EVENTS values('8', '2021-01-28 12:47:52.69', '2022-01-28 14:47:52.69', '8 event', 'link', '8 event', 1, 'false', 'false', 'false', 'false', 'location', '35', '2', '1');
--SELECT * FROM EVENTS_X_USERS_RESPONSES WHERE USER_ID = 1;
--SELECT EVENT_ID, DATE_TIME_FROM  FROM EVENTS;

//SECTIONS_X_EVENTS
insert into SECTIONS_X_EVENTS values('1', '1');
insert into SECTIONS_X_EVENTS values('2', '2');
insert into SECTIONS_X_EVENTS values('3', '3');

//EVENTS_X_USERS_RESPONSES
INSERT INTO EVENTS_X_USERS_RESPONSES VALUES (1 ,1 ,1);
INSERT INTO EVENTS_X_USERS_RESPONSES VALUES (1 ,3 ,2);
INSERT INTO EVENTS_X_USERS_RESPONSES VALUES (2 ,2 ,1);
INSERT INTO EVENTS_X_USERS_RESPONSES VALUES (3 ,3 ,1);
INSERT INTO EVENTS_X_USERS_RESPONSES VALUES (4 ,1 ,1);
INSERT INTO EVENTS_X_USERS_RESPONSES VALUES (5 ,1 ,1);
INSERT INTO EVENTS_X_USERS_RESPONSES VALUES (5 ,2 ,2);
INSERT INTO EVENTS_X_USERS_RESPONSES VALUES (6 ,2 ,1);
INSERT INTO EVENTS_X_USERS_RESPONSES VALUES (7 ,3 ,1);
INSERT INTO EVENTS_X_USERS_RESPONSES VALUES (8 ,2 ,1);

//ADMINS
insert into ADMINS values('1001','Marek','Głowienka','maro.mareczek@mail.com','234KJDdddJKSW23@@','1');
insert into ADMINS values('1002','Agnieszka','Górka','aga.gorka@mail.com','532##ddSSAe4QQ','1');

//HASHTAGS
insert into HASHTAGS values('2','71','false','Nudy');
insert into HASHTAGS values('1','53','true','AleDymy');
insert into HASHTAGS values('3','53','true','Celebrujmy');

//ATTACHMENTS
insert into ATTACHMENTS values('1','www.wbijajnatenlink.pl');
insert into ATTACHMENTS values('2','www.alekozackilink.pl');

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

--//NOTIFICATIONS_X_TYPES
--insert into NOTIFICATIONS_X_TYPES values('1','1');
--insert into NOTIFICATIONS_X_TYPES values('2','2');
--insert into NOTIFICATIONS_X_TYPES values('3','3');
--insert into NOTIFICATIONS_X_TYPES values('4','4');
--insert into NOTIFICATIONS_X_TYPES values('5','5');
--insert into NOTIFICATIONS_X_TYPES values('6','6');
--insert into NOTIFICATIONS_X_TYPES values('7','7');
--insert into NOTIFICATIONS_X_TYPES values('8','8');
--insert into NOTIFICATIONS_X_TYPES values('9','9');
--insert into NOTIFICATIONS_X_TYPES values('10','10');
--insert into NOTIFICATIONS_X_TYPES values('11','11');

//EVENTS_X_HASHTAGS
insert into EVENTS_X_HASHTAGS values('1','1');
insert into EVENTS_X_HASHTAGS values('4','2');

--//NOTIFICATIONS_EVENT
--insert into NOTIFICATIONS_EVENT values('1','2023-07-11 20:47:52.69','true','true','1','1','1');
--insert into NOTIFICATIONS_EVENT values('2','2023-07-11 20:46:52.69','true','false','2','3','2');
--insert into NOTIFICATIONS_EVENT values('3','2023-07-11 20:45:52.69','false','false','3','4','3');

--//NOTIFICATIONS_GENERAL
--insert into NOTIFICATIONS_GENERAL values('1','2023-07-11 20:45:52.69','true','true','1','1');
--insert into NOTIFICATIONS_GENERAL values('2','2023-07-11 20:45:52.69','true','false','2','3');
--insert into NOTIFICATIONS_GENERAL values('3','2023-07-11 20:45:52.69','false','false','3','4');

--//NOTIFICATIONS_GROUP
--insert into NOTIFICATIONS_GROUP values('1','2023-07-11 20:45:52.69','true','true','1','1','1');
--insert into NOTIFICATIONS_GROUP values('2','2023-07-11 20:45:52.69','true','false','2','3','2');
--insert into NOTIFICATIONS_GROUP values('3','2023-07-11 20:45:52.69','false','false','3','4','3');

//GUESTS
insert into GUESTS values('1','Norbert','Gierczak','distork@mail.pl');
insert into GUESTS values('2','Marcin','Majkut','xayoo777@mail.com');
insert into GUESTS values('3','Krzysztof','Sauć','golemztopa@mail.pl');
insert into GUESTS values('4','Jakub','Turewicz','hardstuckwd2@mail.com');
insert into GUESTS values('5','Jakub','Cinkrof','toniezlylec@mail.com');

//RECURRING_EVENTS
insert into RECURRING_EVENTS values('1','15','2');
insert into RECURRING_EVENTS values('2','0','3');
insert into RECURRING_EVENTS values('3','32','0');

//RECURRING_EVENTS_X_EVENTS
insert into RECURRING_EVENTS_X_EVENTS values('1','1');
insert into RECURRING_EVENTS_X_EVENTS values('2','3');
insert into RECURRING_EVENTS_X_EVENTS values('3','5');

//SECTIONS_X_RECURRING_EVENTS
insert into SECTIONS_X_RECURRING_EVENTS values('1', '1');
insert into SECTIONS_X_RECURRING_EVENTS values('2', '2');
insert into SECTIONS_X_RECURRING_EVENTS values('3', '3');

//EVENTS_X_ATTACHMENTS
insert into EVENTS_X_ATTACHMENTS values('1', '1');
insert into EVENTS_X_ATTACHMENTS values('2', '2');

//EVENTS_X_INVITED_USERS
insert into EVENTS_X_INVITED_USERS values('1', '1');
insert into EVENTS_X_INVITED_USERS values('2', '2');
insert into EVENTS_X_INVITED_USERS values('3', '3');

//EVENTS_X_JOINED_GUESTS
insert into EVENTS_X_JOINED_GUESTS values('1', '1');
insert into EVENTS_X_JOINED_GUESTS values('2', '2');
insert into EVENTS_X_JOINED_GUESTS values('3', '3');

//SURVEYS
insert into SURVEYS values('1', '1','QUESTION1','1');
insert into SURVEYS values('2', '2','QUESTION2','2');
insert into SURVEYS values('3', '3','QUESTION3','3');

//SURVEY_CHOICES
insert into SURVEY_CHOICES values('1', 'ANKIETA1');
insert into SURVEY_CHOICES values('2', 'ANKIETA2');
insert into SURVEY_CHOICES values('3', 'ANKIETA3');

//SURVEYS_X_CHOICES
insert into SURVEYS_X_CHOICES values('1', '1');
insert into SURVEYS_X_CHOICES values('2', '2');
insert into SURVEYS_X_CHOICES values('3', '3');
insert into SURVEYS_X_CHOICES values('1', '2');
insert into SURVEYS_X_CHOICES values('1', '3');

//SURVEY_RESPONSES
insert into SURVEY_RESPONSES values('1', '1');
insert into SURVEY_RESPONSES values('2', '2');
insert into SURVEY_RESPONSES values('3', '3');
insert into SURVEY_RESPONSES values('4', '2');
insert into SURVEY_RESPONSES values('5', '3');

//SURVEYS_X_RESPONSES
insert into SURVEYS_X_RESPONSES values('1', '1');
insert into SURVEYS_X_RESPONSES values('2', '2');
insert into SURVEYS_X_RESPONSES values('1', '4');
insert into SURVEYS_X_RESPONSES values('1', '5');

//SURVEY_RESPONSES_X_ANSWERS
insert into SURVEY_RESPONSES_X_ANSWERS values('1', '1');
insert into SURVEY_RESPONSES_X_ANSWERS values('2', '2');
insert into SURVEY_RESPONSES_X_ANSWERS values('3', '3');
insert into SURVEY_RESPONSES_X_ANSWERS values('4', '2');
insert into SURVEY_RESPONSES_X_ANSWERS values('5', '2');

//EVENT_COMMENTS
insert into EVENT_COMMENTS(COMMENT_ID,ADDING_DATE_TIME, COMMENT, OWNER_ID, EVENT_ID) values('1','2023-02-15 17:45:52.69','comment1','1','1');
insert into EVENT_COMMENTS values('2', '2023-01-02 20:45:52.69','comment2','2','2','1');
insert into EVENT_COMMENTS values('3', '2023-01-05 20:45:52.69','comment3','3','3','2');
--insert into EVENT_COMMENTS(COMMENT_ID,ADDING_DATE_TIME, COMMENT, OWNER_ID) values('4','2023-02-15 17:45:52.69','comment4','1');

//EVENT_COMMENTS_X_ATTACHMENTS
insert into EVENT_COMMENTS_X_ATTACHMENTS values('1', '1');
insert into EVENT_COMMENTS_X_ATTACHMENTS values('1', '2');

//REQUEST_COMMENTS
insert into  REQUEST_COMMENTS values('1', '2023-01-01 16:45:52.69','request comment1','1001');
insert into  REQUEST_COMMENTS values('2', '2023-01-02 17:45:52.69','request comment2','1002');
insert into  REQUEST_COMMENTS values('3', '2023-01-03 18:45:52.69','request comment3','1001');

//USERS_X_EVENTS_RESPONSES
insert into USERS_X_EVENTS_RESPONSES  values('1','1','1');
insert into USERS_X_EVENTS_RESPONSES  values('2','2','2');

//REQUESTS_CATEGORY
insert into REQUESTS_CATEGORY (REQUEST_ID, STATUS, REQUESTER_ID, NAME, COMMENT_ID) values ('1', 'ACCEPTED', '1', 'taniec', '1')
insert into REQUESTS_CATEGORY (REQUEST_ID, STATUS, REQUESTER_ID, NAME, COMMENT_ID) values ('2', 'ACCEPTED', '1', 'sport', '2')
insert into REQUESTS_CATEGORY (REQUEST_ID, STATUS, REQUESTER_ID, NAME, COMMENT_ID) values ('3', 'REJECTED', '1', 'kółko różańcowe', '3')
insert into REQUESTS_CATEGORY (REQUEST_ID, STATUS, REQUESTER_ID, NAME) values ('4', 'ACCEPTED', '2', 'programowanie')
insert into REQUESTS_CATEGORY (REQUEST_ID, STATUS, REQUESTER_ID, NAME) values ('5', 'NOT_PROCESSED', '2', 'muzyka')
insert into REQUESTS_CATEGORY (REQUEST_ID, STATUS, REQUESTER_ID, NAME) values ('6', 'NOT_PROCESSED', '1', 'kulinaria')
insert into REQUESTS_CATEGORY (REQUEST_ID, STATUS, REQUESTER_ID, NAME) values ('7', 'NOT_PROCESSED', '1', 'k')
insert into REQUESTS_CATEGORY (REQUEST_ID, STATUS, REQUESTER_ID, NAME, CATEGORY_ID) values ('8', 'NOT_PROCESSED', '2', 'teatr', '5')
insert into REQUESTS_CATEGORY (REQUEST_ID, STATUS, REQUESTER_ID, NAME) values ('9', 'NOT_PROCESSED', '1', 'kino')
insert into REQUESTS_CATEGORY (REQUEST_ID, STATUS, REQUESTER_ID, NAME, CATEGORY_ID) values ('10', 'NOT_PROCESSED', '1', 'taniec', '4')
insert into REQUESTS_CATEGORY (REQUEST_ID, STATUS, REQUESTER_ID, NAME) values ('11', 'NOT_PROCESSED', '1', 'fani rocka')
insert into REQUESTS_CATEGORY (REQUEST_ID, STATUS, REQUESTER_ID, NAME) values ('12', 'NOT_PROCESSED', '1', 'fani rocka')
insert into REQUESTS_CATEGORY (REQUEST_ID, STATUS, REQUESTER_ID, NAME, CATEGORY_ID) values ('13', 'NOT_PROCESSED', '1', 'meteopaci', '3')
insert into REQUESTS_CATEGORY (REQUEST_ID, STATUS, REQUESTER_ID, NAME, CATEGORY_ID) values ('14', 'NOT_PROCESSED', '1', 'meteopaci', '3')



