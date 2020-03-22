package org.formation.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.formation.model.Document;
import org.formation.model.Member;
import org.formation.repository.DocumentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DocumentRepositoryTest {

	@Autowired
	ApplicationContext context;
	
	@Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DocumentRepository repository;
    
    
    @Before
    public void setup() {
        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }
    @Test
    public void testExample() throws Exception {
    	Member firstMember = new Member();
		firstMember.setEmail("david@gmail.com");
		firstMember.setPassword("secret");
		firstMember.setPrenom("");
		firstMember.setNom("");
		firstMember.setRegisteredDate(new Date());
		Document doc1 = new Document();
		Document doc2 = new Document();
		firstMember.addDocument(doc1);
		firstMember.addDocument(doc2);	
        this.entityManager.persist(firstMember);
        
        List<Document> docs = this.repository.findByOwner(firstMember);
        assertThat(docs.size(),is(2));

    }
    
    
}
