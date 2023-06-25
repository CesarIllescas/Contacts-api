package com.example.Contactsapi.rest;

import com.example.Contactsapi.domain.Contact;
import com.example.Contactsapi.payload.NewContactRequest;
import com.example.Contactsapi.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class ContactController {
    private final Logger log = LoggerFactory.getLogger(ContactController.class);
    private ContactService contactService;
    public ContactController(ContactService contactService){
        this.contactService = contactService;
    }
@GetMapping("/contacts/{id}")
    public ResponseEntity<Contact> findById(@PathVariable Long id){
        log.info("Rest request to find a contact");
        Optional<Contact> contactop =this.contactService.findById(id);
        if(contactop.isPresent()){
            return ResponseEntity.ok(contactop.get());
        }
        return ResponseEntity.notFound().build();
}
@GetMapping("/contacts")
    public List<Contact> findAll(){
        log.info("Rest request to find all contacs");
        return this.contactService.findAll();
}
@PostMapping("/contacts")
    public ResponseEntity<Contact> create(@RequestBody Contact contact){
        log.info("Rest request to create a contact");
        if(contact.getId() != null){
            log.warn("Trying to create a car with an existent id");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(this.contactService.save(contact));
}
@PutMapping("/contacts")
    public ResponseEntity<Contact> update(@RequestBody Contact contact){
    if(contact.getId() == null){
        log.warn("Trying to update a contact without an existent id");
        return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok(this.contactService.save(contact));
}
@DeleteMapping("/contacts/{id}")
    public ResponseEntity<Contact> delete(@PathVariable Long id){
        this.contactService.deleteById(id);
        return ResponseEntity.noContent().build();
}

@DeleteMapping("/contacts")
    public ResponseEntity<Contact> deleteAll(){
        this.contactService.deleteAll();
        return ResponseEntity.noContent().build();

}
@GetMapping("/contacts/name/{name}")
    public List<Contact> findByName(@PathVariable String name){
        log.info("Rest request to find a contact by his name");
        return this.contactService.findByName(name);
}
@GetMapping("/contacts/lastname/{lastname}")
    public List<Contact> findByLastName(@PathVariable String lastname){
        log.info("Rest request to find a contact by his name");
        return this.contactService.findByLastName(lastname);
    }
    @GetMapping("/contacts/ocupation/{ocupation}")
    public List<Contact> findByOcupation(@PathVariable String ocupation){
        log.info("Rest request to find a contact by his name");
        return this.contactService.findByOcupation(ocupation);
    }
    @GetMapping("/contacts/phonenumber/{phonenumber}")
    public List<Contact> findByPhoneNumber(@PathVariable String phoneNumber){
        log.info("Rest request to find a contact by his name");
        return this.contactService.findByPhoneNumber(phoneNumber);
    }
}
