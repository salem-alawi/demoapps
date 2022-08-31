package com.deraah.demo_api.dto;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrUpDateContactFormDto {

    private String name;

    @NotNull
    private String phone;

}
