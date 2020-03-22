package org.formation.repository;

import java.util.List;

import org.formation.model.Member;
import org.formation.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for Member data implemented using Spring Data JPA.
 * 
 * @author David THIBAU from Paul Chapman
 */
public interface PostRepository extends CrudRepository<Post, Long> {

	/**
	 * Find an Document with the id.
	 * 
	 * @param id
	 * @return
	 */
	public Post findById(long id);

	/**
	 * Find Documents by their owner
	 * 
	 * @param partialName
	 *            Any alphabetic string.
	 * @return The list of matching Members - always non-null, but may be
	 *         empty.
	 */
	@Query("select m.posts from Member m where m = ?1")
	public List<Post> findByAuthor(Member member);
	
	/**
	 * Find All Documents 
	 * 
	 * @param partialName
	 *            Any alphabetic string.
	 * @return The list of matching Members - always non-null, but may be
	 *         empty.
	 */
	public List<Post> findAll();
	/**
	 * Fetch the number of Members known to the system.
	 * 
	 * @return The number of Members.
	 */
	@Query("SELECT count(*) from Post")
	public int countPosts();
}
