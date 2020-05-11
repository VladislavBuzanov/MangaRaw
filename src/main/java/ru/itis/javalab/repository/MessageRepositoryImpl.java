package ru.itis.javalab.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.itis.javalab.model.Chat;
import ru.itis.javalab.model.Message;
import ru.itis.javalab.model.User;

import javax.persistence.*;
import java.util.List;

@Component
public class MessageRepositoryImpl implements MessageRepository {
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    JdbcTemplate jdbcTemplate;

    //language=PostgreSQL
    public static final String SQL_SAVE_MESSAGE = "INSERT INTO message(date, message, chat_id, user_id) VALUES (?,?,?,?)";
    public static final String SQL_FIND_ALL_MESSAGES_BY_CHAT_ID = "SELECT * FROM message WHERE chat_id = ?";

    RowMapper<Message> mapper = (rs, i) ->
            Message.builder()
                    .user(User.builder().id(rs.getLong("user_id")).build())
                    .date(rs.getDate("date"))
                    .chat(Chat.builder().chatId(rs.getLong("chat_id")).build())
                    .message(rs.getString("message"))
                    .build();

    @Override
    public List<Message> findMessagesByChatId(Long id) {
        return jdbcTemplate.query(SQL_FIND_ALL_MESSAGES_BY_CHAT_ID, mapper, id);
    }

    @Override
    public void save(Message message) {
        jdbcTemplate.update(SQL_SAVE_MESSAGE, message.getDate(), message.getMessage(), message.getChat().getChatId(), message.getUser().getId());
    }
}
