package employ;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * ���̃N���X�́A���O�t�@�C���ւ̏������݂��s���܂��B
 * 
 * <br>�ŏI�X�V���F2014/6/19
 * @version 1.0
 */
public class OutLog {
	static File file = new File("C:/test/log/log.txt");
	static PrintWriter pw;
	
	public static void outLogDmp(String s){	
		try {
			openFile();
			pw.write(DateString.getDate14() + ":" + s);
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void outLogDmp(Integer s){
		try {
			openFile();
			pw.write(DateString.getDate14() + ":" + s.toString());
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void openFile() throws IOException{
		pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream (file, true)));
	}
}
