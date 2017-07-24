package multithread;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleReader implements DataBaseReader {
	Connection con;

	public OracleReader(Connection conn) {
		this.con=conn;
		
	}


	//TODO:oracle 实现
	@Override
	public void read(String tableName) {
		System.out.println("table name:"+tableName);
		try {
			if (!con.isClosed())
				System.out.println("Succeeded connecting to the Database!");

			// 2.创建statement类对象，用来执行SQL语句！！
			Statement statement = con.createStatement();
			// 要执行的SQL语句
			String sql = "select * from "+tableName;
			// 3.ResultSet类，用来存放获取的结果集！！
			ResultSet rs = statement.executeQuery(sql);

			String id = null;
			while (rs.next()) {

				ResultSetMetaData meta = rs.getMetaData();
				int count=meta.getColumnCount();
				for(int i=1;i<=count;i++){
					System.out.print(rs.getObject(i)+"\t");
				}
				System.out.println();
				// 输出结果
			}
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
