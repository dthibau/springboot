package org.formation.repository;

import java.util.List;
import java.util.Optional;

import org.formation.model.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for Member data implemented using Spring Data JPA.
 * 
 * @author Paul Chapman
 */
public interface MemberRepository extends CrudRepository<Member, Long> {


	/**
	 * Find Members whose owner name contains the specified string
	 * 
	 * @param partialName
	 *            Any alphabetic string.
	 * @return The list of matching Members - always non-null, but may be
	 *         empty.
	 */
	public List<Member> findByNomContainingIgnoreCase(String partialNom);
	
	
	
	/**
	 * Find Members with email and password
	 * @param emailAddress
	 * @param lastname
	 * @return
	 */
	public Optional<Member> findByEmailAndPassword(String email, String password);
	
	
	/**
	 * Find Members with only email
	 * @param email
	 * @return
	 */
	public Optional<Member> findByEmail(String email);

	/**
	 * Fetch the number of Members known to the system.
	 * 
	 * @return The number of Members.
	 */
	@Query("SELECT count(*) from Member")
	public int countMembers();
	
	@Query("select m from Member m left join fetch m.documents where m.id = ?1")
	public Member fullLoad(Long id);
}
