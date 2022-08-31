package com.deraah.demo_api.service;


import com.deraah.demo_api.dto.ContactDto;
import com.deraah.demo_api.dto.CreateOrUpDateContactFormDto;
import com.deraah.demo_api.entity.Contact;
import com.deraah.demo_api.exceptions.IllegalOperatorException;
import com.deraah.demo_api.exceptions.ResourceNotFoundException;
import com.deraah.demo_api.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContactServices {

    @Autowired
    private ContactRepository contactRepository;


    public Page<Contact> findAll(Pageable pageable, String search) {


        if (search.trim().length() == 0) {
            return this.contactRepository.findAllActive(pageable);
        } else {
            return this.contactRepository.findAllWithSearch(pageable, "%" + search + "%");
        }

    }

    public Contact createNewContact(CreateOrUpDateContactFormDto createOrUpDateContactFormDto) {
        // TODO there is not any validation on duplicated contact phone number
        Contact contact = new Contact(createOrUpDateContactFormDto);

        return this.contactRepository.save(contact);
    }

    public Contact findOneById(Long id) throws ResourceNotFoundException {

        return this.contactRepository.findOneByIdActive(id).orElseThrow(() -> new ResourceNotFoundException("CONTACT_NOT_FOUND"));
    }

    public Contact updateOneContactById(Long id, CreateOrUpDateContactFormDto createOrUpDateContactFormDto) throws ResourceNotFoundException, IllegalOperatorException {

        Contact contact = this.findOneById(id);
        contact.update(createOrUpDateContactFormDto);
     return    this.contactRepository.save(contact);
    }

    public void disableOneContactById(Long id) throws ResourceNotFoundException {
        Contact contact = this.findOneById(id);
        contact.disable();
        this.contactRepository.save(contact);

    }
}
