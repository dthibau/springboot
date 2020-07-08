package org.formation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.formation.model.Customer;
import org.formation.model.Document;
import org.formation.model.Member;
import org.formation.repository.CustomerRepository;
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
public class Tp2AApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(Tp2AApplication.class);

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	DocumentRepository documentRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Value("${my.email}")
	private String email;

	public static void main(String[] args) {
		SpringApplication.run(Tp2AApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		_doSomeJpaTests();

//		_playWithMongo();

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

		logger.info("Nombre de membres " + memberRepository.count());
		memberRepository.save(newMember);
		logger.info("New id for newMember : " + newMember.getId());
		logger.info("After insert : " + memberRepository.count());

		memberRepository.fullLoad(newMember.getId());

		logger.info("Membres en base : " + memberRepository.findAll());

		Member first = memberRepository.findById(1l).orElseThrow(() -> new IllegalArgumentException("Invalide ID"));

		List<Document> docs = documentRepository.findByOwner(first);
		System.out.println("Docs of " + first.getNom() + " are " + docs);

		docs = documentRepository.findByOwner(newMember);
		logger.info("Docs of " + newMember.getNomComplet() + " are " + docs);

		Member member1 = memberRepository.fullLoad(1l);
		logger.info("" + member1.getDocuments());

		Optional<Member> optMember = memberRepository.findByEmail("toto@email.com");
		assert !optMember.isPresent();
		optMember = memberRepository.findByEmail("david.thibau@gmail.com");
		assert optMember.isPresent();
		assert optMember.get().getNom().equals("THIBAU");

	}

	private void _playWithMongo() {
		customerRepository.deleteAll();
		// save a couple of customers
		customerRepository.save(new Customer("Alice", "Smith"));
		customerRepository.save(new Customer("Bob", "Smith"));
		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : customerRepository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();
		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(customerRepository.findByFirstName("Alice"));
		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Customer customer : customerRepository.findByLastName("Smith")) {
			System.out.println(customer);
		}
	}

}
