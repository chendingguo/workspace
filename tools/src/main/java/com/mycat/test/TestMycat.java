package com.mycat.test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Random;

import org.junit.Test;

/**
 * 测试表数据
 * create database 20170101
 * use database 20170101
 * create table source (  
        id int(11) not null auto_increment primary key comment 'pk',  
        name varchar(10) default '' comment 'source name'  
);  
create table message (  
        id int(11) not null auto_increment primary key comment 'pk',  
        content varchar(255) default '' comment 'message content',  
        create_time date default null,  
        source_id int(11) not null,  
        foreign key(source_id) references source(id)  
);  
insert into `source`(`id`,`name`) values(1,'weibo');  
insert into `source`(`id`,`name`) values(2,'weixin');  
insert into `source`(`id`,`name`) values(3,'qq');  
insert into `source`(`id`,`name`) values(4,'email');  
insert into `source`(`id`,`name`) values(5,'sms');
 * @author 　蓝桥
 *
 */
public class TestMycat {
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://127.0.0.1:8066/messagedb?useServerPrepStmts=false&rewriteBatchedStatements=true";
	private static final String username = "admin";
	private static final String password = "admin123";

	@Test
	public void test() throws SQLException {
		Calendar calendar = Calendar.getInstance();
		Random random = new Random();
		calendar.set(2017, 0, 1, 0, 0, 0);

		Connection connection = null;
		PreparedStatement ps = null;
		try {
			Class.forName(driver);
			connection = (Connection) DriverManager.getConnection(url, username, password);
			connection.setAutoCommit(false);
			String sql = "insert into message(`content`, `create_time`, `source_id`) values(?,?,?)";
			ps = connection.prepareStatement(sql);
			long start = System.currentTimeMillis();
			for (int i = 0; i < 1000; i++) {
				
				long randomtime = calendar.getTimeInMillis()
						+ (random.nextInt(365) ) * 86400 * 1000l;
				Date date = new Date(randomtime);
				int source_id = random.nextInt(5) + 1;
				ps.setString(1, System.currentTimeMillis() + "");
				ps.setDate(2, date);
				ps.setInt(3, source_id);
				ps.addBatch();
				if (i != 0 && i % 100 == 0) {
					System.out.println("execute batch : " + i);
					ps.executeBatch();
					
					
				}
			}
			connection.commit();
			System.out.println("used　Time:"+(System.currentTimeMillis() - start));
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (ps != null)
				ps.close();
			if (connection != null)
				connection.close();
		}
	}

}