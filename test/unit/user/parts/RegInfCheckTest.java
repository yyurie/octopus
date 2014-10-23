package unit.user.parts;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import user.parts.RegInfCheck;

public class RegInfCheckTest {
	String name;
	String age;
	String id;
	RegInfCheck regcheck = new RegInfCheck();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//�����Ȃ�
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//�����Ȃ�
	}

	@Before
	public void setUp() throws Exception {
		//�����Ȃ�
	}

	@After
	public void tearDown() throws Exception {
		//�����Ȃ�
	}

	//����nCheckName()�̃e�X�g
	@Test
	public void testCheckName1() {
		System.out.println("UT001-001�Fname:0123456789�̃e�X�g(����n)");
		String name = "0123456789";
		regcheck.checkName(name);
		assertEquals("",regcheck.getErrMsg());
	}
	@Test
	public void testCheckName2() {
		System.out.println("UT001-002�Fname:���������������������̃e�X�g(����n)");
		String name = "��������������������";
		regcheck.checkName(name);
		assertEquals("",regcheck.getErrMsg());
	}
	
	//�ُ�nCheckName()
	@Test
	public void errtestCheckName1() {
		System.out.println("UT001-003�Fname:01234567890��(�ُ�n)");
		String name = "01234567890";
		regcheck.checkName(name);
		assertEquals("���O��10���ȓ��œ��͂��Ă��������B<br />",regcheck.getErrMsg());
	}
	
	@Test
	public void errtestCheckName2() {
		System.out.println("UT001-004:name:�����������������������̃e�X�g(�ُ�n)");
		String name = "����������������������";
		regcheck.checkName(name);
		assertEquals("���O��10���ȓ��œ��͂��Ă��������B<br />",regcheck.getErrMsg());		
	}

	//����nCheckAge()�̃e�X�g
	@Test
	public void testCheckAge1() {
		System.out.println("UT001-005:age:16�̃e�X�g(����n)");
		String age = "16";
		regcheck.checkAge(age);
		assertEquals("",regcheck.getErrMsg());
	}
	
	@Test
	public void testCheckAge2() {
		System.out.println("UT001-006:age:60�̃e�X�g(����n)");
		String age = "60";
		regcheck.checkAge(age);
		assertEquals("",regcheck.getErrMsg());
	}

	//�ُ�nCheckAge()�̃e�X�g
	@Test
	public void errtestCheckAge1() {
		System.out.println("UT001-007:age:15�̃e�X�g(�ُ�n)");
		String age = "15";
		regcheck.checkAge(age);
		assertEquals("�N���(16-60)�͈̔͂œ��͂��Ă��������B<br />",regcheck.getErrMsg());
	}
	
	@Test
	public void errtestCheckAge2() {
		System.out.println("UT001-008:age:61�̃e�X�g(�ُ�n)");
		String age = "61";
		regcheck.checkAge(age);
		assertEquals("�N���(16-60)�͈̔͂œ��͂��Ă��������B<br />",regcheck.getErrMsg());
	}
	
	@Test
	public void errtestCheckAge3() {
		System.out.println("UT001-009:age:�P�U�i�啶���j�̃e�X�g(�ُ�n)");
		String age = "�P�U";
		regcheck.checkAge(age);
		assertEquals("�N��͐��l(���p)�œ��͂��Ă��������B<br />",regcheck.getErrMsg());
	}
	
	@Test
	public void errtestCheckAge4() {
		System.out.println("UT001-010:age:�����������̃e�X�g(�ُ�n)");
		String age = "����������";
		regcheck.checkAge(age);
		assertEquals("�N��͐��l(���p)�œ��͂��Ă��������B<br />�N���(16-60)�͈̔͂œ��͂��Ă��������B<br />",regcheck.getErrMsg());
	}
	
	@Test
	public void errtestCheckAge5() {
		System.out.println("UT001-011:age:16�����������̃e�X�g(�ُ�n)");
		String age = "16����������";
		regcheck.checkAge(age);
		assertEquals("�N��͐��l(���p)�œ��͂��Ă��������B<br />�N���(16-60)�͈̔͂œ��͂��Ă��������B<br />",regcheck.getErrMsg());
	}
	
	//����nCheckId()�̃e�X�g
	@Test
	public void testCheckId1() {
		System.out.println("UT001-012:id:999�̃e�X�g(����n)");
		String id = "999";
		regcheck.checkId(id);
		assertEquals("",regcheck.getErrMsg());
	}
	
	//�ُ�nCheckId()�̃e�X�g
	@Test
	public void errtestCheckId1() {
		System.out.println("UT001-013:id:1000�̃e�X�g(�ُ�n)");
		String id = "1000";
		regcheck.checkId(id);
		assertEquals("�o�^�\��ID�i999�j�𒴂��Ă��܂��B�Ǘ��҂ɖ₢���킹�Ă��������B<br />",regcheck.getErrMsg());
	}
}
