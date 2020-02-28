package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dao.PersonDAO;
import com.example.demo.model.Person;

/**
 * 
 * @author Tsiaro
 *
 * Controller main 
 * 
 */

@Controller
public class MainController {
 
    private List<Person> persons = new ArrayList<Person>();
	@Autowired
	private PersonDAO personDAO;
 
    // Injectez (inject) via application.properties.
    @Value("${welcome.message}")
    private String message;
 
    @Value("${error.message}")
    private String errorMessage;
 
    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
 
        model.addAttribute("message", message);
        
        return "index";
    }
 
    @RequestMapping(value = { "/personList" }, method = RequestMethod.GET)
    public String personList(Model model) {
 
    	persons = personDAO.findAll();
        model.addAttribute("persons", persons);
 
        return "personList";
    }
 
    @RequestMapping(value = { "/addPerson" }, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {
 
        Person personForm = new Person();
        model.addAttribute("personForm", personForm);
 
        return "addPerson";
    }
 
    @RequestMapping(value = { "/addPerson" }, method = RequestMethod.POST)
    public String savePerson(Model model, @ModelAttribute("person") Person personAdd) {
 
        String firstName = personAdd.getFirstName();
        String lastName = personAdd.getLastName();
 
        Person personForm = new Person(firstName, lastName);
        
        if (firstName != null && firstName.length() > 0 && lastName != null && lastName.length() > 0) {
            personDAO.save(personForm);
 
            return "redirect:/personList";
        }
 
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("personForm", personForm);
        
        return "addPerson";
    }
 
}
