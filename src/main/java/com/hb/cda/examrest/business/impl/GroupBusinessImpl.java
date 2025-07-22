package com.hb.cda.examrest.business.impl;

import org.springframework.stereotype.Service;

import com.hb.cda.examrest.business.GroupBusiness;
import com.hb.cda.examrest.business.exception.GroupAlreadyExistsException;
import com.hb.cda.examrest.model.Group;
import com.hb.cda.examrest.repository.GroupRepository;

@Service
public class GroupBusinessImpl implements GroupBusiness {

    GroupRepository groupRepository;
    

    public GroupBusinessImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }


    @Override
    public Group createGroup (Group group) {

        if (groupRepository.findByName(group.getName()).isPresent()) {
            throw new GroupAlreadyExistsException();
        }
       Group newGroup = groupRepository.save(group);

       return newGroup;
    }


    @Override
    public void deleteGroup(Group group) {
        // TODO
        throw new UnsupportedOperationException("Unimplemented method 'activateUser'");
    }
    
}
