package com.crazycookiecoders.db;

import java.time.LocalDate;

import java.util.*;

import org.skife.jdbi.v2.DefaultMapper;


import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;



@RegisterMapper(DefaultMapper.class)
public interface ReleaseDAO {


    @SqlUpdate("insert into release (key, name,description,release_date) values (:key, :name,:description,:date)")
    void insert(@Bind("key") String key, @Bind("name") String name, @Bind("description") String description,
            @Bind("date") LocalDate date);

    @SqlUpdate("update release set name=:name,description=:description,release_date=:date,status=:status where key=:key")
    void update(@Bind("key") String key, @Bind("name") String name, @Bind("description") String description,
            @Bind("date") LocalDate date,@Bind("status") String status);


    @SqlQuery("select * from release")
    List<Map<String,Object>> getAllReleases();

    @SqlQuery("select * from release where key = :key")
    Map<String, Object> findReleaseByKey(@Bind("key") String key);
}