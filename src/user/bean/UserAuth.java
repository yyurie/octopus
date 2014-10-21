package user.bean;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ���̃N���X�́A���O�C�����[�U�̔F�؂��s���܂��B
 * 
 * <br>�ŏI�X�V���F2014/6/19
 * @version 1.0
 */
public class UserAuth {

	public UserAuth() {
	}

	public boolean execute(UserInfo uInfo) throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = null;
		
		try{
			fis = new FileInputStream("c:\\temp/user.properties");
			prop.load(fis);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			fis.close();
		}
		String tmpId = prop.getProperty("user.id");
		String[] id = tmpId.split(",");
		String tmpPass = prop.getProperty("user.pass");
		String[] pass = tmpPass.split(",");

		for (int i = 0; i < id.length; i++) {

			if (id[i].equals(uInfo.getUserName())
					&& pass[i].equals(uInfo.getUserPass())) {

				// ���[�U�ƃp�X���[�h����v���Ă����OK
				return true;
			}
		}

		return false;

	}

}
