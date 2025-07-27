package com.hb.cda.examrest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hb.cda.examrest.model.Contributor;
import com.hb.cda.examrest.model.Group;
import com.hb.cda.examrest.model.Repayment;
import com.hb.cda.examrest.model.User;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;



@SpringBootTest(classes = ExamrestApplication.class)
@AutoConfigureTestDatabase
@AutoConfigureMockMvc(addFilters = false)
@Transactional
public class ApiRepaymentTest {
    
    @Autowired
	MockMvc mvc;
	@Autowired
	EntityManager em;


    @BeforeEach
	void setUp() throws Exception {

        User user1 = new User();
        user1.setEmail("debtor@test.com");
        user1.setFirstname("debtor1");
        user1.setLastname("debtor");

        User user2 = new User();
        user2.setEmail("payer@test.com");
        user2.setFirstname("payer1");
        user2.setLastname("payer");

        Group group1 = new Group();
        group1.setName("group1");
        group1.setNumber(1);

        em.persist(user1);
		em.persist(user2);
		em.persist(group1);
    
        Contributor debtor = new Contributor();
        debtor.setUser(user1);
        debtor.setGroup(group1);
        debtor.setUserId(user1.getId());
        debtor.setGroupId(group1.getId()); 
        
        Contributor payer = new Contributor();
        payer.setUser(user2);
        payer.setGroup(group1);
        payer.setUserId(user2.getId());
        payer.setGroupId(group1.getId()); 

        Repayment repayment1 = new Repayment();
        repayment1.setDebtor(debtor);
        repayment1.setPayer(payer);
        repayment1.setGroup(group1);
        repayment1.setAmount(20.0);
        repayment1.setPayed(false);

        em.persist(debtor);
        em.persist(payer);
        em.persist(repayment1);
        em.flush();
		
	}


    @Test
    void completeRepaymentShouldBySetTrue() throws Exception {

        mvc.perform(post("/api/repayment/confirm-payment")
        .contentType(MediaType.APPLICATION_JSON)
		.content("""
			{
				"email":"debtor@test.com",
				"groupNumber": 1,
                "amount": 20.0,
                "payerFirstname": "payer1"
			}   
		    """))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.payed").value(true));
    }


    



}
