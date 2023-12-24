INSERT INTO MEMBER (name, password, authority, latitude, longitude) VALUES ('neppiness', '1234', 'USER', 0, 0);
INSERT INTO MEMBER (name, password, authority, latitude, longitude) VALUES ('kim-jeonghyun', '1234', 'USER', 0, 0);
INSERT INTO MEMBER (name, password, authority, latitude, longitude) VALUES ('teromerone', '1234', 'USER', 0, 0);
INSERT INTO MEMBER (name, password, authority, latitude, longitude) VALUES ('adolee', '1234', 'USER', 0, 0);

INSERT INTO RESTAURANT (city, name, close_or_open, type_of_foods, address_as_location_name, address_as_road_name, latitude, longitude, number_of_reviews, total_ratings, rating)
VALUES ('의정부시', '쌍홍루', '영업', '중국식', '경기도 의정부시 가능동 675-22', '경기도 의정부시 의정로173번길 16 (가능동)', 37.7484226750, 127.0335010664, 2, 7, 3.5);
INSERT INTO RESTAURANT (city, name, close_or_open, type_of_foods, address_as_location_name, address_as_road_name, latitude, longitude, number_of_reviews, total_ratings, rating)
VALUES ('용인시', '삼국지', '영업', '일식', '경기도 용인시 기흥구 보라동 586-4', '경기도 용인시 기흥구 한보라2로14번길 3-7 (보라동)', 37.2539499121, 127.1119282508, 3, 12, 4);
INSERT INTO RESTAURANT (city, name, close_or_open, type_of_foods, address_as_location_name, address_as_road_name, latitude, longitude, number_of_reviews, total_ratings, rating)
VALUES ('용인시', '정문짜장', '영업', '카페', '경기도 용인시 기흥구 보라동 138 민속프라자 111호', '경기도 용인시 기흥구 사은로 130-8 (보라동, 민속프라자 111호)', 37.2546599200, 127.1196152690, 1, 3, 3);
INSERT INTO RESTAURANT (city, name, close_or_open, type_of_foods, address_as_location_name, address_as_road_name, latitude, longitude, number_of_reviews, total_ratings, rating)
VALUES ('화성시', '신연진 마라탕 동탄역점', '영업', '중국식', '경기도 화성시 오산동 1020 동탄역 린스트라우스 1층 판매시설동 1101호', '경기도 화성시 동탄대로 469-12, 판매시설동 1층 1101호 (오산동, 동탄역 린스트라우스)', 37.1965518481, 127.0967175125, 4, 18, 4.5);

INSERT INTO REVIEW (member_seq, restaurant_seq, rating, opinion) VALUES (1, 1, 3, '가성비가 괜찮아요');
INSERT INTO REVIEW (member_seq, restaurant_seq, rating, opinion) VALUES (2, 1, 4, '꽤 만족스러웠어요');
INSERT INTO REVIEW (member_seq, restaurant_seq, rating, opinion) VALUES (1, 2, 3, '짬뽕이 괜찮았어요');
INSERT INTO REVIEW (member_seq, restaurant_seq, rating, opinion) VALUES (2, 2, 4, '짜장면이 꽤 맛있었습니당');
INSERT INTO REVIEW (member_seq, restaurant_seq, rating, opinion) VALUES (3, 2, 5, '탕수육이 엄청 맛있더라구요!');
INSERT INTO REVIEW (member_seq, restaurant_seq, rating, opinion) VALUES (1, 3, 3, '짜장 말곤 그냥 그렇더라구요');
INSERT INTO REVIEW (member_seq, restaurant_seq, rating, opinion) VALUES (1, 4, 4, '마라탕은 꽤 맛있었지만, 마라탕 말곤 보통이었습니다.');
INSERT INTO REVIEW (member_seq, restaurant_seq, rating, opinion) VALUES (2, 4, 4, '샹궈를 먹었는데 마라탕만하진 않더라구요ㅠ');
INSERT INTO REVIEW (member_seq, restaurant_seq, rating, opinion) VALUES (3, 4, 5, '마라탕 국물이 끝내줬습니다');
INSERT INTO REVIEW (member_seq, restaurant_seq, rating, opinion) VALUES (4, 4, 5, '마라탕 최고에요!');