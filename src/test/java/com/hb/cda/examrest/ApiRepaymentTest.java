package com.hb.cda.examrest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hb.cda.examrest.model.Contributor;
import com.hb.cda.examrest.model.Group;
import com.hb.cda.examrest.model.Repayment;
import com.hb.cda.examrest.model.User;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;




@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@Transactional
class ApiRepaymentTest {
    
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
        user2.setEmail("payer2@test.com");
        user2.setFirstname("payer1");
        user2.setLastname("payer1");

        User user3 = new User();
        user2.setEmail("payer2@test.com");
        user2.setFirstname("payer2");
        user2.setLastname("payer2");

        Group group1 = new Group();
        group1.setName("group1");
        group1.setNumber(1);

        em.persist(user1);
		em.persist(user2);
        em.persist(user3);
		em.persist(group1);
    
        Contributor debtor = new Contributor();
        debtor.setUser(user1);
        debtor.setGroup(group1);
        debtor.setUserId(user1.getId());
        debtor.setGroupId(group1.getId()); 
        
        Contributor payer1 = new Contributor();
        payer1.setUser(user2);
        payer1.setGroup(group1);
        payer1.setUserId(user2.getId());
        payer1.setGroupId(group1.getId());
        
        Contributor payer2 = new Contributor();
        payer2.setUser(user3);
        payer2.setGroup(group1);
        payer2.setUserId(user2.getId());
        payer2.setGroupId(group1.getId()); 

        Repayment repayment1 = new Repayment();
        repayment1.setDebtor(debtor);
        repayment1.setPayer(payer1);
        repayment1.setGroup(group1);
        repayment1.setAmount(20.0);
        repayment1.setPayed(false);

        Repayment repayment2 = new Repayment();
        repayment2.setDebtor(debtor);
        repayment2.setPayer(payer2);
        repayment2.setGroup(group1);
        repayment2.setAmount(30.0);
        repayment2.setPayed(false);

        em.persist(debtor);
        em.persist(payer1);
        em.persist(repayment1);
        em.persist(repayment2);
        em.flush();
		
	}


    @Test
    @WithMockUser(username="debtor@test.com")
    void shouldReturnListOfRepaymentNotPayed() throws Exception {
        String type = "due";
        int groupNumber = 1;
        mvc.perform(get("/api/repayment?groupNumber=" + groupNumber + "&type=" + type)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isMap())
            .andExpect(jsonPath("$.repayments.length()").value(2))
            .andExpect(jsonPath("$.repayments.[0].payed").value(false))
            .andExpect(jsonPath("$.repayments.[1].payed").value(false));
    }



    @Test
    @WithMockUser(username="debtor@test.com")
    void shouldSendExceptionIfAmountIsNotCorrect() throws Exception {
        mvc.perform(post("/api/repayment/confirm-payment")
        .contentType(MediaType.APPLICATION_JSON)
		.content("""
			{
				"groupNumber": 1,
                "amount": 10.0,
                "payerFirstname": "payer1"
			}   
		    """))
        .andExpect(status().isBadRequest())
        .andExpect(result -> {
            String content = result.getResolvedException().getMessage();
            assertTrue(content.contains("Le montant du remboursement ne correspond pas entièrement"));
        });
    }  

    

    @Test
    @WithMockUser(username="debtor@test.com")
    void completeRepaymentShouldBySetTrue() throws Exception {
        mvc.perform(post("/api/repayment/confirm-payment")
        .contentType(MediaType.APPLICATION_JSON)
		.content("""
			{
				"groupNumber": 1,
                "amount": 20.0,
                "payerFirstname": "payer1"
			}   
		    """))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.payed").value(true));
    }

}
