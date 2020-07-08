package org.formation.json;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.formation.model.Document;
import org.formation.model.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.context.ApplicationContext;

@JsonTest
public class MemberTest {

	@Autowired
	ApplicationContext context;
	
	@Autowired
    private JacksonTester<Member> json;

	Member aMember;
	

	@BeforeEach
	public void setUp() {
		System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
        	if ( !(beanName.startsWith("org.") || beanName.startsWith("spring.")) )
                System.out.println(beanName);
        }
		
		aMember = new Member();
		aMember.setId(1);
		aMember.setEmail("dd@dd.fr");
		Document doc1 = new Document();
		doc1.setName("Toto");
		aMember.addDocument(doc1);
	}
    @Test
    public void testSerialize() throws Exception {

    	System.out.println(this.json.write(aMember));

        assertThat(this.json.write(aMember))
        	.hasJsonPathStringValue("@.email")
        	.hasEmptyJsonPathValue("@.documents")
        	.extractingJsonPathStringValue("@.email").isEqualTo("dd@dd.fr");
      
    }

    @Test
    public void testDeserialize() throws Exception {
        String content = "{\"id\":\"1\",\"email\":\"dd@dd.fr\"}";
        assertThat(this.json.parse(content))
                .isEqualTo(aMember);

        assertThat(this.json.parseObject(content).getEmail()).isEqualTo("dd@dd.fr");
    }
}
