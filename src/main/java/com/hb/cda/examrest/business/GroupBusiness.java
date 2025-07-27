package com.hb.cda.examrest.business;

import com.hb.cda.examrest.model.Group;


public interface GroupBusiness {
    Group createGroup (Group group);
    Group findGroup(int groupNumber);
    void deleteGroup (Group group);
}
