package com.example.holidayplanner.group;

import com.example.holidayplanner.interfaces.ServiceInterface;
import com.example.holidayplanner.user.User;
import com.example.holidayplanner.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService implements ServiceInterface<Group> {

    private GroupRepository groupRepository;
    private UserRepository userRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String create(Group group) {
         groupRepository.insert(group);
         return "Group has been successfully created";
    }

    @Override
    public List<Group> getAll() { return groupRepository.findAll(); }

    @Override
    public String update(String entityId, Group newEntityInfo) {
        return null;
    }

    @Override
    public String delete(String entityId) {
        return null;
    }

    public String addGroupMember(String groupId, String userId) {

        User newGroupMember = userRepository.findById(userId).get();

        if (newGroupMember == null) { return "user with id " + userId + " does not exists"; }

        Group group = groupRepository.findById(groupId).get();

        group.addNewMember(newGroupMember);

        groupRepository.save(group);

        return newGroupMember.getFirstName() + " has been successfully added to " + group.getName();
    }

    public String removeGroupMember(String groupId, String userId) {
        Group group = groupRepository.findById(groupId).get();

        if (group == null) { return "group with id " + groupId + " does not exists"; }
        group.removeMember(userId);

        groupRepository.save(group);

        return "user with id: " + userId + " has been successfully removed from " + group.getName();
    }
}
