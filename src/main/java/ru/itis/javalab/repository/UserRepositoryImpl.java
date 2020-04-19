package ru.itis.javalab.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.itis.javalab.model.enumerated.Role;
import ru.itis.javalab.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    EntityManagerFactory entityManagerFactory;

    RowMapper<User> mapper = (rs, i) ->
            User.builder()
                    .login(rs.getString("login"))
                    .hashPassword(rs.getString("password"))
                    .email(rs.getString("email"))
                    .role(Role.valueOf(rs.getString("role")))
                    .isConfirmed(rs.getBoolean("isConfirmed"))
                    .userId(rs.getLong("user_id"))
                    .build();


    private static final String SQL_SAVE_USER = "INSERT INTO users(login, password) VALUES (?,?)";
    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM itis_user WHERE login = ?";
    private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM itis_user WHERE user_id = ?";
    private static final String SQL_FIND_ALL = "SELECT * FROM itis_user";
    private static final String SQL_FIND_USER_BY_CODE = "SELECT * FROM itis_user WHERE code = ?";


    @Override
    public Optional<User> findUserByLogin(String login) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_USER_BY_LOGIN, mapper, login));
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_USER_BY_ID, mapper, id));
    }


    @Override
    @Transactional
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
        entityManager.persist(user);
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

    @Override
    public List<User> findAllUsers() {
        return jdbcTemplate.query(SQL_FIND_ALL, mapper);
    }
}
