/*Lesson 13. Creating Advanced Joins
In this lesson, you�ll learn all about additional join types�what they are and
how to use them. You�ll also learn how to use table aliases and how to use
aggregate functions with joined tables.*/

SELECT RTRIM(vend_name) + ' (' + RTRIM(vend_country) + ')'
AS vend_title
FROM Vendors
ORDER BY vend_name;
FROM Customers AS C, Orders AS O, OrderItems AS OI
WHERE C.cust_id = O.cust_id
AND OI.order_num = O.order_num
AND prod_id = 'RGAN01';