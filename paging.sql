-- ROWNUM 기본 제공되는 가상컬럼
SELECT ROWNUM AS rnum, idx, subject, user_name, reg_date, bhit
FROM bbs
ORDER BY idx DESC;
-- ROWNUM을 매긴 후 정렬하기 때문에 순서가 일정하게 정렬되지 않음.

-- ROW_NUMBER() OVER(조건) 사용
SELECT ROW_NUMBER() OVER(ORDER BY idx DESC) as rnum, idx, subject, user_name, reg_date, bhit
FROM bbs;
