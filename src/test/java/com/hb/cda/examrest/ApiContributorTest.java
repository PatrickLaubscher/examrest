package com.hb.cda.examrest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.jsonPath;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import com.hb.cda.examrest.model.Contributor;
import com.hb.cda.examrest.model.Group;
import com.hb.cda.examrest.model.User;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;



@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@Transactional
public class ApiContributorTest {
    
    
    @Autowired
	MockMvc mvc;
	@Autowired
	EntityManager em;

    User user1 = new User();
    Group group1 = new Group();

    @BeforeEach
	void setUp() throws Exception {

        
        user1.setEmail("debtor@test.com");
        user1.setFirstname("debtor1");
        user1.setLastname("debtor");

        User user2 = new User();
        user2.setEmail("payer@test.com");
        user2.setFirstname("payer1");
        user2.setLastname("payer");

        group1.setName("group1");
        group1.setNumber(1);

        em.persist(user1);
		em.persist(user2);
		em.persist(group1);
    
        Contributor contributor1 = new Contributor();
        contributor1.setUser(user1);
        contributor1.setGroup(group1);
        contributor1.setUserId(user1.getId());
        contributor1.setGroupId(group1.getId()); 
        
        em.flush();
		
	}

    
    @Test
    void shouldPersistContributor() throws Exception {
        mvc.perform(post("/api/contributor/add"))
        .contentType(MediaType.APPLICATION_JSON)
        .content("""
                {
                    "email":"%s",
                    "groupNumber":"%s"
                }        
                """.formatted(user1.getEmail(), group1.getNumber()))
        .andExpect(status().isCreated())




    }

}
