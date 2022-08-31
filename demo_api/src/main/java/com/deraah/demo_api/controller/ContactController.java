package com.deraah.demo_api.controller;


import com.deraah.demo_api.dto.ContactDto;
import com.deraah.demo_api.dto.CreateOrUpDateContactFormDto;
import com.deraah.demo_api.entity.Contact;
import com.deraah.demo_api.exceptions.IllegalOperatorException;
import com.deraah.demo_api.exceptions.ResourceNotFoundException;
import com.deraah.demo_api.service.ContactServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContactController {

    @Autowired
    private ContactServices contactServices;


    @GetMapping("/contacts")
    public ResponseEntity findAllContact(Pageable pageable, @RequestParam(value = "search",defaultValue = "")String search){


        Page<ContactDto> contactDtoPage = this.contactServices.findAll(pageable,search).map(ContactDto::new);
        return new ResponseEntity(contactDtoPage, HttpStatus.OK);
    }
    @PostMapping("/contacts")
    public ResponseEntity  createNewContact(@RequestBody @Validated CreateOrUpDateContactFormDto createOrUpDateContactFormDto){


        Contact contact = this.contactServices.createNewContact(createOrUpDateContactFormDto);

        return new ResponseEntity(new ContactDto(contact),HttpStatus.OK);
    }

    @GetMapping("/contacts/{id}")
    public ResponseEntity findOneContactById(@PathVariable("id")Long id) throws ResourceNotFoundException {

        Contact contact=this.contactServices.findOneById(id);
        return new ResponseEntity(new ContactDto(contact),HttpStatus.OK);
    }

    @PutMapping("/contacts/{id}")
    public ResponseEntity updateOneContact(@PathVariable("id")Long id,@RequestBody CreateOrUpDateContactFormDto createOrUpDateContactFormDto) throws IllegalOperatorException, ResourceNotFoundException {

       Contact contact= this.contactServices.updateOneContactById(id,createOrUpDateContactFormDto);
        return new ResponseEntity(new ContactDto(contact
        ),HttpStatus.OK);
    }

    @DeleteMapping("/contacts/{id}")
    public ResponseEntity disableOneContact(@PathVariable("id")Long id) throws ResourceNotFoundException {
        this.contactServices.disableOneContactById(id);
        return new ResponseEntity(HttpStatus.OK);

    }



}
