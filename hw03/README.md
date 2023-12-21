# Homework 3

## 3.2.1 Index Failures 01 
![](img/img.png)

### Postgres
```sql
create index ticket_flights_fare_cond_idx on ticket_flights(fare_conditions);
```
![](img/img_1.png)
![](img/img_2.png)
![](img/img_3.png)
  - Với query Like ở đầu sẽ không sử dụng Index vì Index này sử dụng cấu trúc dữ liệu là B-Tree
  - B-Tree sẽ hoạt động trên các câu truy vấn trong khoảng `=, >, >=, <, <=, BETWEEN`  
  - Với câu query LIKE  'prefix%' thì Postgres chỉ support index dựa trên 'C' collate hoặc index sử dụng `text_pattern_ops operator`     
```sql
create index ticket_flights_fare_cond_idx on ticket_flights(fare_conditions text_pattern_ops);
```
![](img/img_4.png)
- Khi Tạo index với `xxx_pattern_ops` thì index sẽ không hoạt động được các truy vấn `=, >, >=, <, <=`

### Mysql
![](img/img_5.png)

### Oracle
![](img/img_6.png)

`Với Mysql/Oracle thì vẫn hỗ trợ index với query LIKE 'prefix%'`

## 3.2.2. Index Failures 02
![](img/img_7.png)
![](img/img_8.png)
- Trường hợp query: `cond1(filed có index) OR cond2(field không có index)` thì kết quả sẽ là hợp của cond1 và cond2 mà cond2 phải duyệt toàn bộ bảng vì không có index nên câu truy vấn sẽ phải duyệt toàn bộ bảng và không hoạt động được index

## 3.2.3 Index Failures 03
![](img/img_9.png)

![](img/img_10.png)
- Trường hợp này database sẽ convert 1 lần flight_id sang int và tindexhực hiện tìm kiếm trên index nên sẽ hoạt động được trên index
![](img/img_11.png)
- Database báo lỗi syntax

## 3.2.4 Index Failure 04
![](img/img_13.png)
![](img/img_14.png)
- Mặc định database sẽ không hỗ trợ index thông qua function vì key index tree chỉ được tạo dựa trên value của field

## 3.2.5 Index Failure 05
![](img/img_15.png)
![](img/img_16.png)
![](img/img_17.png)
- Với các cấu truy vấn trên, database sẽ hiểu nó là các biểu thức toán học nên sẽ không sử dụng được index

