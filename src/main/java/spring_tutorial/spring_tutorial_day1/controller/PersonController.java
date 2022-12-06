package spring_tutorial.spring_tutorial_day1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spring_tutorial.spring_tutorial_day1.model.Person;
import spring_tutorial.spring_tutorial_day1.service.PersonService;

@Controller
public class PersonController {

    private List<Person> personList = new ArrayList<Person>();

    @Autowired
    PersonService personService;

    @Value("${welcome.message}")
    private String welcomeString;

    @Value("${person.list.header}")
    private String headerMessage;

    @GetMapping(value = { "/", "/home" })
    public String home(Model model) {

        model.addAttribute("message", welcomeString);

        return "home";
    }

    @GetMapping(value = "/testRetrieveAllPerson", produces = "application/json")
    public @ResponseBody List<Person> getAllPersons() {
        personList = personService.getPersons();

        return personList;
    }

    @GetMapping(value ="/list")
    public String personList(Model model) {
        personList = personService.getPersons();
        model.addAttribute("persons",personList);
        model.addAttribute("listofPersons", headerMessage);

        return "personList";
    }

    @GetMapping(value ="/add")
    public String addPerson(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);
        return "addPerson";
    }

    @PostMapping(value="/add")
    public String savePerson(@ModelAttribute(value="person") Person p, Model model) {
        personService.addPerson(p);
        return "redirect:/list";
    }

    @PostMapping(value = "/update")
    public String updatePerson(@ModelAttribute(value="per") Person p, Model model) {
        model.addAttribute("per", p);
        return "personEdit";
    }

    @PostMapping(value ="/updatePerson")
    public String updatePersonRecord(@ModelAttribute(value="person") Person p) {
        personService.updatePerson(p);
        return "redirect:/list";
    }

    @PostMapping(value="/deletePerson")
    public String deletePerson(@ModelAttribute(value="person") Person p) {
        personService.removePerson(p);
        return "redirect:/list";
    }
}
