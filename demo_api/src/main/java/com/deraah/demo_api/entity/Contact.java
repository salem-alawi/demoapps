package com.deraah.demo_api.entity;


import com.deraah.demo_api.dto.CreateOrUpDateContactFormDto;
import com.deraah.demo_api.exceptions.IllegalOperatorException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "contacts")
@NoArgsConstructor
public class Contact {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;


    @Column(name = "phone")
    private String phone;

    @Column(name = "created_at")
    private LocalDateTime createAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "active")
    private Integer active;


    public Contact(CreateOrUpDateContactFormDto createOrUpDateContactFormDto) {

        this.name= createOrUpDateContactFormDto.getName();
        this.phone= createOrUpDateContactFormDto.getPhone();
        this.active=1;
        LocalDateTime currentTimeOnServer=LocalDateTime.now();
        this.createAt=currentTimeOnServer;
        this.updatedAt=currentTimeOnServer;

    }

    public void update(CreateOrUpDateContactFormDto createOrUpDateContactFormDto) throws IllegalOperatorException {


        if(active==0)
            throw new IllegalOperatorException("CONTACT_CAN_NOT_UPDATE_IT_IS_ALREADY_DELETE");


        this.name= createOrUpDateContactFormDto.getName();
        this.phone=createOrUpDateContactFormDto.getPhone();
        this.updatedAt=LocalDateTime.now();
    }

    public void disable() {

        this.active=0;
        this.updatedAt=LocalDateTime.now();
    }
}
