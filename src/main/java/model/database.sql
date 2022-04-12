
create database m3_final;
use m3_final;

create table category(
    id int primary key auto_increment,
    name varchar(50)
);

create table product(
    id int primary key auto_increment,
    name varchar(50),
    price int,
    color varchar(20),
    description varchar (250),
    category_ID int,
    foreign key (category_ID) references category(id)
);

insert into category(name) values ('PS'),
                                  ('NS'),
                                  ('Xbox');
insert into product(name, price, color, description, category_ID)
values ('PS5', 10000, 'black', 'brand new ps5', 1),
       ('Nintendo Switch', 20000, 'red-blue', 'standard edition',2),
       ('Xbox One S', 30000, 'white', 'another choice if you hate Sony', 3);

select p.name, p.price, p.color, p.description, c.name as catetory from product p join category c on p.category_ID = c.id where p.id = 2;


delete from product where id = 2;
update product set name = ?, price = ?, color = ?, description = ?, category_ID = ? where id = ?;
insert into product(name,price,color,description,category_ID) value (?, ?, ?, ?, ?, ?);
select p.id, p.name, p.price, p.color, p.description, c.name as category from product p join category c on p.category_ID = c.id where p.name = ?;