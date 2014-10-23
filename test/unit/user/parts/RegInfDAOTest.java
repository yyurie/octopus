package unit.user.parts;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import user.parts.RegInfDAO;
import user.bean.RegistrantInfo;

public class RegInfDAOTest {

	RegInfDAO dao;
	ArrayList<RegistrantInfo> list = new ArrayList<RegistrantInfo>(); 
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//JNDI
	    System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
	    System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");

	    InitialContext ic = new InitialContext();
	    ic.createSubcontext("java:");
	    ic.createSubcontext("java:comp");
	    ic.createSubcontext("java:comp/env");
	    ic.createSubcontext("java:comp/env/jdbc");

	    MysqlDataSource ds = new MysqlDataSource();
	    ds.setUser("root");
	    ds.setPassword("root");
	    ds.setURL("jdbc:mysql://localhost/Task");

	    ic.bind("java:comp/env/jdbc/Task", ds);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//処理なし
	}

	@Before
	public void setUp() throws Exception {
		//DBの前提条件の設定
		connectDB("DELETE FROM registrants");
		connectDB("INSERT INTO registrants (registrant_id,registrant_name,registrant_age) VALUES('001','鈴木太郎','35'),('002','Tommy','25'),('003','山田花子','30')");
		//変数の初期化
		list.clear();
	}

	@After
	public void tearDown() throws Exception {
		dao.close();
	}

	//Insert()の正常系テスト
	@Test
	public void testInsert() {
		
		System.out.println("UT002-001_id:004、name:佐藤路未央、age:16のInsertテスト(正常系)");
		dao = new RegInfDAO();
		String id = "004";
		String name = "佐藤路未央";
		String age = "28"; 
		dao.insert(id, name, age);
		
		//期待値
		String uid[] = {"001","002","003","004"};
		String uname[] = {"鈴木太郎","Tommy","山田花子","佐藤路未央"};
		String uage[] = {"35","25","30","28"};
		
		//DBと期待値を比較
		letsHikaku(uid,uname,uage);

	}

	//Update()の正常系テスト
	@Test
	public void testUpdate() {
		System.out.println("UT002-002_id:002をname:Michael、age:29に更新するUpdateテスト(正常系)");
		dao = new RegInfDAO();
		String id = "002";
		String name = "Michael";
		String age = "29"; 
		dao.update(id, name, age);
		
		//期待値
		String uid[] = {"001","002","003","004"};
		String uname[] = {"鈴木太郎","Michael","山田花子","佐藤路未央"};
		String uage[] = {"35","29","30","28"};
		//DBと期待値を比較
		letsHikaku(uid,uname,uage);
	}

	//Delete()の正常系テスト
	@Test
	public void testDelete() {
		System.out.println("UT002-003_id:001を削除するDeleteテスト(正常系)");
		dao = new RegInfDAO();
		String id = "001";
		dao.delete(id);
		
		//期待値
		String uid[] = {"002","003","004"};
		String uname[] = {"Michael","山田花子","佐藤路未央"};
		String uage[] = {"29","30","28"};
		//DBと期待値を比較
		letsHikaku(uid,uname,uage);
	}

	//GetReglist()の正常系テスト
	@Test
	public void testGetReglist() {
		System.out.println("UT002-004_GetReglist()でテーブルデータを取得するテスト(正常系)");
		dao = new RegInfDAO();
		list = dao.getReglist();
		
		//期待値
		String uid[] = {"001","002","003"};
		String uname[] = {"鈴木太郎","Tommy","山田花子"};
		String uage[] = {"35","25","30"};
		//DBと期待値を比較
		letsHikaku(uid,uname,uage);
	}

	//GetNextId()の正常系テスト
	@Test
	public void testGetNextId() {
		System.out.println("UT002-005_GetNextId()で空テーブルよりIDを取得するテスト(正常系)");
		connectDB("DELETE FROM registrants");
		dao = new RegInfDAO(); 
		String id = dao.getNextId();
		assertEquals("001",id);
	}

	
	/**
	 * DBに接続し、引数のSQL文を実行するメソッド
	 * @param sql
	 */
	private void connectDB(String sql) {
		String url ="jdbc:mysql://localhost/task";
		String user = "root";
		String password = "root";
		//変数の初期化
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;		
		try {
			//接続
			con = DriverManager.getConnection(url, user, password);
			pstm = con.prepareStatement(sql);
			//SQL実行
			pstm.executeUpdate();
		} catch (SQLException e) {
			System.err.println("エラー番号："+e.getErrorCode());
			System.err.println("エラー説明："+e.getMessage());
		}finally{
			try {
				if(rs != null){
					rs.close();
				}if(pstm != null){
					pstm.close();
				}if(con != null){
					con.close();
				}				
			} catch (SQLException e) {
				System.err.println("クローズエラー"+e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * DBの中身と受け取った期待値を比較するメソッド
	 * @param uid
	 * @param uname
	 * @param uage
	 */
	private void letsHikaku(String[] uid, String[] uname, String[] uage) {
		for(int i = 0;i<list.size();i++){
			RegistrantInfo reg = list.get(i);
			assertEquals(uid[i],reg.getrId());
			assertEquals(uname[i],reg.getrName());
			assertEquals(uage[i],reg.getrAge());
		}		
	}
}
