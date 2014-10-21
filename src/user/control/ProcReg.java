package user.control;

import java.io.IOException;
import user.bean.RegistrantInfo;
import user.parts.RegInfCheck;
import user.parts.RegInfDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * �ٗp�ғo�^���s���܂��B
 * 
 * <br>�ŏI�X�V���F2014/6/19
 * @version 1.0
 */
public class ProcReg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String errMsg;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcReg() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegInfDAO rDAO = null;
		try {
			request.setCharacterEncoding("UTF-8"); 
			response.setContentType("text/html; charset=UTF-8"); 
			RequestDispatcher rd = null;
						
			errMsg = "";
			rDAO = new RegInfDAO();

			// �o�^���ꂽ�p�����[�^���擾
			String regName = request.getParameter("regName");
			String regAge = request.getParameter("regAge");
			// �V�KID��ݒ�
			String regId = rDAO.getNextId();
		
			inputCheck(regId,regName, regAge);// ���̓f�[�^�Ɍ�肪�������ꍇ�͂��̎|��\��������

			if(!errMsg.equals("")){
				request.setAttribute("errMsg", errMsg);
				rd = request.getRequestDispatcher("/RegRegist.jsp");
				rd.forward(request, response);
				return ;
			}
			// ���͏���ݒ�
			RegistrantInfo regInfo = new RegistrantInfo();
			regInfo.setrId(regId);
			regInfo.setrName(regName);
			regInfo.setrAge(regAge);
			rDAO.insert(regId, regName, regAge);

			// �f�[�^����������
//			WriteRegistInfo.regRegInfo(regInfo);

			// ���ʉ�ʂ֑J�ڂ��邽�߂̏���ݒ�
			request.setAttribute("regInfo", regInfo);
			rd = request.getRequestDispatcher("/RegistResult.jsp");

			rd.forward(request, response);
		
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errMsg", "�G���[���������܂����B�\���󂠂�܂��񂪂�����x�ŏ����炨�肢���܂��B");
			HttpSession session = request.getSession(true);
			session.invalidate();
			RequestDispatcher rd = request.getRequestDispatcher(
					"/Login.jsp");
			rd.forward(request, response);
		} finally {
			if(rDAO != null){
				rDAO.close();
			}
		}
	}

	private void inputCheck(String regId, String regName, String regAge) {
		RegInfCheck richk = new RegInfCheck();
		richk.checkId(regId);
		richk.checkName(regName);
		richk.checkAge(regAge);
		errMsg = richk.getErrMsg();
	}
}
