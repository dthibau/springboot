package org.formation.repository;

import java.util.List;

import org.formation.model.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Repository for Member data implemented using Spring Data JPA.
 * 
 * @author Paul Chapman
 */
//@RepositoryRestResource(collectionResourceRel = "people", path = "salaries")
public interface MemberRepository extends CrudRepository<Member, Long> {
	/**
	 * Find an Member with the specified Member number.
	 *
	 * @param MemberNumber
	 * @return The Member if found, null otherwise.
	 */
	public Member findById(long id);

	/**
	 * Find Members whose owner name contains the specified string
	 * 
	 * @param partialName
	 *            Any alphabetic string.
	 * @return The list of matching Members - always non-null, but may be
	 *         empty.
	 */
	public List<Member> findByNomContainingIgnoreCase(@Param ("partialName") String partialNom);
	
	
	
	/**
	 * Find Members with email and password
	 * @param emailAddress
	 * @param lastname
	 * @return
	 */
	public Member findByEmailAndPassword(String email, String password);

	/**
	 * Fetch the number of Members known to the system.
	 * 
	 * @return The number of Members.
	 */
	@Query("SELECT count(*) from Member")
	public int countMembers();
	
	
}
