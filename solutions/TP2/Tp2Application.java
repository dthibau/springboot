package org.formation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.formation.model.Document;
import org.formation.model.Member;
import org.formation.repository.DocumentRepository;
import org.formation.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Tp2Application implements CommandLineRunner {

	private static final Logger logger = LoggerFactory
			.getLogger(Tp2Application.class);
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	DocumentRepository documentRepository;
	
	@Autowired
	EntityManager entityManager;
	
	@Value("${app.email}")
    private String email;
	
	public static void main(String[] args) {
		SpringApplication.run(Tp2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		_doSomeJpaTests();
		
	}

	private void _doSomeJpaTests() {
		Member newMember = new Member();
		newMember.setAge(18);
		newMember.setEmail(email);
		newMember.setPassword("secret");
		
		Document doc = new Document();
		doc.setContentType("dummy");
		doc.setName("Dummy.doc");
		doc.setUploadedDate(new Date());
		
		newMember.addDocument(doc);
		
		logger.info("******** TEST AJOUT d'UN MEMBRE ET DE SES DOCUMENTS ********");
		logger.info("Nombre de membres avant " + memberRepository.count());
		memberRepository.save(newMember);		
		logger.info("New id for newMember : " + newMember.getId());
		logger.info("Nombres de membre après: " + memberRepository.count());
		
		logger.info("******** TEST FULL LOAD *********");
		entityManager.clear();
		memberRepository.fullLoad(newMember.getId());	
		
		logger.info("******** TEST LOAD ALL*********");
		logger.info("Membres en base : " + memberRepository.findAll());
	
		
		logger.info("******** TEST FIND BY OWNER *********");
		Optional<Member> first = memberRepository.findById(1l);
		List<Document> docs = documentRepository.findByOwner(first.get());
		logger.info("Docs of " + first.get().getNom() +" are " + docs);
		
		docs = documentRepository.findByOwner(newMember);
		logger.info("Docs of " + newMember.getNomComplet() +" are " + docs);
		
		Member member1 = memberRepository.fullLoad(1l);
		logger.info(""+member1.getDocuments());
		
		logger.info("******** USE OF assertions ( -ea) *********");
		Optional<Member> optMember = memberRepository.findByEmail("toto@email.com");
		assert !optMember.isPresent();
		
		optMember = memberRepository.findByEmail("david.thibau@gmail.com");
		assert optMember.isPresent();
		assert optMember.get().getNom().equals("THIBAU");
		
	}
}
