package com.azxx.demon.dao.jdbc;

import com.azxx.demon.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class UserDao {

    private static Logger logger = Logger.getLogger(UserDao.class.getName());

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(User user){
        String sql = "insert into user_info(name ,age) values (?,?)";
        int result = this.jdbcTemplate.update(sql,user.getName(),user.getAge());
        logger.info("---------------------->"+result+"");
    }

    public void update(User user) throws Exception {
        if(true)
            throw new Exception("abc");
        String sql = "update user_info set age = ? where name = ?";
        this.jdbcTemplate.update(sql,user.getAge(), user.getName());
    }

    /**
     * JDBC 批处理
     * @param actors
     * @return
     */
    public int[] batchUpdate(final List<User> actors) {
        return this.jdbcTemplate.batchUpdate(
                "update t_actor set first_name = ?, last_name = ? where id = ?",
                new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, actors.get(i).getName());
                        ps.setString(2, actors.get(i).getName());
//                        ps.setLong(3, actors.get(i).getId());
                    }
                    public int getBatchSize() {
                        return actors.size();
                    }
                });
    }

    public int[][] batchUpdate(final Collection<User> actors) {
        int[][] updateCounts = jdbcTemplate.batchUpdate(
                "update t_actor set first_name = ?, last_name = ? where id = ?",
                actors,
                100,
                new ParameterizedPreparedStatementSetter<User>() {
                    public void setValues(PreparedStatement ps, User argument) throws SQLException {
                        ps.setString(1, argument.getName());
                        ps.setString(2, argument.getName());
//                        ps.setLong(3, argument.getId().longValue());
                    }
                });
        return updateCounts;
    }
}
