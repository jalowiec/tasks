package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CompanyConfig {

    @Value("${company.name}")
    private String companyName;

    @Value("${company.mail}")
    private String companyMail;

    @Value("${company.phone}")
    private String companyPhone;


}
