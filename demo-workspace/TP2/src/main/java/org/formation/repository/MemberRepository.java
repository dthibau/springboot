package org.formation.repository;

import java.util.List;
import java.util.Optional;

import org.formation.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 * Repository for Member data implemented using Spring Data JPA.
 * 
 * @author David THIBAU
 */
/**
 * @author dthibau
 *
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

	
	/**
	 * Le membre ayant un email particulier.
	 * @param email
	 * @return
	 */
	public Optional<Member> findByEmail(String email);

	/**
	 * Find Members whose owner name contains the specified string
	 * 
	 * @param partialName
	 *            Any alphabetic string.
	 * @return The list of matching Members - always non-null, but may be
	 *         empty.
	 */
	public List<Member> findByNomContainingIgnoreCase(String partialNom);
	
	
	@Query("select m from Member m where m.nom like '%?1%' or m.prenom like '%?1%' or m.email like '%?1%' ")
	public List<Member> quickSearch(String partialNom);
	


	/**
	 * Return a Member via its email and password
	 * @param email
	 * @param password
	 * @return
	 */
	public Member findByEmailAndPassword(String email, String password);


	
	@Query("select m from Member m left join fetch m.documents where m.id=?1")
	public Member fullLoad(Long id);
}
