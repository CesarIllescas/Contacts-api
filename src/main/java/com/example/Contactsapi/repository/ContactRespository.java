package com.example.Contactsapi.repository;

import com.example.Contactsapi.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRespository extends JpaRepository<Contact,Long> {

    List<Contact> findByName(String name);
    List<Contact> findByLastName(String lastname);
    List<Contact> findByOcupation(String ocupation);
    List<Contact> findByPhoneNumber(String phoneNumber);
}
