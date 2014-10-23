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
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		//変数初期化
		name = "";
		age = "";
		id = "";
	}

	@After
	public void tearDown() throws Exception {
	}

	//正常系CheckName()のテスト
	@Test
	public void testCheckName1() {
		System.out.println("UT001-001：name:0123456789のテスト(正常系)");
		name = "0123456789";
		regcheck.checkName(name);
		assertEquals("",regcheck.getErrMsg());
	}
	@Test
	public void testCheckName2() {
		System.out.println("UT001-002：name:あいうえおかきくけこのテスト(正常系)");
		name = "あいうえおかきくけこ";
		regcheck.checkName(name);
		assertEquals("",regcheck.getErrMsg());
	}
	
	//異常系CheckName()
	@Test
	public void errtestCheckName1() {
		System.out.println("UT001-003：name:01234567890の(異常系)");
		name = "01234567890";
		regcheck.checkName(name);
		assertEquals("名前は10桁以内で入力してください。<br />",regcheck.getErrMsg());
	}
	
	@Test
	public void errtestCheckName2() {
		System.out.println("UT001-004:name:あいうえおかきくけこさのテスト(異常系)");
		name = "あいうえおかきくけこさ";
		regcheck.checkName(name);
		assertEquals("名前は10桁以内で入力してください。<br />",regcheck.getErrMsg());		
	}

	//正常系CheckAge()のテスト
	@Test
	public void testCheckAge1() {
		System.out.println("UT001-005:age:16のテスト(正常系)");
		age = "16";
		regcheck.checkAge(age);
		assertEquals("",regcheck.getErrMsg());
	}
	
	@Test
	public void testCheckAge2() {
		System.out.println("UT001-006:age:60のテスト(正常系)");
		age = "60";
		regcheck.checkAge(age);
		assertEquals("",regcheck.getErrMsg());
	}

	//異常系CheckAge()のテスト
	@Test
	public void errtestCheckAge1() {
		System.out.println("UT001-007:age:15のテスト(異常系)");
		age = "15";
		regcheck.checkAge(age);
		assertEquals("年齢は(16-60)の範囲で入力してください。<br />",regcheck.getErrMsg());
	}
	
	@Test
	public void errtestCheckAge2() {
		System.out.println("UT001-008:age:61のテスト(異常系)");
		age = "61";
		regcheck.checkAge(age);
		assertEquals("年齢は(16-60)の範囲で入力してください。<br />",regcheck.getErrMsg());
	}
	
	@Test
	public void errtestCheckAge3() {
		System.out.println("UT001-009:age:１６（大文字）のテスト(異常系)");
		age = "１６";
		regcheck.checkAge(age);
		assertEquals("年齢は数値(半角)で入力してください。<br />",regcheck.getErrMsg());
	}
	
	@Test
	public void errtestCheckAge4() {
		System.out.println("UT001-010:age:あいうえおのテスト(異常系)");
		age = "あいうえお";
		regcheck.checkAge(age);
		assertEquals("年齢は数値(半角)で入力してください。<br />年齢は(16-60)の範囲で入力してください。<br />",regcheck.getErrMsg());
	}
	
	@Test
	public void errtestCheckAge5() {
		System.out.println("UT001-011:age:16あいうえおのテスト(異常系)");
		age = "16あいうえお";
		regcheck.checkAge(age);
		assertEquals("年齢は数値(半角)で入力してください。<br />年齢は(16-60)の範囲で入力してください。<br />",regcheck.getErrMsg());
	}
	
	//正常系CheckId()のテスト
	@Test
	public void testCheckId1() {
		System.out.println("UT001-012:id:999のテスト(正常系)");
		id = "999";
		regcheck.checkId(id);
		assertEquals("",regcheck.getErrMsg());
	}
	
	//異常系CheckId()のテスト
	public void errtestCheckId1() {
		System.out.println("UT001-013:id:1000のテスト(異常系)");
		age = "1000";
		regcheck.checkId(id);
		assertEquals("登録可能なID（999）を超えています。管理者に問い合わせてください。<br />",regcheck.getErrMsg());
	}
}
