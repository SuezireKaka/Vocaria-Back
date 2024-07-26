insert into sys_form(purpose, pos, title, type, valid_regex, uniq)
values ('register', 0, '이름', 'text', '^.{,255}$', 0),
('register', 1, '생년월일', 'date', null, 0),
('register', 2, '아이디', 'text', '^[A-Za-z0-9]{8,30}$', 1),
('register', 3, '패스워드', 'pswd', '^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-])[a-z0-9#?!@$%^&*-]{8,30}$', 0),
('register', 4, '닉네임', 'text', '^.{,255}$', 1),
('register', 5, '소개', 'text', '^.{,1000}$', 0)