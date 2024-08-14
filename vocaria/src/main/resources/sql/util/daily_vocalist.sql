DELIMITER $$
CREATE OR REPLACE PROCEDURE DAILY_VOCALIST(student varchar(30), choiseNum int)
BEGIN
	declare result varchar(10000);
	
	/* imsi_word에 가장 적게 본 단어 choiseNum개를 무작위로 뽑아 임시로 저장 */
	insert into imsi_word
	select w.word
	  from t_word w
	  left outer join t_educate edu
	    on w.word = edu.word
	 order by edu.viewcnt, rand()
	 limit choiseNum;

	/* 리턴값 준비 - table function이 불가능하므로 콤마로 잇는 꼼수 */
	select group_concat(imsi.word SEPARATOR ', ') into result
	  from imsi_word imsi;

	/* t_educate의 viewcnt 정보 갱신 */
	insert into t_educate(account, word, viewcnt)
	select student account, w.word word, 1 viewcnt
	  from imsi_word w
	    on duplicate key update viewcnt = viewcnt + 1;

	/* 오늘의 미션으로 저장 */
	insert into t_daily_mission(account, word)
	select student account, w.word word
	  from imsi_word w;

	/* 임시로 저장해둔 테이블 초기화 */
    delete
      from imsi_word
     where 1 = 1;
END;
$$
DELIMITER ;