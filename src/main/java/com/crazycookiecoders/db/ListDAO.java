package com.crazycookiecoders.db;

import java.util.*;

import org.skife.jdbi.v2.DefaultMapper;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(DefaultMapper.class)
public interface ListDAO {

    @SqlUpdate("insert into lists (key, name,json,listdate) values (:key, :name,:json,current_date)")
    void insert(@Bind("key") String key, @Bind("name") String name, @Bind("json") String json);

    @SqlQuery("select * from lists where key = :key")
    Map<String, Object> find(@Bind("key") String key);

    @SqlUpdate("delete from lists where key= :key")
    void delete(@Bind("key") String key);
}