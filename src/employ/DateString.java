package employ;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ���̃N���X�́A���t�\���`���̕ϊ����s���܂��B
 * 
 * <br>�ŏI�X�V���F2014/6/19
 * @version 1.0
 */
public class DateString {
	public static String getDate14(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return sdf.format(date);
	}
	
}
