package com.example.Contactsapi.service;

import com.example.Contactsapi.domain.Contact;
import com.example.Contactsapi.domain.User;
import com.example.Contactsapi.repository.ContactRespository;
import com.example.Contactsapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService{
    @Autowired
    private UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(ContactServiceImpl.class);
    private ContactRespository contactRespository;

    public ContactServiceImpl(ContactRespository contactRespository) {
        this.contactRespository = contactRespository;
    }

    @Override
    public List<Contact> findAll() {
        return this.contactRespository.findAll();
    }

    @Override
    public Contact save(Contact contact) {
        if(!this.validateContact(contact)){
            return null;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepository.findByUsername(name).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + name));

        contact.setGeneratedBy(user.getId());

        Contact contactDB = this.contactRespository.save(contact);
        return contactDB;
    }
    @Override
    public Optional<Contact> findById(Long id) {
        log.info("Executing findById");
        return this.contactRespository.findById(id);
    }
    private boolean validateContact(Contact contact) {
        // car null validation
        if (contact == null) {
            log.warn("Trying to create null car");
            return false;
        }
        // num doors validation
        if (contact.getLastName() == null || contact.getName()==null) {
            log.warn("Trying to create a contact without name or lastname");
            return false;
        }

        // color validation
        // ....

        return true;
    }
    @Override
    public void deleteById(Long id) {
        log.info("Deleting all contacts");
        if(id==null|| id<0|| id==0){
            log.warn("Trying to delete a contact with an invalid id");
            return;
        }
        try{
            this.contactRespository.deleteById(id);
        }
        catch (Exception e){
            log.error("Error trying to delete contact by id {}",id,e);
        }
    }

    @Override
    public void deleteAll() {
        log.info("deleting all cars");
        this.contactRespository.deleteAll();
    }

    @Override
    public List<Contact> findByName(String name) {
        if(name==null){
            log.warn("Trying to find a contact with an invalid name");
            return null;
        }
        return this.contactRespository.findByName(name);

    }

    @Override
    public List<Contact> findByLastName(String lastname) {
        if(lastname==null){
            log.warn("Trying to find a contact with an invalid name");
            return null;
        }
        return  this.contactRespository.findByName(lastname);

    }

    @Override
    public List<Contact> findByOcupation(String ocupation) {
        if(ocupation==null){
            log.warn("Trying to find a contact with an invalid name");
            return null;
        }
        return  this.contactRespository.findByName(ocupation);

    }

    @Override
    public List<Contact> findByPhoneNumber(String phoneNumber) {
        if(phoneNumber==null){
            log.warn("Trying to find a contact with an invalid name");
            return null;
        }
        return this.contactRespository.findByName(phoneNumber);

    }
}
