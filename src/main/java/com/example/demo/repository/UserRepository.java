package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jbdv;

    public UserRepository(JdbcTemplate jbdv) {
        this.jbdv = jbdv;
    }


    public List<User> findAll() {
        String sql = "SELECT * FROM userTable";

        RowMapper<User> userRowMapper = (r,i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;

        };
        return jbdv.query(sql,userRowMapper);
    }
    public User save(User user) {
        String sql = "INSERT INTO userTable VALUES (NULL, ?, ?)";
        jbdv.update(sql, user.getFirstName(),user.getLastName());
        return user;

    }

    public void deleteById(int id) {
        String sql = "DELETE FROM userTable WHERE id=?";
        jbdv.update(sql,id);



    }
    public void update (User user) {
        String sql = "UPDATE userTable SET firstname = ?, lastName = ? WHERE id = ?";
        jbdv.update(sql, user.getFirstName(),user.getLastName(), user.getId());
    }
    public User findUserbyId(int id) {
        String sql = "SELECT * FROM userTable WHERE id = ?";
        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };
        return jbdv.queryForObject(sql, userRowMapper, id);
    }


}
