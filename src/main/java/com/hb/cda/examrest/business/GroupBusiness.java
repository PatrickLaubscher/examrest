package com.hb.cda.examrest.business;

import java.util.List;

import com.hb.cda.examrest.model.Group;


public interface GroupBusiness {
    Group createGroup (Group group);
    Group findGroup(int groupNumber);
    void deleteGroup (Group group);
    List<Group> findAllGroupByUser(String email);
}
