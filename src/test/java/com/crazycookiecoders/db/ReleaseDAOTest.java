package com.crazycookiecoders.db;

import org.skife.jdbi.v2.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDate;

import java.util.*;

public class ReleaseDAOTest {
    private static ReleaseDAO releaseDAO;

    @BeforeClass
    public static void setup() {
        DBI dbi = new DBI("jdbc:h2:./target/database", "sa", "sa");
        dbi.open().createScript(
                "drop table if exists release;create table release(key varchar(26) primary key, name varchar(256) not null, description varchar(4000), release_date date, status char(1) not null default 'O');")
                .execute();
        releaseDAO = dbi.onDemand(ReleaseDAO.class);
    }

    @Test
    public void testInsert() {
        releaseDAO.insert("A", "B", "description here", LocalDate.now());
        Map<String, Object> r1 = releaseDAO.findReleaseByKey("A");
        assertEquals("B", r1.get("name"));
        System.out.println(r1);
    }

    @Test
    public void testUpdate() {
        releaseDAO.insert("C", "Cname", "description here", LocalDate.now());
        Map<String, Object> r = releaseDAO.findReleaseByKey("C");
        assertEquals("Cname", r.get("name"));
        assertEquals("O", r.get("status"));
        releaseDAO.update("C", "Cname", "description here", LocalDate.now(), "R");

        r = releaseDAO.findReleaseByKey("C");
        assertEquals("Cname", r.get("name"));
        assertEquals("R", r.get("status"));
        System.out.println(r);
    }

    @Test
    public void testQueryAll() {
        releaseDAO.insert("B", "C", "description here", LocalDate.now());
        List<Map<String,Object>> r = releaseDAO.getAllReleases();
        assertNotNull(r);
    }

}
