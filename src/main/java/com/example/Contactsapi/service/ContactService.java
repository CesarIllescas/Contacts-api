package com.example.Contactsapi.service;

import com.example.Contactsapi.domain.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    Optional<Contact> findById(Long id);
    List<Contact> findAll();

    Contact save(Contact contact);
    void  deleteById(Long id);
    void deleteAll();

    List<Contact> findByName(String name);
    List<Contact> findByLastName(String lastname);
    List<Contact> findByOcupation(String ocupation);
    List<Contact> findByPhoneNumber(String phoneNumber);
}
