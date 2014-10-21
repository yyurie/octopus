package user.bean;

import java.io.Serializable;

/**
 * ���̃N���X�́A�ٗp�ҏ��̃G���e�B�e�B�N���X�ł��B
 * 
 * <br>�ŏI�X�V���F2014/6/19
 * @version 1.0
 */
public class RegistrantInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	String rId = null;
	String rName = null;
	String rAge = null;

	public RegistrantInfo(String rId, String rName, String rAge) {
		this.rId = rId;
		this.rName = rName;
		this.rAge = rAge;
	}
	public RegistrantInfo() {
		
	}
	public String getrId() {
		return rId;
	}
	public void setrId(String rId) {
		this.rId = rId;
	}
	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	public String getrAge() {
		return rAge;
	}
	public void setrAge(String rAge) {
		this.rAge = rAge;
	}
}
