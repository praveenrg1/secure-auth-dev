package com.secureauth.lab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.secureauth.lab.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//	@PersistenceContext
//	private EntityManager em;

//	@Transactional
//	// ❌ VULNERABLE QUERY (INTENTIONAL)
//	public List<User> login(String username, String password) {
//        String query = "SELECT * FROM secure_users WHERE username = '" + username +
//                "' AND password = '" + password + "'";
//
//        return em.createNativeQuery(query, User.class).getResultList();
//    }

	// plain password retrival
//	@Transactional
//	public List<User> login(String username, String password) {
//
//		String query = "SELECT * FROM secure_users WHERE username = :username AND password = :password";
//
//		return em.createNativeQuery(query, User.class).setParameter("username", username)
//				.setParameter("password", password).getResultList();
//	}

	// get user data and validate encoded password
//	@Transactional
	List<User> findByUsername(String userName);
//	{
//
//		String query = "SELECT * FROM secure_users WHERE username = :username";
//
//		return em.createNativeQuery(query, User.class).setParameter("username", username).getResultList();
//	}

//	@Transactional
//	public void save(User user) {
//		em.persist(user);
//	}
}
