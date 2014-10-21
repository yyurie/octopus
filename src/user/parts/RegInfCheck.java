package user.parts;

/**
 * ���̃N���X�́A�ٗp�ҏ��̓��͒l���`�F�b�N����@�\��񋟂��܂��B
 * 
 * <br>�ŏI�X�V���F2014/6/19
 * @version 1.0
 */
public class RegInfCheck {
	private StringBuilder errMsg = new StringBuilder();
	final static String CHECK_TYPE_FIX = "1";
	final static String CHECK_TYPE_WITHIN = "0";
	
	public String getErrMsg() {
		return errMsg.toString();
	}
	
	public void checkName(String regName) {

		// �����`�F�b�N(�ȓ�)
		if (!lengthCheck(regName, CHECK_TYPE_WITHIN, 10)) {
			errMsg.append("���O��10���ȓ��œ��͂��Ă��������B<br />");
		}
	}
	
	public void checkAge(String regAge) {
		
		// ���l�`�F�b�N
		if (!numberCheck(regAge)) {
			errMsg.append("�N��͐��l(���p)�œ��͂��Ă��������B<br />");
		}
		
		// �͈̓`�F�b�N(16-60)
		if (!limitCheck(regAge, 16, 60)) {
			errMsg.append("�N���(16-60)�͈̔͂œ��͂��Ă��������B<br />");
		}
	}
	
	public void checkId(String regId){
		if(!maxCheck(regId,1000)){
			errMsg.append("�o�^�\��ID�i999�j�𒴂��Ă��܂��B�Ǘ��҂ɖ₢���킹�Ă��������B<br />");
		}	
	}


	private boolean numberCheck(final String str) {

		int intChk = 0; 
		for (int i = 0; i < str.length(); i++) { 
		
			char c = str.charAt(i);
			char c1 = '0'; 
			char c2 = '9'; 
			if (c < c1 || c > c2) {
				intChk = intChk + 1;
			} 
		}

		if (intChk == 0) {
			return true;
		} else {
			return false;
		} 
	}

	private boolean lengthCheck(final String str, final String type, final int dig) {
		
		if (str == null || str.length()==0) {
			return false;
		}
		
		if (CHECK_TYPE_FIX.equals(type)) {
			
			if (str.length() == dig ) {
				return true;
			} else { 
				return false;
			}
		} else {
			
			if (str.length() <= dig) {
				return true;
			} else { 
				return false;
			}
		}
	}
	
	private boolean minCheck(final String val, final int min){
		try {
			int num = Integer.parseInt(val);
		
			if (min < num) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	private boolean maxCheck(final String val, final int max){
		try {
			int num = Integer.parseInt(val);
		
			if (num < max) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	private boolean limitCheck(final String val, final int min, final int max) {
		
		try {
			int num = Integer.parseInt(val);
		
			if ((min <= num) && (num <= max)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
}
