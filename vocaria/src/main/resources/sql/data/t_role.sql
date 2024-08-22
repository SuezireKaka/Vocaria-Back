insert into t_role (id, provider, name, info, dflt)
values (NEXT_PK('s_role'), '0000', 'manager', '보카리아 전체를 관리합니다', 0),
(NEXT_PK('s_role'), '0000', 'student', '보카리아의 기본 기능을 이용할 수 있습니다', 1)

insert into t_role (id, provider, name, info, dflt)
values (NEXT_PK('s_role'), '0000', 'teacher', '학생들을 가르치고 관리할 수 있습니다', 0);