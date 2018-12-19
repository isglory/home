package com.pmgroup.isglory;
import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//mysql 연동 테스트
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class MySQLConnectionTest {

	@Inject
	private DataSource ds;
	
	@Test
	public void testConnetion() {
		try(Connection conn = ds.getConnection()){
			System.out.println("\n >>>>>>>>>> Connection 출력 : " + conn + "\n");

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
