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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hb.cda.examrest.model.Contributor;
import com.hb.cda.examrest.model.Group;
import com.hb.cda.examrest.model.User;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;



@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@Transactional
public class ApiGroupTest {

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
    @WithMockUser()
    void shouldPersistGroupWithoutDescription() throws Exception {
        mvc.perform(post("/api/group/create")
        .contentType(MediaType.APPLICATION_JSON)
        .content("""
                {
                    "name":"groupeTest"
                }        
                """))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.number").value(2))
        .andExpect(jsonPath("$.description").value((Object) null));
    }
    

    @Test
    @WithMockUser()
    void shouldPersistGroupWithDescription() throws Exception {
        mvc.perform(post("/api/group/create")
        .contentType(MediaType.APPLICATION_JSON)
        .content("""
                {
                    "name":"groupeTest",
                    "description":"descriptionTest"
                }        
                """))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.number").value(2))
        .andExpect(jsonPath("$.description").value("descriptionTest"));
    }
    
}
