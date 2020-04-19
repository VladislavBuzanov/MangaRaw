import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.itis.javalab.config.ApplicationContextConfig;
import ru.itis.javalab.model.Chat;
import ru.itis.javalab.repository.ChatRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationContextConfig.class)
public class RepositoryTest {

    @Autowired
    ChatRepository chatRepository;

    @Test
    public void chatSaveEntityTest() {
        chatRepository.save(Chat.builder().build());
    }

    @Test
    public void chatSaveMethodShouldReturnObjectWithGeneratedKey() {
        Chat chat = chatRepository.save(Chat.builder().build());
        System.out.println(chat.getChatId());
        Assert.assertNotNull(chat.getChatId());
    }

    @Test
    public void findChatByUserIdWithNotExistingUserId() {
        System.out.println(chatRepository.findChatByUserId(1L));

    }

    @Test
    public void findChatHistoryByUserIdWithNotExistingUserId() {
        System.out.println(chatRepository.findChatByUserId(1L).get().getHistory().toString());

    }

    @Test
    public void findChatByIdWithNotExistingUserId() {
        chatRepository.findChatById(1L);

    }
}
