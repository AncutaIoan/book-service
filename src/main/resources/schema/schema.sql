CREATE TABLE book (
    id integer generated by default  as identity primary key ,
    self_link varchar(500),
    sale_info varchar(500),
    volume_info varchar(500)
);