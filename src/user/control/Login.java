package user.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.arnx.jsonic.JSON;
import user.bean.UserAuth;
import user.bean.UserInfo;
import employ.OutLog;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String REQUEST_STRING = "requestJs";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("/Login.jsp");
		HttpSession session = request.getSession(true);
		session.invalidate();
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			String parameter = request.getParameter(REQUEST_STRING);
			UserInfo uInfo = JSON.decode(parameter, UserInfo.class);

			UserAuth uAuth = new UserAuth();

			HttpSession session = null;
			RequestDispatcher rd = null;

			// �F��
			if (uAuth.execute(uInfo) == true) {
				// �F��OK
				// �Z�b�V������V�K�쐬
				session = request.getSession(true);

				// ���[�U����ݒ肷��
				session.setAttribute("userName", uInfo.getUserName());

				rd = request.getRequestDispatcher("/MenuList.jsp");
				OutLog.outLogDmp("���O�C�����[�U :" + uInfo.getUserName()
						+ ", ���O�C���p�X���[�h :" + uInfo.getUserPass());

			} else {
				request.setAttribute("errMsg",
						"���[�U�A�܂��̓p�X���[�h���قȂ��Ă��܂��B���Ȃ����Ă��������B");

				rd = request.getRequestDispatcher("/Login.jsp");
			}
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errMsg",
					"�G���[���������܂����B�\���󂠂�܂��񂪂�����x�ŏ����炨�肢���܂��B");
			HttpSession session = request.getSession(true);
			session.invalidate();
			RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
			rd.forward(request, response);
		}
	}

	public boolean checkSession(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (session != null) {
			return true;
		} else {
			return false;
		}
	}

}
