insert into sys_form(
	purpose, level,
	input_prop,
	input_title,
	input_type,
	input_holder,
	input_valid_regex,
	input_unique)
values ('register', 0, 'name', '이름', 'text', '%s을 입력해주세요', '^.{,255}$', 0),
('register', 1, 'birth', '생년월일', 'date', '%s을 입력해주세요', null, 0),
('register', 2, 'loginId', '아이디', 'text', '8~30자리의 %s를 입력해주세요', '^[A-Za-z0-9]{8,30}$', 1),
('register', 3, 'rawPassword', '패스워드', 'password', '8~30자리의 소문자와 숫자, 특수문자로 이루어진 %s를 입력해주세요', '^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-])[a-z0-9#?!@$%^&*-]{8,30}$', 0),
('register', 4, 'nick', '닉네임', 'text', '%s을 입력해주세요', '^.{,255}$', 1),
('register', 5, 'introduce', '소개', 'textarea', '해당 계정을 다른 사람들에게 %s해주세요', '^.{,1000}$', 0),
('group', 0, 'groupName', '이름', 'text', '그룹 %s을 입력해주세요', '^.{,255}$', 1)