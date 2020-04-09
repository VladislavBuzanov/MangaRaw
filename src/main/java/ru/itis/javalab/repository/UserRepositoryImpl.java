package ru.itis.javalab.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.itis.javalab.model.Role;
import ru.itis.javalab.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    EntityManagerFactory entityManagerFactory;

    RowMapper<User> mapper = (rs, i) ->
            User.builder()
                    .login(rs.getString("login"))
                    .hashPassword(rs.getString("hashpassword"))
                    .email(rs.getString("email"))
                    .role(Role.valueOf(rs.getString("role")))
                    .isConfirmed(rs.getBoolean("isConfirmed"))
                    .id(rs.getLong("id"))
                    .build();


    private static final String SQL_SAVE_USER = "INSERT INTO users(login, hashpassword) VALUES (?,?)";
    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    private static final String SQL_FIND_USER_BY_CODE = "SELECT * FROM users WHERE code = ?";

    @Override
    public Optional<User> findUserByLogin(String login) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_USER_BY_LOGIN, mapper, login));
    }


    @Override
    public void save(User user) {
       /* KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(SQL_SAVE_USER, new String[]{"id"});
                    ps.setString(1, user.getLogin());
                    ps.setString(2, user.getHashPassword());
                    return ps;
                }, keyHolder);
        user.setId(keyHolder.getKey().

                longValue());
        return user;
*/
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
    }


    @Override
    public void update(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(user);
        transaction.commit();
    }

    @Override
    public Optional<User> findUserByCode(String code) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_USER_BY_CODE, mapper, code));
    }
}
