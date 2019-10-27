package br.com.livreteca.service;

import br.com.livreteca.dto.UserDTO;
import br.com.livreteca.error.ValidationError;
import br.com.livreteca.model.dao.RoleDAO;
import br.com.livreteca.model.dao.UserDAO;
import br.com.livreteca.model.entity.Role;
import br.com.livreteca.model.entity.User;
import br.com.livreteca.util.JPAUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ronifabio on 01/05/2019.
 */
public class UserService extends AbstractService<String, User> {

    public UserService() {
        dao = new UserDAO();
    }

    public boolean saveUserAndRole(User user, Role role){
        RoleDAO roleDAO = new RoleDAO();

        boolean isSuccess = true;
        try {
            JPAUtil.beginTransaction();
            dao.save(user);
            roleDAO.save(role);
            JPAUtil.commit();
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
            JPAUtil.rollBack();
        } finally {
            JPAUtil.closeEntityManager();
        }
        return isSuccess;
    }

    public boolean deleteUserAndRole(String id){
        RoleDAO roleDAO= new RoleDAO();

        boolean isSuccess = true;
        try {
            User user = dao.getById(id);
            Role role = roleDAO.getById(id);

            JPAUtil.beginTransaction();
            dao.delete(user);
            roleDAO.delete(role);
            JPAUtil.commit();
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
            JPAUtil.rollBack();
        } finally {
            JPAUtil.closeEntityManager();
        }
        return isSuccess;
    }

    /**
     * Valida o parâmetro id usado em edições e remoções
     *
     * @param id
     * @return
     */
    public List<ValidationError> paramValidation(String id) {
        List<ValidationError> errors = new ArrayList<>();

        if (id == null || id.isEmpty()) {
            errors.add(new ValidationError("id", "O identificador do item é obrigatório."));
        }

        User user = getById(id);
        if (user == null) {
            errors.add(new ValidationError("id", "O item não foi encontrado."));
        }
        return (errors.isEmpty() ? null : errors);
    }

    /**
     * Valida os campos do formulário de dados pessoais.
     *
     * @param userDTO
     * @return
     */
    public List<ValidationError> formValidation(UserDTO userDTO) {
        List<ValidationError> errors = new ArrayList<>();

        if (userDTO.getLogin() == null || userDTO.getLogin().isEmpty()) {
            errors.add(new ValidationError("name", "O campo nome é obrigatório."));
        }

        if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) {
            errors.add(new ValidationError("email", "O campo email é obrigatório."));
        }

        return (errors.isEmpty() ? null : errors);
    }


}
