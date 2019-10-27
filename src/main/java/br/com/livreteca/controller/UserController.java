package br.com.livreteca.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.livreteca.dto.UserDTO;
import br.com.livreteca.util.Constants;
import br.com.livreteca.util.Routes;
import br.com.livreteca.util.Sha256Generator;
import br.com.livreteca.error.ValidationError;
import br.com.livreteca.model.entity.Role;
import br.com.livreteca.model.entity.User;
import br.com.livreteca.model.mapper.UserMapper;
import br.com.livreteca.service.UserService;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(urlPatterns = { "/usuarios/cadastrar", "/a/usuarios/remover", "/a/usuarios/listar" })
public class UserController extends HttpServlet {

	 UserService userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String address = "";
		if (req.getServletPath().contains(Routes.CREATE)) {
			address = "/WEB-INF/view/user/register.jsp";
			req.getRequestDispatcher(address).forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		UserDTO userDTO = new UserDTO(name, password ,email );
		List<ValidationError> errors = formValidation(userDTO);

		boolean hasError = errors != null;

		if (hasError) {
			// reapresenta o formulário com os erros de validação
			sendError(req, resp, errors);
			return;
		}

		if (req.getServletPath().contains(Routes.CREATE)) {
			// persiste o usuário
			boolean isSuccess = persist(req, resp, userDTO);

			if (!isSuccess) {
				// reapresenta o formulário com a mensagem de erro
				String address = "/WEB-INF/view/user/register.jsp";

				errors = new ArrayList<>();
				errors.add(new ValidationError("", "Erro ao persistir os dados."));

				req.setAttribute("errors", errors);
				req.getRequestDispatcher(address).forward(req, resp);
				return;
			}

			// formulario de login
			String address = req.getContextPath() + "/login";
			resp.sendRedirect(address);
		}
	}

	private boolean persist(HttpServletRequest req, HttpServletResponse resp, UserDTO userDTO) {
		User user = UserMapper.toEntity(userDTO);

        final String hashed = Sha256Generator.generate(user.getPwd());
        user.setPwd(hashed);

        Role role = new Role(userDTO.getLogin(), Constants.USER);
        
        return userService.saveUserAndRole(user, role);
	}

	private void sendError(HttpServletRequest req, HttpServletResponse resp, List<ValidationError> errors)
			throws ServletException, IOException {
		String address = "/WEB-INF/view/user/register.jsp";
		req.setAttribute("errors", errors);
		req.getRequestDispatcher(address).forward(req, resp);

	}

	private List<ValidationError> formValidation(UserDTO userDTO) {
		List<ValidationError> errors = new ArrayList<>();

		if (userDTO.getLogin() == null || userDTO.getLogin().isEmpty()) {
			errors.add(new ValidationError("name", "O campo nome é obrigatório."));
		}

		if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) {
			errors.add(new ValidationError("email", "O campo email é obrigatório."));
		}

		if (userDTO.getPwd() == null || userDTO.getPwd().isEmpty()) {
			errors.add(new ValidationError("password", "O campo senha é obrigatório."));
		}

		return (errors.isEmpty() ? null : errors);
	}
}
