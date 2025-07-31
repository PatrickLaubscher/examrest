package com.hb.cda.examrest;

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
import com.hb.cda.examrest.model.Expenditure;
import com.hb.cda.examrest.model.Group;
import com.hb.cda.examrest.model.User;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;



@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@Transactional
public class ApiExpenditureTest {

    @Autowired
	MockMvc mvc;
	@Autowired
	EntityManager em;

    User user1 = new User();
    User user2 = new User();
    User user3 = new User();
    Group group1 = new Group();
    Group group2 = new Group();

    @BeforeEach
	void setUp() throws Exception {
        
        user1.setEmail("debtor@test.com");
        user1.setFirstname("debtor1");
        user1.setLastname("debtor");

        user2.setEmail("payer@test.com");
        user2.setFirstname("payer1");
        user2.setLastname("payerFirst");

        user3.setEmail("payer2@test.com");
        user3.setFirstname("payer2");
        user3.setLastname("payerSecond");

        group1.setName("group1");
        group1.setNumber(1);

        group2.setName("group2");
        group2.setNumber(2);

        em.persist(user1);
		em.persist(user2);
        em.persist(user3);
		em.persist(group1);
        em.persist(group2);

        Contributor contributor1 = new Contributor();
        contributor1.setUser(user1);
        contributor1.setGroup(group1);
        contributor1.setUserId(user1.getId());
        contributor1.setGroupId(group1.getId());

        Contributor contributor2 = new Contributor();
        contributor2.setUser(user2);
        contributor2.setGroup(group1);
        contributor2.setUserId(user2.getId());
        contributor2.setGroupId(group1.getId());

        Contributor contributor3 = new Contributor();
        contributor3.setUser(user3);
        contributor3.setGroup(group1);
        contributor3.setUserId(user3.getId());
        contributor3.setGroupId(group1.getId());

        Contributor contributor4 = new Contributor();
        contributor4.setUser(user1);
        contributor4.setGroup(group2);
        contributor4.setUserId(user1.getId());
        contributor4.setGroupId(group2.getId());
        
        em.persist(contributor1);
        em.persist(contributor2);
        em.persist(contributor3);
        em.persist(contributor4);

        Expenditure expenditure1 = new Expenditure();
        expenditure1.setGroup(group1);
        expenditure1.setAmount(10.0);
        expenditure1.setDescription("exp 1");
        expenditure1.setContributor(contributor2);

        Expenditure expenditure2 = new Expenditure();
        expenditure2.setGroup(group1);
        expenditure2.setAmount(25.0);
        expenditure2.setDescription("exp 2");
        expenditure2.setContributor(contributor2);

        Expenditure expenditure3 = new Expenditure();
        expenditure3.setGroup(group1);
        expenditure3.setAmount(35.0);
        expenditure3.setDescription("exp 2");
        expenditure3.setContributor(contributor3);
    
        em.persist(expenditure1);
        em.persist(expenditure2);
        em.persist(expenditure3);

        em.flush();
	}


    @Test
    @WithMockUser(username="payer@test.com")
    void shouldPersistExpenditure() throws Exception {
        mvc.perform(post("/api/expenditure/add")
        .contentType(MediaType.APPLICATION_JSON)
        .content("""
                {
                    "groupNumber": 1,
                    "amount": 20.0,
                    "description": "expenditure1 test"
                }        
                """))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.group.number").value(1))
        .andExpect(jsonPath("$.amount").value(20.0))
        .andExpect(jsonPath("$.contributor.user.email").value("payer@test.com"));
    }
    
    
    @Test
    @WithMockUser
    void shouldDisplayAllExpenditure() throws Exception {
        int groupNumber = 1;
        mvc.perform(get("/api/expenditure?groupNumber=" + groupNumber)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$.length()").value(3));
    }


    @Test
    @WithMockUser
    void shouldNotDisplayExpenditureIfNotPresent() throws Exception {
        int groupNumber = 2;
        mvc.perform(get("/api/expenditure?groupNumber=" + groupNumber)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$.length()").value(0));
    }

    

    @Test
    @WithMockUser
    void shouldDisplayExpenditureAndFilterByLastname() throws Exception {
        int groupNumber = 1;
        String lastname= "payerFirst";
        mvc.perform(get("/api/expenditure?groupNumber=" + groupNumber + "&lastname=" + lastname)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$.length()").value(2));
    }


    @Test
    @WithMockUser
    void shouldDisplayExpenditureAndFilterByFirst() throws Exception {
        int groupNumber = 1;
        String firstname = "payer2";
        mvc.perform(get("/api/expenditure?groupNumber=" + groupNumber + "&firstname=" + firstname)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    @WithMockUser
    void shouldDisplayExpenditureAndFilterByMinimumAmount() throws Exception {
        int groupNumber = 1;
        Double min = 30.0;
        mvc.perform(get("/api/expenditure?groupNumber=" + groupNumber + "&min=" + min)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$.length()").value(1));
    }


    @Test
    @WithMockUser
    void shouldDisplayExpenditureAndFilterByMaximumAmount() throws Exception {
        int groupNumber = 1;
        Double max = 30.0;
        mvc.perform(get("/api/expenditure?groupNumber=" + groupNumber + "&max=" + max)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$.length()").value(2));
    }


    
}
