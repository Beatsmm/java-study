import com.example.Application;
import com.example.service.UserService;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestCreateIndex {


    @Autowired
    private UserService userService;



    @Test
    public void createIndex() throws IOException{
        userService.createUserIndex("user");
    }

    @Test
    public void deleteUserIndex(String index) throws IOException{
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(index);

    }
}
