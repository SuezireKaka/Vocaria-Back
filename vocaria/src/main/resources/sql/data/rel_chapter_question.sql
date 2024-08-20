insert into rel_chapter_question(chapter, pos, question)
select sub1.id chapter, (row_number() over (order by sub2.id) - 1) % 5 pos, sub2.id question
  from (
    select chapter.*, row_number() over (order by id) - 1 row_num
      from t_chapter chapter
  ) sub1
  left outer join (
    select floor((row_number() over (order by id) - 1) / 5) row_quot, quest.*
      from t_question quest
  ) sub2
    on sub1.row_num = sub2.row_quot