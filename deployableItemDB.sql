create table release(key varchar(26) primary key, name varchar(256) not null, description varchar(4000), release_date date, status char(1) not null default 'O');
create table deployment_item(id int primary key, name varchar(256) not null, description varchar(26) not null);
create table item_version(id int primary key,item_key varchar(26), version varchar(256) not null, description varchar(4000), build_date timestamp not null,unique key(item_key,version));
create table deployment(id int primary key, version_id int not null, environment_key varchar(26) not null, release_key varchar(26));
