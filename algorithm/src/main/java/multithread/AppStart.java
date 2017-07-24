package multithread;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppStart {

	public static void main(String[] args) {
		Connection connection = getConn("root", "123456");
		MySqlReader mySqlReader = new MySqlReader(connection);
		ExecutorService pool = Executors.newFixedThreadPool(5);
		// 创建线程
		DataReadThread t1 = new DataReadThread(mySqlReader, "message");
		DataReadThread t2 = new DataReadThread(mySqlReader, "source");

		// 将线程放入池中进行执行
		pool.execute(t1);
		pool.execute(t2);

		// 关闭线程池
		pool.shutdown();

		while (pool.isTerminated()) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static Connection getConn(String user, String password) {
		Connection con = null;

		try {
			// URL指向要访问的数据库名mydata
			String url = "jdbc:mysql://localhost:3306/message201704";
			// 驱动程序名
			String driver = "com.mysql.jdbc.Driver";
			// 加载驱动程序
			Class.forName(driver);
			// 1.getConnection()方法，连接MySQL数据库！！
			con = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("数据库数据成功获取！！");
		}
		return con;

	}

}
