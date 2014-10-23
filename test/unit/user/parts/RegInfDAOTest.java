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
		//�����Ȃ�
	}

	@Before
	public void setUp() throws Exception {
		//DB�̑O������̐ݒ�
		connectDB("DELETE FROM registrants");
		connectDB("INSERT INTO registrants (registrant_id,registrant_name,registrant_age) VALUES('001','��ؑ��Y','35'),('002','Tommy','25'),('003','�R�c�Ԏq','30')");
		//�ϐ��̏�����
		list.clear();
	}

	@After
	public void tearDown() throws Exception {
		dao.close();
	}

	//Insert()�̐���n�e�X�g
	@Test
	public void testInsert() {
		
		System.out.println("UT002-001_id:004�Aname:�����H�����Aage:16��Insert�e�X�g(����n)");
		dao = new RegInfDAO();
		String id = "004";
		String name = "�����H����";
		String age = "28"; 
		dao.insert(id, name, age);
		
		//���Ғl
		String uid[] = {"001","002","003","004"};
		String uname[] = {"��ؑ��Y","Tommy","�R�c�Ԏq","�����H����"};
		String uage[] = {"35","25","30","28"};
		
		//DB�Ɗ��Ғl���r
		letsHikaku(uid,uname,uage);

	}

	//Update()�̐���n�e�X�g
	@Test
	public void testUpdate() {
		System.out.println("UT002-002_id:002��name:Michael�Aage:29�ɍX�V����Update�e�X�g(����n)");
		dao = new RegInfDAO();
		String id = "002";
		String name = "Michael";
		String age = "29"; 
		dao.update(id, name, age);
		
		//���Ғl
		String uid[] = {"001","002","003","004"};
		String uname[] = {"��ؑ��Y","Michael","�R�c�Ԏq","�����H����"};
		String uage[] = {"35","29","30","28"};
		//DB�Ɗ��Ғl���r
		letsHikaku(uid,uname,uage);
	}

	//Delete()�̐���n�e�X�g
	@Test
	public void testDelete() {
		System.out.println("UT002-003_id:001���폜����Delete�e�X�g(����n)");
		dao = new RegInfDAO();
		String id = "001";
		dao.delete(id);
		
		//���Ғl
		String uid[] = {"002","003","004"};
		String uname[] = {"Michael","�R�c�Ԏq","�����H����"};
		String uage[] = {"29","30","28"};
		//DB�Ɗ��Ғl���r
		letsHikaku(uid,uname,uage);
	}

	//GetReglist()�̐���n�e�X�g
	@Test
	public void testGetReglist() {
		System.out.println("UT002-004_GetReglist()�Ńe�[�u���f�[�^���擾����e�X�g(����n)");
		dao = new RegInfDAO();
		list = dao.getReglist();
		
		//���Ғl
		String uid[] = {"001","002","003"};
		String uname[] = {"��ؑ��Y","Tommy","�R�c�Ԏq"};
		String uage[] = {"35","25","30"};
		//DB�Ɗ��Ғl���r
		letsHikaku(uid,uname,uage);
	}

	//GetNextId()�̐���n�e�X�g
	@Test
	public void testGetNextId() {
		System.out.println("UT002-005_GetNextId()�ŋ�e�[�u�����ID���擾����e�X�g(����n)");
		connectDB("DELETE FROM registrants");
		dao = new RegInfDAO(); 
		String id = dao.getNextId();
		assertEquals("001",id);
	}

	
	/**
	 * DB�ɐڑ����A������SQL�������s���郁�\�b�h
	 * @param sql
	 */
	private void connectDB(String sql) {
		String url ="jdbc:mysql://localhost/task";
		String user = "root";
		String password = "root";
		//�ϐ��̏�����
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;		
		try {
			//�ڑ�
			con = DriverManager.getConnection(url, user, password);
			pstm = con.prepareStatement(sql);
			//SQL���s
			pstm.executeUpdate();
		} catch (SQLException e) {
			System.err.println("�G���[�ԍ��F"+e.getErrorCode());
			System.err.println("�G���[�����F"+e.getMessage());
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
				System.err.println("�N���[�Y�G���["+e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * DB�̒��g�Ǝ󂯎�������Ғl���r���郁�\�b�h
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
