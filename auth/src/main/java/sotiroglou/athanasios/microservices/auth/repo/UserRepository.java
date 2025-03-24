package sotiroglou.athanasios.microservices.auth.repo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import sotiroglou.athanasios.microservices.auth.dom.Address;
import sotiroglou.athanasios.microservices.auth.dom.AuthUser;
import sotiroglou.athanasios.microservices.auth.dom.Card;

import java.util.List;

@ApplicationScoped
public class UserRepository {
    @PersistenceContext
    EntityManager em;

    public AuthUser findByUsername(String username) {
        return em.createQuery("SELECT u FROM AuthUser u JOIN FETCH u.authRoles WHERE u.username = :username", AuthUser.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    public void createAuthUser(AuthUser authUser) {
        em.persist(authUser);
    }


    @Transactional
    public AuthUser updateAuthUser(AuthUser authUser) {
        return em.merge(authUser);
    }

    public AuthUser findById(Long id) {
        return em.find(AuthUser.class, id);
    }

    @Transactional
    public void createAddress(Address address) {
        em.persist(address);
    }

    public List<Address> findAddressesByUserId(Long userId) {
        return em.createQuery("SELECT a FROM Address a WHERE a.user.authUserId = :userId", Address.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Transactional
    public void createCard(Card card) {
        em.persist(card);
    }

    public List<Card> findCardsByUserId(Long userId) {
        return em.createQuery("SELECT c FROM Card c WHERE c.user.authUserId = :userId", Card.class)
                .setParameter("userId", userId)
                .getResultList();
    }


}
