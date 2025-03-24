package sotiroglou.athanasios.microservices.auth.repo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import sotiroglou.athanasios.microservices.auth.dom.AuthRole;
import sotiroglou.athanasios.microservices.auth.dom.AuthUser;

import java.util.List;

@ApplicationScoped
public class AuthRepository {
    @PersistenceContext
    EntityManager em;

    @Transactional
    public void createAuthRole(AuthRole authRole) {
        em.persist(authRole);
    }

    public AuthRole findRoleByName(AuthRole.RoleName roleName) {
        return em.createQuery("SELECT r FROM AuthRole r WHERE r.roleName = :roleName", AuthRole.class)
                .setParameter("roleName", roleName)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    public List<AuthUser> findAllUsers() {
        return em.createQuery("SELECT u FROM AuthUser u", AuthUser.class)
                .getResultList();
    }
}
