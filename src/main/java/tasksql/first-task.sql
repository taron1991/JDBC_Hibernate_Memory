 create database cars;

 create table body(
                      id serial primary key,
                      body varchar(255)
 );

 create table engine(
                        id serial primary key,
                        engine varchar(255)
 );

 create table gearbox(
                         id serial primary key,
                         gearbox varchar(255)
 );

create table car(
                    id serial primary key,
                    name varchar(255),
                    body_id int references body(id),
                    engine_id int references engine(id),
                    gearbox_id int references gearbox(id)
);

 /*1 все машины ЦЕЛЫЕ*/
 Select car.name,body.body,engine.engine,gearbox.gearbox FROM car INNER JOIN body ON car.body_id=body.id
INNER JOIN gearbox ON car.gearbox_id=gearbox.id INNER JOIN engine ON car.engine_id=engine.id;
/* 1* все машины и их детали */
 Select car.name,body.body,engine.engine,gearbox.gearbox FROM car FULL JOIN body ON car.body_id=body.id
     FULL JOIN gearbox ON car.gearbox_id=gearbox.id FULL JOIN engine ON car.engine_id=engine.id;
/*2 отсуствие кузова*/
 select car.name,body.body from body right join car on car.body_id=body.id;;
/*3 отсутсвие двигателя*/
 select car.name,engine.engine from car left join engine on car.engine_id=engine.id;
/*4 отсуствие КПП*/
 select car.name,gearbox.gearbox from car left join gearbox on car.gearbox_id=gearbox.id;

/*Написать SQL запросы:
1. Вывести список всех машин и все привязанные к ним детали.
2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач. */
/*ser.gearbox_id=gearbox.id*/
