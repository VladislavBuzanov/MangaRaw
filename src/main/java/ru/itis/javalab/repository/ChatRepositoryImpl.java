package ru.itis.javalab.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.model.Chat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.sql.PreparedStatement;
import java.util.Optional;

@Repository
public class ChatRepositoryImpl implements ChatRepository {


    @Autowired
    JdbcTemplate jdbcTemplate;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Autowired
    MessageRepository messageRepository;

    private static final String SQL_FIND_CHAT_BY_ID = "SELECT * FROM chat WHERE chat_id = ?";
    private static final String SQL_FIND_CHAT_BY_USER_ID = "SELECT * FROM chat WHERE user_id = ?";
    private static final String SQL_SAVE_CHAT = "INSERT INTO chat(user_id) VALUES (?)";

    RowMapper<Chat> mapper = (rs, i) ->
            Chat.builder()
                    .history(messageRepository.findMessagesByChatId(rs.getLong("chat_id")))
                    .user(userRepository.findUserById(rs.getLong("user_id")).get())
                    .chatId(rs.getLong("chat_id"))
                    .build();


    @Override
    public Optional<Chat> findChatById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_CHAT_BY_ID, new Object[]{id}, mapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Chat> findChatByUserId(Long user_id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_CHAT_BY_USER_ID, new Object[]{user_id}, mapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Chat> findChatByUserLogin(String login) {
        return Optional.empty();
    }

    @Override
    public Chat save(Chat chat) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(SQL_SAVE_CHAT, new String[]{"chat_id"});
                    ps.setLong(1, chat.getUser().getId());
                    return ps;
                }, keyHolder);
        chat.setChatId(keyHolder.getKey().longValue());
        return chat;
    }

    @Override
    public void update(Chat chat) {

    }
}
