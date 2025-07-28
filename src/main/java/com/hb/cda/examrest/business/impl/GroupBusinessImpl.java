package com.hb.cda.examrest.business.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        
        Integer lastNumber = groupRepository.findMaxNumero().orElse(0);
        group.setNumber(lastNumber+1);
        group.setTotal(0.0);

        Group newGroup = groupRepository.save(group);

        return newGroup;
    }


    @Override
    public Group findGroup(int groupNumber) {

        Group group = groupRepository.findByNumber(groupNumber).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le groupe n'existe pas")
        );
        return group;
    }
    

    @Override
    public List<Group> findAllGroupByUser(String email) {

        List<Group> groups = groupRepository.findAllByUserEMail(email);
        return groups;
    }


    @Override
    public void deleteGroup(Group group) {
        // TODO
        throw new UnsupportedOperationException("Unimplemented method 'activateUser'");
    }

    
}
