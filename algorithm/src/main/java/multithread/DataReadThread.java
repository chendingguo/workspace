package multithread;

public class DataReadThread implements Runnable {
	DataBaseReader  reader;
	String tableName;
	public DataReadThread(DataBaseReader reader,String tableName) {
		this.reader=reader;
		this.tableName=tableName;
	}

	@Override
	public void run() {
		reader.read(tableName);

	}

}
