import com.example.minishop.config.OracleProperties;
import com.example.minishop.mapper.MemberMapper;
import com.example.minishop.model.Member;
import com.example.minishop.factory.OracleSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ConnectionTest {
    @Test
    public void connection() {

        try (SqlSession session = OracleSqlSessionFactory.getSession()) {
            MemberMapper mapper = session.getMapper(MemberMapper.class);
            mapper.insertMember(new Member("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"));
            mapper.fetchAllMembers().forEach(System.out::println);
            int i = mapper.updateMember(new Member("1", "332", "3", "4", "5", "6", "7", "8", "9", "10", "11"));
            mapper.fetchAllMembers().forEach(System.out::println);
            System.out.println(i);

            session.selectList("com.example.minishop.mapper.MemberMapper.fetchAllMembers").forEach(System.out::println);
            session.rollback();
        }
    }


    @Test
    public void loadYaml() throws FileNotFoundException {
        Yaml yaml = new Yaml(new Constructor(OracleProperties.class));

        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("config/oracle.yaml");

         OracleProperties properties = yaml.load(inputStream);

        System.out.println(properties);
    }
}
