package co.yedam.hello.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

// 싱글톤 클래스 만드는 방법
public class DataSource {
//	private static DataSource dataSource = new DataSource();
//	private DataSource() {}
//	
//	public static DataSource getInstance() {
//		// 필요한 사항 정의
//		return dataSource;
//	}
	
	private static SqlSessionFactory sqlSessionFactory;
	private DataSource() {}
	
	public static SqlSessionFactory getInstance() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSessionFactory;
	}
}
