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
	//グローバル変数の設定
	BufferedReader br = null;
	StringBuffer sb = null;
	//FORMATはyyyy/mm/dd hh:mm:ss:
	static final String DAY_FORMAT = "[0-9]{4}/[0-9]{2}/[0-9]{2}";
	static final String TIME_FORMAT = " [0-9]{2}:[0-9]{2}:[0-9]{2}:";
	static final String FILENAME ="C:/test/log/log.txt";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//処理なし
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//処理なし
	}

	@Before
	public void setUp() throws Exception {
		//logファイルの中身を空にする
		WriteFile();
		//変数の初期化
		br = null;
		sb = null;
	}

	@After
	public void tearDown() throws Exception {
		//処理なし
	}

	/**
	 * 正常系OutLogDmpString()のテスト
	 * @throws IOException
	 */
	@Test
	public void testOutLogDmpString() throws IOException {
		System.out.println("UT003-001_sample：サンプルを設定するテスト(正常系)");
		//実行
		OutLog.outLogDmp("sample：サンプル");
		String str =readFile().toString();
		String log = str.substring(20,31);
		
		//dayformatとtimeformat_check
		boolean flag1 = checkDayTimeFormat(str);
		assertTrue(flag1);
		
		//logformat_check
		boolean flag3 = log.matches("sample：サンプル");
		assertTrue(flag3);
	}

	/**
	 * 正常系_OutLogDmpInteger()のテスト
	 * @throws IOException
	 */
	@Test
	public void testOutLogDmpInteger() throws IOException {
		System.out.println("UT003-002_12345を設定するテスト(正常系)");
		//実行
		OutLog.outLogDmp(12345);
		String str =readFile().toString();		
		String log = str.substring(20,25);
		
		//dayformatとtimeformat_check
		boolean flag1 = checkDayTimeFormat(str);
		assertTrue(flag1);
				
		//logformat_check
		boolean flag2 = log.matches("12345");
		assertTrue(flag2);		
	}

	/**
	 * 日付と時間のデータフォーマットチェック
	 */
	private boolean checkDayTimeFormat(String str) {
		//日付と時間のフォーマットを取得
		String day = str.substring(0,10);
		String time = str.substring(10,20);
		
		if(day.matches(DAY_FORMAT)){
			time.matches(TIME_FORMAT);
			return true;
		}		
		return false;		
	}

	/**
	 * ファイルの中身を読み込み、StringBufferに格納して返す
	 * @return　ファイルの中身
	 * @throws IOException
	 */
	private StringBuffer readFile() throws IOException{
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(FILENAME)));
			sb = new StringBuffer();
			//ファイル読み込み
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
		//StringBuffer返却
		return sb;		
	}
	
	/**
	 * logファイルの中身を""で上書きして空ファイルにする
	 */
	private void WriteFile() {
		File file = new File(FILENAME);
		FileWriter filewriter = null;
		try {
			//File中身削除
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
