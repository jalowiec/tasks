package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;

    public String buildTrelloCardEmail(String message){

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context contextTrelloCardEmail = new Context();
        contextTrelloCardEmail.setVariable("preview_message", "Utworzenie Trello Task");
        contextTrelloCardEmail.setVariable("message", message);
        contextTrelloCardEmail.setVariable("tasks_url", "https://jalowiec.github.io/");
        contextTrelloCardEmail.setVariable("button", "Visit website");
        contextTrelloCardEmail.setVariable("goodbye_message", "W razie pytan skontaktuj sie z nami:");
        contextTrelloCardEmail.setVariable("company_name", companyConfig.getCompanyName());
        contextTrelloCardEmail.setVariable("company_mail", companyConfig.getCompanyMail());
        contextTrelloCardEmail.setVariable("company_phone", companyConfig.getCompanyPhone());
        contextTrelloCardEmail.setVariable("show_button", false);
        contextTrelloCardEmail.setVariable("is_friend", true);
        contextTrelloCardEmail.setVariable("admin_config", adminConfig);
        contextTrelloCardEmail.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", contextTrelloCardEmail);
    }

    public String buildSchedulerEmail(String message){


        Context contextSchedulerEmail = new Context();
        contextSchedulerEmail.setVariable("preview_message", "Utworzenie Trello Task");
        contextSchedulerEmail.setVariable("message", message);
        contextSchedulerEmail.setVariable("tasks_url", "https://jalowiec.github.io/");
        contextSchedulerEmail.setVariable("button", "Visit website");
        contextSchedulerEmail.setVariable("goodbye_message", "W razie pytan skontaktuj sie z nami:");
        contextSchedulerEmail.setVariable("company_name", companyConfig.getCompanyName());
        contextSchedulerEmail.setVariable("company_mail", companyConfig.getCompanyMail());
        contextSchedulerEmail.setVariable("company_phone", companyConfig.getCompanyPhone());
        contextSchedulerEmail.setVariable("show_button", true);
        contextSchedulerEmail.setVariable("is_friend", true);
        contextSchedulerEmail.setVariable("admin_config", adminConfig);
        return templateEngine.process("mail/created-scheduler-mail", contextSchedulerEmail);
    }


}
