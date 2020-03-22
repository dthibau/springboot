package org.formation.repository;

import java.util.List;

import org.formation.model.Document;
import org.formation.model.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

/**
 * Repository for Member data implemented using Spring Data JPA.
 * 
 * @author David THIBAU from Paul Chapman
 */
public interface DocumentRepository extends Repository<Document, Long> {

	/**
	 * Find an Document with the id.
	 * 
	 * @param id
	 * @return
	 */
	public Document findById(long id);

	/**
	 * Find Documents by their owner
	 * 
	 * @param partialName
	 *            Any alphabetic string.
	 * @return The list of matching Members - always non-null, but may be
	 *         empty.
	 */
	@Query("select m.documents from Member m where m = ?1")
	public List<Document> findByOwner(Member member);
	
	/**
	 * Find All Documents 
	 * 
	 * @param partialName
	 *            Any alphabetic string.
	 * @return The list of matching Members - always non-null, but may be
	 *         empty.
	 */
	public List<Document> findAll();
	/**
	 * Fetch the number of Members known to the system.
	 * 
	 * @return The number of Members.
	 */
	@Query("SELECT count(*) from Document")
	public int countDocuments();
}
