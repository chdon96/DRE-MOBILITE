//package com.example.chadi.Service;
//
//import com.example.chadi.Entity.Choice;
//import com.example.chadi.Entity.User;
//import com.example.chadi.Interface.IChoice;
//import com.example.chadi.Repository.ChoiceRep;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
//
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//@Slf4j
//public class ChoiceImplService implements IChoice {
//    @Autowired
//    ChoiceRep choiceRep;
//
//    @Override
//    public List<Choice> afficherChoice() {
//        log.info("fetching all choices");
//        return choiceRep.findAll();
//    }
//
//    @Override
//    public Choice ajouterChoice(Choice choice) {
//        return choiceRep.save(choice);
//    }
//
//    @Override
//    public void supprimerChoice(Integer IdChoice) {
//
//    }
//
//    @Override
//    public User modifierChoice(Choice choice, int idChoice) {
//        return null;
//    }
//}
