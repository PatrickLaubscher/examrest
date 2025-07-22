package com.hb.cda.examrest.business;

import org.springframework.stereotype.Service;

import com.hb.cda.examrest.model.Group;

@Service
public interface GroupBusiness {
    Group createGroup (Group group);
    void deleteGroup (Group group);
}
