package user.bean;

import java.io.Serializable;

/**
 * ���̃N���X�́A���O�C�����[�U�̃G���e�B�e�B�N���X�ł��B
 * 
 * <br>�ŏI�X�V���F2014/6/19
 * @version 1.0
 */
public class UserInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String userName = null;
	private String userPass = null;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
}
