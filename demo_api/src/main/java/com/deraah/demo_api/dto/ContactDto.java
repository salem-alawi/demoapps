package com.deraah.demo_api.dto;


import com.deraah.demo_api.entity.Contact;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContactDto {


    private Long id;
    private String name;
    private String phone;

    public ContactDto(Contact contact){
        this.id = contact.getId();
        this.name = contact.getName();
        this.phone =  contact.getPhone();
    }



}
