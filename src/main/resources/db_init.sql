
//USER
insert into STANDARD_USER values('1', 'Jan', 'Kowalski', 'jan.kowalski@mail.com', '#$fvxy8&df$%dfg24$#%', '1');
insert into STANDARD_USER values('2', 'Anna', 'Kowalska', 'anna.kowalska@mail.com', '34534#$#%df$%dfg24$#%', '1');
insert into STANDARD_USER values('3', 'Stanisław', 'Nowak', 'stan.kowal@mail.com', '#$fvxy8&854dfg24$#%', '1');
insert into STANDARD_USER values('4', 'Władysław', 'Kowal', 'wladyslaw.k@mail.com', '#$fvxy345345$%dfg24$#%', '1');
insert into STANDARD_USER values('5', 'Jacek', 'Michaluk', 'jacek.michaluk@mail.com', '#U12376545$%dhf54$#%', '1');


//CATEGORY
insert into CATEGORY values('1', 'true', 'sport');
insert into CATEGORY values('2', 'true', 'kulinaria');
insert into CATEGORY values('3', 'true', 'programowanie');

//COORDINATES
insert into COORDINATES values('1', '154541.2132', '484454545.23');

//SECTION
insert into SECTION values('1', 'first section', 'true', 'first section', '1');
insert into SECTION values('2', 'second section', 'true', 'second section', '2');
insert into SECTION values('3', 'third section', 'true', 'third section', '3');
insert into SECTION values('4', 'fourth section', 'false', 'fourth section', '4');

//SECTION EVENTS
insert into SECTION_EVENTS values('1', '1');
insert into SECTION_EVENTS values('2', '2');
insert into SECTION_EVENTS values('3', '3');

//SECTION JOINED USERS
insert into SECTION_JOINED_USERS values('1', '1');
insert into SECTION_JOINED_USERS values('2', '2');
insert into SECTION_JOINED_USERS values('3', '3');

//EVENT_RESPONSE
INSERT INTO  event_response VALUES (1 , 'Accepted');
INSERT INTO  event_response VALUES (2 , 'Declined');
INSERT INTO  event_response VALUES (3 , 'Undecided');

//SINGLE EVENT
insert into SINGLE_EVENT values('1', '2023-03-12 18:47:52.69', '2023-03-12 20:47:52.69', 'first event', 'link', 'first event', 1, 'false', 'false', 'false', 'false', 'location', '50', '1', '1', '1');
insert into SINGLE_EVENT values('2', '2023-02-12 17:47:52.69', '2023-02-12 19:47:52.69', 'second event', 'link', 'second event', 2, 'false', 'false', 'false', 'false', 'location', '20', '2', '1', '1');
insert into SINGLE_EVENT values('3', '2023-09-12 16:47:52.69', '2023-09-12 18:47:52.69', 'third event', 'link', 'third event', 2, 'false', 'false', 'false', 'false', 'location', '40', '3', '1', '1');
insert into SINGLE_EVENT values('4', '2023-01-12 15:47:52.69', '2023-01-12 17:47:52.69', 'fourth event', 'link', 'fourth event', 1, 'false', 'false', 'false', 'false', 'location', '20', '1', '1', '1');
insert into SINGLE_EVENT values('5', '2022-09-10 14:47:52.69', '2022-09-10 16:47:52.69', 'fifth event', 'link', 'fifth event', 1, 'false', 'false', 'false', 'true', 'location', '35', '2', '1', '1');
insert into SINGLE_EVENT values('6', '2022-08-10 13:47:52.69', '2022-08-10 15:47:52.69', '6 event', 'link', '6 event', 1, 'false', 'false', 'false', 'false', 'location', '35', '2', '1', '1');
insert into SINGLE_EVENT values('7', '2022-07-10 12:47:52.69', '2022-07-10 14:47:52.69', '7 event', 'link', '7 event', 1, 'false', 'false', 'false', 'false', 'location', '35', '2', '1', '1');

//SINGLE EVENT PARTICIPANTS
INSERT INTO  single_event_participants VALUES (1 ,1 ,1);
INSERT INTO  single_event_participants VALUES (1 ,3 ,2);
INSERT INTO  single_event_participants VALUES (2 ,3 ,1);
INSERT INTO  single_event_participants VALUES (2 ,2 ,2);
INSERT INTO  single_event_participants VALUES (3 ,3 ,1);
INSERT INTO  single_event_participants VALUES (3 ,2 ,2);
INSERT INTO  single_event_participants VALUES (4 ,3 ,1);
INSERT INTO  single_event_participants VALUES (4 ,1 ,2);
INSERT INTO  single_event_participants VALUES (5 ,1 ,1);
INSERT INTO  single_event_participants VALUES (5 ,2 ,2);

//ADMIN
insert into ADMIN values('1001','Marek','Głowienka','maro.mareczek@mail.com','234KJDdddJKSW23@@','2');
insert into ADMIN values('1002','Agnieszka','Górka','aga.gorka@mail.com','532##ddSSAe4QQ','2');

//HASHTAGS
insert into HASHTAG values('2','71','false','Nudy');
insert into HASHTAG values('1','53','true','AleDymy');

//ATTACHMENT
insert into ATTACHMENT values('1','www.wbijajnatenlink.pl');
insert into ATTACHMENT values('2','www.alekozackilink.pl');

//REQUEST_STATUS
insert into REQUEST_STATUS values('1','accept');
insert into REQUEST_STATUS values('2','decline');

//REQUEST_BOX
insert into REQUEST_BOX values('1');
insert into REQUEST_BOX values('2');

//REQUEST_BOX_REQUESTS
insert into REQUEST_BOX_REQUESTS values('1','1');
insert into REQUEST_BOX_REQUESTS values('2','2');

//NOTIFICATION_TYPE
insert into NOTIFICATION_TYPE values('1','DELETE');
insert into NOTIFICATION_TYPE values('2','EDIT');
insert into NOTIFICATION_TYPE values('3','EDIT DATE');
insert into NOTIFICATION_TYPE values('4','EDIT LOCATION');
insert into NOTIFICATION_TYPE values('5','EDIT TIME');
insert into NOTIFICATION_TYPE values('6','CHANGE OWNERSHIP');
insert into NOTIFICATION_TYPE values('7','MENTIONED');
insert into NOTIFICATION_TYPE values('8','QUOTED');
insert into NOTIFICATION_TYPE values('9','ACCEPTED REQUEST');
insert into NOTIFICATION_TYPE values('10','DECLINE REQUEST');
insert into NOTIFICATION_TYPE values('11','RESPONSE REQUIRED');

//NOTIFICATION_CATEGORY
insert into NOTIFICATION_CATEGORY values('1','sport');
insert into NOTIFICATION_CATEGORY values('2','impreza');
insert into NOTIFICATION_CATEGORY values('3','modlitwa');

//NOTIFICATION_NOTIFICATION_TYPES
insert into NOTIFICATION_NOTIFICATION_TYPES values('1','1');
insert into NOTIFICATION_NOTIFICATION_TYPES values('2','2');
insert into NOTIFICATION_NOTIFICATION_TYPES values('3','3');
insert into NOTIFICATION_NOTIFICATION_TYPES values('4','4');
insert into NOTIFICATION_NOTIFICATION_TYPES values('5','5');
insert into NOTIFICATION_NOTIFICATION_TYPES values('6','6');
insert into NOTIFICATION_NOTIFICATION_TYPES values('7','7');
insert into NOTIFICATION_NOTIFICATION_TYPES values('8','8');
insert into NOTIFICATION_NOTIFICATION_TYPES values('9','9');
insert into NOTIFICATION_NOTIFICATION_TYPES values('10','10');
insert into NOTIFICATION_NOTIFICATION_TYPES values('11','11');

//EVENT_HASHTAGS
insert into EVENT_HASHTAGS values('1','1');
insert into EVENT_HASHTAGS values('4','2');

//EVENT_NOTIFICATION
insert into EVENT_NOTIFICATION values('1','2023-07-11 20:47:52.69','true','true','1','1','1');
insert into EVENT_NOTIFICATION values('2','2023-07-11 20:46:52.69','true','false','2','3','2');
insert into EVENT_NOTIFICATION values('3','2023-07-11 20:45:52.69','false','false','3','4','3');

//GENERAL_NOTIFICATION
insert into GENERAL_NOTIFICATION values('1','2023-07-11 20:45:52.69','true','true','1','1');
insert into GENERAL_NOTIFICATION values('2','2023-07-11 20:45:52.69','true','false','2','3');
insert into GENERAL_NOTIFICATION values('3','2023-07-11 20:45:52.69','false','false','3','4');

//GROUP_NOTIFICATION
insert into GROUP_NOTIFICATION values('1','2023-07-11 20:45:52.69','true','true','1','1','1');
insert into GROUP_NOTIFICATION values('2','2023-07-11 20:45:52.69','true','false','2','3','2');
insert into GROUP_NOTIFICATION values('3','2023-07-11 20:45:52.69','false','false','3','4','3');

//GUEST
insert into GUEST values('1','Norbert','Gierczak','distork@mail.pl');
insert into GUEST values('2','Marcin','Majkut','xayoo777@mail.com');
insert into GUEST values('3','Krzysztof','Sauć','golemztopa@mail.pl');
insert into GUEST values('4','Jakub','Turewicz','hardstuckwd2@mail.com');
insert into GUEST values('5','Jakub','Cinkrof','toniezlylec@mail.com');

//RECURRING EVENT SET
insert into RECURRING_EVENT_SET values('1','15','2');
insert into RECURRING_EVENT_SET values('2','0','3');
insert into RECURRING_EVENT_SET values('3','32','0');

//RECURRING EVENT SET EVENTS
insert into RECURRING_EVENT_SET_EVENTS values('1','1');
insert into RECURRING_EVENT_SET_EVENTS values('2','3');
insert into RECURRING_EVENT_SET_EVENTS values('3','5');

//SECTION RECURRING EVENTS
insert into SECTION_RECURRING_EVENTS values('1', '1');
insert into SECTION_RECURRING_EVENTS values('2', '2');
insert into SECTION_RECURRING_EVENTS values('3', '3');

//SINGLE EVENT ATTACHMENTS
insert into SINGLE_EVENT_ATTACHMENTS values('1', '1');
insert into SINGLE_EVENT_ATTACHMENTS values('2', '2');

//SINGLE EVENT INVITED USERS
insert into SINGLE_EVENT_INVITED_USERS values('1', '1');
insert into SINGLE_EVENT_INVITED_USERS values('2', '2');
insert into SINGLE_EVENT_INVITED_USERS values('3', '3');

//SINGLE EVENT JOINED GUESTS
insert into SINGLE_EVENT_JOINED_GUESTS values('1', '1');
insert into SINGLE_EVENT_JOINED_GUESTS values('2', '2');
insert into SINGLE_EVENT_JOINED_GUESTS values('3', '3');

//SURVEY
insert into SURVEY values('1', '1','QUESTION1','1');
insert into SURVEY values('2', '2','QUESTION2','2');
insert into SURVEY values('3', '3','QUESTION3','3');

//SURVEY CHOICE
insert into SURVEY_CHOICE values('1', 'ANKIETA1');
insert into SURVEY_CHOICE values('2', 'ANKIETA2');
insert into SURVEY_CHOICE values('3', 'ANKIETA3');

//SURVEY CHOICES
insert into SURVEY_CHOICES values('1', '1');
insert into SURVEY_CHOICES values('2', '2');
insert into SURVEY_CHOICES values('3', '3');
insert into SURVEY_CHOICES values('1', '2');
insert into SURVEY_CHOICES values('1', '3');

//SURVEY RESPONSE
insert into SURVEY_RESPONSE values('1', '1');
insert into SURVEY_RESPONSE values('2', '2');
insert into SURVEY_RESPONSE values('3', '3');
insert into SURVEY_RESPONSE values('4', '2');
insert into SURVEY_RESPONSE values('5', '3');

//SURVEY RESPONSES
insert into SURVEY_RESPONSES values('1', '1');
insert into SURVEY_RESPONSES values('2', '2');
insert into SURVEY_RESPONSES values('1', '4');
insert into SURVEY_RESPONSES values('1', '5');

//SURVEY RESPONSE ANSWERS
insert into SURVEY_RESPONSE_ANSWERS values('1', '1');
insert into SURVEY_RESPONSE_ANSWERS values('2', '2');
insert into SURVEY_RESPONSE_ANSWERS values('3', '3');
insert into SURVEY_RESPONSE_ANSWERS values('4', '2');
insert into SURVEY_RESPONSE_ANSWERS values('5', '2');

//SINGLE EVENT SURVEYS
insert into SINGLE_EVENT_SURVEYS values('1', '1');
insert into SINGLE_EVENT_SURVEYS values('2', '2');
insert into SINGLE_EVENT_SURVEYS values('3', '3');