package com.example.demo.repository;

import com.example.demo.model.User;
import lombok.Setter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jbdv;
    private final SqlParam sqlParam;

    public UserRepository(JdbcTemplate jbdv, SqlParam sqlParam) {
        this.sqlParam = sqlParam;
        this.jbdv = jbdv;
    }


    public List<User> findAll() {
        //String sql = "SELECT * FROM userTable";

        RowMapper<User> userRowMapper = (r,i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;

        };
        return jbdv.query(sqlParam.getFindAll(),userRowMapper);
    }
    public User save(User user) {
        //String sql = "INSERT INTO userTable (firstName,lastName)VALUES (?, ?)";
        jbdv.update(sqlParam.getSave(), user.getFirstName(),user.getLastName());
        return user;

    }

    public void deleteById(int id) {
        //String sql = "DELETE FROM userTable WHERE id=?";
        jbdv.update(sqlParam.getDelete(),id);



    }
    public void update (User user) {
      //  String sql = "UPDATE userTable SET firstname = ?, lastName = ? WHERE id = ?";
        jbdv.update(sqlParam.getUpdate(), user.getFirstName(),user.getLastName(), user.getId());
    }
    public User findUserbyId(int id) {
        //String sql = "SELECT * FROM userTable WHERE id = ?";
        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };
        return jbdv.queryForObject(sqlParam.getFindById(), userRowMapper, id);
    }


}
