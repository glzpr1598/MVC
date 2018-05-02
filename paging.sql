-- ROWNUM �⺻ �����Ǵ� �����÷�
SELECT ROWNUM AS rnum, idx, subject, user_name, reg_date, bhit
FROM bbs
ORDER BY idx DESC;
-- ROWNUM�� �ű� �� �����ϱ� ������ ������ �����ϰ� ���ĵ��� ����.

-- ROW_NUMBER() OVER(����) ���
SELECT ROW_NUMBER() OVER(ORDER BY idx DESC) as rnum, idx, subject, user_name, reg_date, bhit
FROM bbs;
