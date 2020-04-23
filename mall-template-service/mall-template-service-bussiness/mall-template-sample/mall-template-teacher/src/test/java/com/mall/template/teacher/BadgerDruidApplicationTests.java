package com.mall.template.teacher;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mall.TeacherApplication;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TeacherApplication.class })
public class BadgerDruidApplicationTests {
// 
//    @Autowired
//    DataSource dataSource;
// 
//    @Test
//    public void contextLoads() throws SQLException {
//        Connection connection = dataSource.getConnection();
//        PreparedStatement prepareStatement = connection
//                .prepareStatement("select * from t_city where parent_id='-1'");
//        ResultSet resultSet = prepareStatement.executeQuery();
//        while (resultSet.next()) {
//            String cityName = resultSet.getString("name");
//            System.out.println(cityName);
//        }
//    }

}
