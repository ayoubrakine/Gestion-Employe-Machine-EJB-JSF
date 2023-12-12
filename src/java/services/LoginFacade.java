package services;

import entities.Admin;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class LoginFacade {

    @PersistenceContext(unitName = "Control_emp_jsfPU")
    private EntityManager entityManager;

    public Admin authenticate(String username, String password) {
        TypedQuery<Admin> query = entityManager.createQuery(
            "SELECT a FROM Admin a WHERE a.username = :username AND a.password = :password",
            Admin.class);
        query.setParameter("username", username);
        query.setParameter("password", password);

        try {
            return query.getSingleResult();
        } catch (Exception e) {
            // Gérer les éventuelles exceptions ou retourner null si aucune correspondance n'est trouvée
            return null;
        }
    }
}
