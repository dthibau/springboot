package org.formation.repository;

import java.util.List;

import org.formation.model.Document;
import org.formation.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repository for Member data implemented using Spring Data JPA.
 * 
 * @author David THIBAU from Paul Chapman
 */
public interface DocumentRepository extends JpaRepository<Document, Long> {

	

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
	

}
