//package com.example.chadi.Service;
//
//import com.example.chadi.Entity.Choice;
//import com.example.chadi.Entity.Group;
//import com.example.chadi.Interface.IGroup;
//import com.example.chadi.Repository.GroupRep;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class GroupImplService implements IGroup {
//    @Autowired
//    GroupRep groupRep;
//
//    @Override
//    public List<Group> afficherGroup() {
//        return groupRep.findAll();
//    }
//
//    @Override
//    public Group ajouterGroup(Group group) {
//        return groupRep.save(group);
//    }
//
//    @Override
//    public void supprimerGroup(Integer idGroup) {
//        groupRep.deleteById(idGroup);
//    }
//}
