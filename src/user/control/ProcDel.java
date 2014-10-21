package user.control;

import java.io.File;
import java.io.IOException;

import user.bean.RegistrantInfo;
import user.parts.ReadRegistInfo;
import user.parts.RegInfDAO;
import user.parts.WriteRegistInfo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegistList
 */
public class ProcDel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcDel() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RegInfDAO rDAO = null;
		try {

			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			RequestDispatcher rd = null;
			rDAO = new RegInfDAO();
			// �Ώۂ̓o�^�҂��擾
			HttpSession session = request.getSession(false);
			RegistrantInfo inputInfo = (RegistrantInfo)session.getAttribute("targetInfo");
			
			// �f�[�^����������
			rDAO.delete(inputInfo.getrId());

			// ���ʉ�ʂ֑J�ڂ��邽�߂̏���ݒ�
			request.setAttribute("delInfo", inputInfo);
			rd = request.getRequestDispatcher("/DelResult.jsp");

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
			rDAO.close();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}
}
