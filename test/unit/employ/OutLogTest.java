package unit.employ;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import employ.OutLog;

public class OutLogTest {
	//�O���[�o���ϐ��̐ݒ�
	BufferedReader br = null;
	StringBuffer sb = null;
	//FORMAT��yyyy/mm/dd hh:mm:ss:
	static final String DAY_FORMAT = "[0-9]{4}/[0-9]{2}/[0-9]{2}";
	static final String TIME_FORMAT = " [0-9]{2}:[0-9]{2}:[0-9]{2}:";
	static final String FILENAME ="C:/test/log/log.txt";
	
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
		//log�t�@�C���̒��g����ɂ���
		WriteFile();
		//�ϐ��̏�����
		br = null;
		sb = null;
	}

	@After
	public void tearDown() throws Exception {
		//�����Ȃ�
	}

	/**
	 * ����nOutLogDmpString()�̃e�X�g
	 * @throws IOException
	 */
	@Test
	public void testOutLogDmpString() throws IOException {
		System.out.println("UT003-001_sample�F�T���v����ݒ肷��e�X�g(����n)");
		//���s
		OutLog.outLogDmp("sample�F�T���v��");
		String str =readFile().toString();
		String log = str.substring(20,31);
		
		//dayformat��timeformat_check
		boolean flag1 = checkDayTimeFormat(str);
		assertTrue(flag1);
		
		//logformat_check
		boolean flag3 = log.matches("sample�F�T���v��");
		assertTrue(flag3);
	}

	/**
	 * ����n_OutLogDmpInteger()�̃e�X�g
	 * @throws IOException
	 */
	@Test
	public void testOutLogDmpInteger() throws IOException {
		System.out.println("UT003-002_12345��ݒ肷��e�X�g(����n)");
		//���s
		OutLog.outLogDmp(12345);
		String str =readFile().toString();		
		String log = str.substring(20,25);
		
		//dayformat��timeformat_check
		boolean flag1 = checkDayTimeFormat(str);
		assertTrue(flag1);
				
		//logformat_check
		boolean flag2 = log.matches("12345");
		assertTrue(flag2);		
	}

	/**
	 * ���t�Ǝ��Ԃ̃f�[�^�t�H�[�}�b�g�`�F�b�N
	 */
	private boolean checkDayTimeFormat(String str) {
		//���t�Ǝ��Ԃ̃t�H�[�}�b�g���擾
		String day = str.substring(0,10);
		String time = str.substring(10,20);
		
		if(day.matches(DAY_FORMAT)){
			time.matches(TIME_FORMAT);
			return true;
		}		
		return false;		
	}

	/**
	 * �t�@�C���̒��g��ǂݍ��݁AStringBuffer�Ɋi�[���ĕԂ�
	 * @return�@�t�@�C���̒��g
	 * @throws IOException
	 */
	private StringBuffer readFile() throws IOException{
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(FILENAME)));
			sb = new StringBuffer();
			//�t�@�C���ǂݍ���
			int ch =br.read();
			 while(ch != -1){
				 sb.append((char)ch);
				 ch =br.read();
			 }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			br.close();
		}
		//StringBuffer�ԋp
		return sb;		
	}
	
	/**
	 * log�t�@�C���̒��g��""�ŏ㏑�����ċ�t�@�C���ɂ���
	 */
	private void WriteFile() {
		File file = new File(FILENAME);
		FileWriter filewriter = null;
		try {
			//File���g�폜
			filewriter = new FileWriter(file, false);
			filewriter.write("");
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}finally{
			try {
				filewriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
}
