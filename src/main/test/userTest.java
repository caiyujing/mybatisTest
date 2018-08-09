
import com.cyj.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class userTest {
    private SqlSessionFactory sqlSessionFactory;
    private InputStream inputStream;
    private SqlSession sqlSession;
    @Test
    public void findAllUser() throws IOException {
      //  inputStream= Resources.getResourceAsStream("SqlMapConfig.xml");
        inputStream=userTest.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml");

        sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession=sqlSessionFactory.openSession();
        List<User> list =sqlSession.selectList("com.cyj.entity.User.findUserAll");
        if(list.size()>0){
            for (User u:list) {
                System.out.println(""+u.toString());
            }
        }else {
            System.out.println("查询语句失败");
        }
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void newUser(){
        try {
            inputStream=Resources.getResourceAsStream("SqlMapConfig.xml");
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession=sqlSessionFactory.openSession();
            User user=new User();
            user.setAge(10);
            user.setUserName("user1");
            user.setPassword("123456");
            int row=sqlSession.insert("com.cyj.entity.User.insertUser",user);
            if (row>0){
                System.out.println(row);
            }
            sqlSession.commit();
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
