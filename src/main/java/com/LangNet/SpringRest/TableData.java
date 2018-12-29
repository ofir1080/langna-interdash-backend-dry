package com.LangNet.SpringRest;

import com.LangNet.SpringRest.model.*;
import com.LangNet.SpringRest.model.month.RecPerMonth;
import com.LangNet.SpringRest.model.month.UserPerMonth;
import com.LangNet.SpringRest.model.month.UserReferencePerMonth;
import com.LangNet.SpringRest.model.top.TopCountries;
import com.LangNet.SpringRest.model.top.TopUsers;
import com.LangNet.SpringRest.model.total.TotalLang;
import com.LangNet.SpringRest.model.total.TotalTime;
import com.LangNet.SpringRest.model.total.TotalUsers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The object TableData contains all the tables.
 * Every table is represented as an ArrayList<OBJECT> where every oblect is an entity of a table.
 *
 * note that this class in singleton, so only one instance allowed.
 *
 */
@Component
public class TableData {

    private static final Logger log = LoggerFactory.getLogger(TableData.class);

    @Autowired
    JdbcTemplate jdbcTemplate;


    /**
     * the following functions return a certain table implemented as an ArrayList
     */

    public List<UserPerMonth> getUserPerMonth() {

        String query = "SELECT mon, sum (growth) OVER (ORDER BY mon), growth FROM" +
                "( SELECT to_char(created_at, 'MM/YYYY') as mon, count(*) as growth FROM \"user\" GROUP BY mon ORDER BY mon ) as t;";
        List<UserPerMonth> table = new ArrayList<>();

        try {
            table = jdbcTemplate.query(query,
                    (rs, rowNum) -> {
                        int total = rs.getInt(2);
                        int growth = rs.getInt(3);
                        return new UserPerMonth(rs.getString(1), total, growth(total - growth, growth));
                    });

        } catch (DataAccessException e) {
            log.error(e.getMessage(), e);
        }
        return table;
    }

    public List<RecPerMonth> getRecPerMonth() {

        String query = "SELECT mon, sum(count) OVER (ORDER BY mon), count FROM\n" +
                "( SELECT to_char(created_at, 'MM-YYYY') as mon, count(*) FROM \"voice\" GROUP BY mon ORDER BY mon ) as t;";
        List<RecPerMonth> table = new ArrayList<>();

        try {
            table = jdbcTemplate.query(query,
                    (rs, rowNum) -> {
                        int total = rs.getInt(2);
                        int growth = rs.getInt(3);
                        return new RecPerMonth(rs.getString(1), total, growth(total - growth, growth));
                    });

        } catch (DataAccessException e) {
            log.error(e.getMessage(), e);
        }
        return table;
    }

    public List<TotalLang> getTotalLang() {

        String query = "SELECT ( SELECT sum(amount) FROM transaction WHERE status = 'approved' )," +
                "sum(amount) FROM transaction WHERE updated_at + '7 days' > now() + '-9 hours' AND status = 'approved';";
        List<TotalLang> table = new ArrayList<>();

        try {
            table = jdbcTemplate.query(query,
                    (rs, rowNum) -> {
                        double total = rs.getDouble(1);
                        double period = rs.getDouble(2);
                        return new TotalLang(trunc(total, 2) , growth(total, period));
                    });

        } catch (DataAccessException e) {
            log.error(e.getMessage(), e);
        }
        return table;
    }

    public double getTotalLangUSD() {

        String query = "SELECT sum(amount) * 0.01 FROM transaction WHERE status = 'approved'";
        double totalUSD = -1;

        try {
            totalUSD = jdbcTemplate.query(query,
                    (rs, rowNum) -> {
                        double total = rs.getDouble(1);
                        return trunc(trunc(total, 2), 2);
                    }).get(0);

        } catch (DataAccessException e) {
            log.error(e.getMessage(), e);
        }
        return totalUSD;
    }

    public List<TotalUsers> getTotalUsers() {

        String query = "SELECT ( SELECT count(*) FROM \"user\" )," +
                "( SELECT count(*) FROM \"user\" WHERE created_at + '7 days' > now() + '-9 hours' );";
        List<TotalUsers> table = new ArrayList<>();

        try {
            table = jdbcTemplate.query(query,
                    (rs, rowNum) -> {
                        double total = rs.getDouble(1);
                        double period = rs.getDouble(2);
                        return new TotalUsers(total, period);
                    });

        } catch (DataAccessException e) {
            log.error(e.getMessage(), e);
        }
        return table;
    }

    public int getTotalCountries() {

        String query = "SELECT count(*) FROM ( SELECT DISTINCT citizenship FROM \"user\" ) as t;";
        int totalCountries = -1;

        try {
            totalCountries = jdbcTemplate.query(query,
                    (rs, rowNum) -> rs.getInt(1)).get(0);

        } catch (DataAccessException e) {
            log.error(e.getMessage(), e);
        }
        return totalCountries;
    }

    public List<TotalTime> getTotalTime() {

        String query = "SELECT ( SELECT sum(length) FROM voice WHERE status_msg LIKE 'success' ),\n" +
                "( SELECT sum(length) FROM voice WHERE created_at + '7 days' > now() + '-9 hours' AND status_msg LIKE 'success');";
        List<TotalTime> table = new ArrayList<>();

        try {
            table = jdbcTemplate.query(query,
                    (rs, rowNum) -> {
                        double total = rs.getDouble(1);
                        double period = rs.getDouble(2);
                        return new TotalTime(trunc(total / 3600, 1), trunc(period / 3600, 1));
                    });

        } catch (DataAccessException e) {
            log.error(e.getMessage(), e);
        }
        return table;
    }

    public List<TopCountries> getTopCountries() {

        String query = "SELECT ROW_NUMBER() OVER(ORDER BY sum desc), main1.citizenship, sum, count FROM\n" +
                "( SELECT citizenship, (sum(sum) / 60) as \"sum\" FROM\n" +
                "( SELECT citizenship, id FROM \"user\" ) as t1 LEFT JOIN \n" +
                "( SELECT user_id, sum(length) FROM voice WHERE status_msg LIKE 'success' GROUP BY user_id ) as t2 ON t1.id = t2.user_id GROUP BY citizenship) as main1\n" +
                "INNER JOIN ( SELECT citizenship, count(*) FROM \"user\" GROUP BY citizenship ) as main2 ON main1.citizenship = main2.citizenship WHERE sum IS NOT null;\n";
        List<TopCountries> table = new ArrayList<>();

        try {
            table = jdbcTemplate.query(query,
                    (rs, rowNum) -> {
                        double recTime = trunc(rs.getDouble(3), 1);
                        int userCount = rs.getInt(4);
                        return new TopCountries(rs.getInt(1), rs.getString(2), trunc(rs.getDouble(3),1),
                                rs.getInt(4), trunc(recTime / userCount, 1));
                    });

        } catch (DataAccessException e) {
            log.error(e.getMessage(), e);
        }
        return table;
    }

    public List<FinishedCampaign> getFinishedCampaign() {

        String query = "SELECT name, \"limit\", total, finished, cam_id FROM\n" +
                "( SELECT cast(ltrim(\"type\", 'sentence_campaign_') as integer) cam_id, count(*) \"finished\" FROM transaction WHERE \"type\" LIKE '%campaign%' AND status = 'approved'\n" +
                "GROUP BY cam_id ) as t1\n" +
                " JOIN \"campaign\" as t2 ON t1.cam_id = t2.id\n" +
                " JOIN \n" +
                "( SELECT campaign_id, count(*) \"total\" FROM \n" +
                "( SELECT user_id, campaign_id, count(*) FROM voice GROUP BY user_id, campaign_id ORDER BY campaign_id ) as t GROUP BY campaign_id ) as t3 ON t2.id = t3.campaign_id ORDER BY created_at;";
        List<FinishedCampaign> table = new ArrayList<>();

        try {
            table = jdbcTemplate.query(query,
                    (rs, rowNum) -> {
                        int total = rs.getInt(3);
                        int finished = rs.getInt(4);
                        return new FinishedCampaign(rs.getString(1), rs.getInt(2), total, finished,
                                trunc(growth(total, finished), 2), rs.getLong(5));
                    });

        } catch (DataAccessException e) {
            log.error(e.getMessage(), e);
        }
        return table;
    }

    public List<TopUsers> getTopUsers() {

        String query = "SELECT ROW_NUMBER () OVER (ORDER BY t1.sum desc), t1.user_id, t2.username, t2.email, justify_hours(make_interval(secs := trunc(cast(t1.sum as numeric)))), t1.count, t3.sum\n" +
                "FROM\n" +
                "( SELECT sum(length) sum, user_id, count(*) FROM voice GROUP BY user_id ) AS t1\n" +
                "INNER JOIN \"user\" t2 ON t1.user_id = t2.id\n" +
                "INNER JOIN ( SELECT user_id, sum(amount) FROM \"transaction\" WHERE status='approved' GROUP BY user_id ) as t3 ON t2.id = t3.user_id\n";
        List<TopUsers> table = new ArrayList<>();

        try {
            table = jdbcTemplate.query(query,
                    (rs, rowNum) -> new TopUsers(rs.getLong(1), rs.getLong(2), rs.getString(3),  rs.getString(4),
                            rs.getString(5), rs.getInt(6), trunc(rs.getDouble(7), 2)));

        } catch (DataAccessException e) {
            log.error(e.getMessage(), e);
        }
        return table;
    }

    public List<UserReferencePerMonth> getUserReferencePerMonth() {
        String query = "SELECT \"TELEGRAM ONLY\".mon, sum(\"TELEGRAM ONLY\".count) OVER(ORDER BY \"TELEGRAM ONLY\".mon),\n" +
                "sum(\"DASHBOARD ONLY\".count) OVER(ORDER BY \"TELEGRAM ONLY\".mon),\n" +
                "sum(\"BOTH\".count) OVER(ORDER BY \"TELEGRAM ONLY\".mon) FROM\n" +
                "( SELECT to_char(created_at, 'MM-YYYY') as mon, count(*) FROM \"user\" WHERE telegram_id IS NOT NULL AND keycloak_id IS NULL GROUP BY mon ORDER BY mon ) as \"TELEGRAM ONLY\"\n" +
                "  LEFT OUTER JOIN\n" +
                "( SELECT to_char(created_at, 'MM-YYYY') as mon, count(*) FROM \"user\" WHERE telegram_id IS NOT NULL AND keycloak_id IS NOT NULL GROUP BY mon ORDER BY mon ) as \"BOTH\"\n" +
                "ON \"TELEGRAM ONLY\".mon = \"BOTH\".mon\n" +
                " LEFT OUTER JOIN\n" +
                "( SELECT to_char(created_at, 'MM-YYYY') as mon, count(*) FROM \"user\" WHERE telegram_id IS NULL AND keycloak_id IS NOT NULL GROUP BY mon ORDER BY mon ) as \"DASHBOARD ONLY\"\n" +
                "ON \"TELEGRAM ONLY\".mon = \"DASHBOARD ONLY\".mon;";
        List<UserReferencePerMonth> table = new ArrayList<>();

        try {
            table = jdbcTemplate.query(query,
                    (rs, rowNum) -> new UserReferencePerMonth(rs.getString(1), rs.getInt(2), rs.getInt(3),
                            rs.getInt(4)));

        } catch (DataAccessException e) {
            log.error(e.getMessage(), e);
        }
        return table;
    }

    public List<UserList> getUserList(String boolOne, String andOr, String boolTwo) {

        String query = "SELECT username, email, citizenship FROM \"user\" WHERE telegram_id IS " + boolOne + " NULL " + andOr + " keycloak_id IS " + boolTwo + " NULL;";
        List<UserList> table = new ArrayList<>();

        try {
            table = jdbcTemplate.query(query,
                    (rs, rowNum) -> new UserList(rs.getString(1), rs.getString(2), rs.getString(3)));

        } catch (DataAccessException e) {
            log.error(e.getMessage(), e);
        }
        return table;
    }

    public List<Referral> getReferral() {

        String query = "SELECT t2.username, t2.email, t1.\"total\", t1.\"completed\" FROM\n" +
                "( SELECT t1.inviter_id, completed, \"total\" FROM\n" +
                "( SELECT inviter_id, count(*) \"total\" FROM user_referral GROUP BY inviter_id ) as t1\n" +
                "JOIN\n" +
                "( SELECT inviter_id, count(*) \"completed\" FROM user_referral WHERE done IS true GROUP BY inviter_id ) as t2 ON t1.inviter_id = t2.inviter_id ) as t1\n" +
                "LEFT JOIN \"user\" as t2 ON inviter_id = id ORDER BY completed desc;";
        List<Referral> table = new ArrayList<>();

        try {
            table = jdbcTemplate.query(query,
                    (rs, rowNum) -> {
                        int total = rs.getInt(3);
                        int completed = rs.getInt(4);
                        return new Referral(rs.getString(1), rs.getString(2), total, completed, trunc(growth(total, completed), 2));
                    });

        } catch (DataAccessException e) {
            log.error(e.getMessage(), e);
        }
        return table;
    }

    public List<NewUsersList> getNewUsersList() {

        String query = "SELECT id , username, email, citizenship, to_char(created_at, 'MM-DD-YYYY') FROM \"user\" WHERE created_at + '60 days' > now() ORDER BY created_at desc;\n";
        List<NewUsersList> table = new ArrayList<>();

        try {
            table = jdbcTemplate.query(query,
                    (rs, rowNum) -> new NewUsersList(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));

        } catch (DataAccessException e) {
            log.error(e.getMessage(), e);
        }
        return table;
    }

    public List<UserList> getLastRecorders() {

        String query = "SELECT t2.username, t2.email, t2.citizenship FROM\n" +
                "( SELECT DISTINCT user_id FROM voice WHERE created_at + '30 days' > now() ) as t1\n" +
                "INNER JOIN \"user\" as t2\n" +
                "ON t1.user_id = t2.id;";
        List<UserList> table = new ArrayList<>();

        try {
            table = jdbcTemplate.query(query,
                    (rs, rowNum) -> new UserList(rs.getString(1), rs.getString(2), rs.getString(3)));

        } catch (DataAccessException e) {
            log.error(e.getMessage(), e);
        }
        return table;
    }


    /**
     * simple calc methods
     */

    private double trunc(double num, int decimalDigits) {

        double temp = Math.pow(10, decimalDigits);
        return ( (int) (num * Math.pow(10, decimalDigits)) ) / temp;
    }

    private double growth(double total, double period) {
        return trunc(period / total * 100, 2);
    }
}