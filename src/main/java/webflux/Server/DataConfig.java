package webflux.webflux.Server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;

@Configuration
public class DataConfig {

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;


    public DataSource getDataSource() throws SQLException {
        Connection connect = DriverManager.getConnection("jdbc:mysql//localhost/user","lukas","passs");
        Statement statement = connect.createStatement();
        ResultSet result = statement.executeQuery("CREATE DATABASE user ");
        if(result.next())
        {
            System.out.println("CREATE DATABASE User");
        }
        else
        {
            throw new SQLException();
        }

        statement.executeUpdate("CREATE TABLE user(id int,name text,surname text,age int,height int,wage int)");
        int exec = statement.executeUpdate("INSERT INTO user VALUES (1,'aaa','bb',23,34,67)");
        if(exec > 0)
        {
            System.out.println("Create database");
        }
        return dataSource;
    }

    public JdbcTemplate jdbcTemplate()
    {
        return new JdbcTemplate(dataSource);
    }
}
