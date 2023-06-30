package com.example.Contactsapi.rest;

import com.example.Contactsapi.domain.Contact;
import com.example.Contactsapi.payload.NewContactRequest;
import com.example.Contactsapi.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
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
@Operation(
        summary = "Retrieve a contact by Id",
        description = "Get a contact object by specifying its id. The response is contact object with id, name, lastname, phoneNumber, ocupation, img, and the user who generated it.",
        tags = { "contacts", "get" })
    public ResponseEntity<Contact> findById(@PathVariable Long id){
        log.info("Rest request to find a contact");
        Optional<Contact> contactop =this.contactService.findById(id);
        if(contactop.isPresent()){
            return ResponseEntity.ok(contactop.get());
        }
        return ResponseEntity.notFound().build();
}
    @Operation(
            summary = "Retrieve all contacts",
            description = "Get all contact objects by specifying its id. The response is contact object with id, name, lastname, phoneNumber, ocupation, img, and the user who generated it.",
            tags = { "contacts", "get" })
@GetMapping("/contacts")
    public List<Contact> findAll(){
        log.info("Rest request to find all contacs");
        return this.contactService.findAll();
}
    @Operation(
            summary = "Creates a contact ",
            description = "Creates a contact object. The request is contact object with id, name, lastname, phoneNumber, ocupation, img, and the user who generated it.",
            tags = { "contacts", "post" })
@PostMapping("/contacts")
    public ResponseEntity<Contact> create(@RequestBody Contact contact){
        log.info("Rest request to create a contact");
        if(contact.getId() != null){
            log.warn("Trying to create a car with an existent id");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(this.contactService.save(contact));
}

    @Operation(
            summary = "Updates a contact",
            description = "Updates a contact object. The response is contact object with id, name, lastname, phoneNumber, ocupation, img, and the user who generated it.",
            tags = { "contacts", "put" })
@PutMapping("/contacts")
    public ResponseEntity<Contact> update(@RequestBody Contact contact){
    if(contact.getId() == null){
        log.warn("Trying to update a contact without an existent id");
        return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok(this.contactService.save(contact));
}
    @Operation(
            summary = "Deletes a contact by Id",
            description = "Deletes a contact object by specifying its id. The response is a 200 status",
            tags = { "contacts", "delete" })
@DeleteMapping("/contacts/{id}")
    public ResponseEntity<Contact> delete(@PathVariable Long id){
        this.contactService.deleteById(id);
        return ResponseEntity.noContent().build();
}
    @Operation(
            summary = "Deletes all contacts ",
            description = "Deletes all contact objects. The response is a 200 status",
            tags = { "contacts", "delete" })
@DeleteMapping("/contacts")
    public ResponseEntity<Contact> deleteAll(){
        this.contactService.deleteAll();
        return ResponseEntity.noContent().build();

}
    @Operation(
            summary = "Retrieve a contact by name",
            description = "Get a contact object by specifying its name. The response is contact object with id, name, lastname, phoneNumber, ocupation, img, and the user who generated it.",
            tags = { "contacts", "get" })
@GetMapping("/contacts/name/{name}")
    public List<Contact> findByName(@PathVariable String name){
        log.info("Rest request to find a contact by his name");
        return this.contactService.findByName(name);
}
    @Operation(
            summary = "Retrieve a contact by lastname",
            description = "Get a contact object by specifying its lastname. The response is contact object with id, name, lastname, phoneNumber, ocupation, img, and the user who generated it.",
            tags = { "contacts", "get" })
@GetMapping("/contacts/lastname/{lastname}")
    public List<Contact> findByLastName(@PathVariable String lastname){
        log.info("Rest request to find a contact by his name");
        return this.contactService.findByLastName(lastname);
    }
    @Operation(
            summary = "Retrieve a contact by ocupation",
            description = "Get a contact object by specifying its ocupation. The response is contact object with id, name, lastname, phoneNumber, ocupation, img, and the user who generated it.",
            tags = { "contacts", "get" })
    @GetMapping("/contacts/ocupation/{ocupation}")
    public List<Contact> findByOcupation(@PathVariable String ocupation){
        log.info("Rest request to find a contact by his name");
        return this.contactService.findByOcupation(ocupation);
    }
    @Operation(
            summary = "Retrieve a contact by phonenumber",
            description = "Get a contact object by specifying its phonenumber. The response is contact object with id, name, lastname, phoneNumber, ocupation, img, and the user who generated it.",
            tags = { "contacts", "get" })
    @GetMapping("/contacts/phonenumber/{phonenumber}")
    public List<Contact> findByPhoneNumber(@PathVariable String phoneNumber){
        log.info("Rest request to find a contact by his name");
        return this.contactService.findByPhoneNumber(phoneNumber);
    }
}
