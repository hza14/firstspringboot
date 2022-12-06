package spring_tutorial.spring_tutorial_day1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import spring_tutorial.spring_tutorial_day1.model.Person;

@Service
public class PersonService {
    
    private List<Person> persons = new ArrayList<Person>();

    public PersonService() {
        persons.add(new Person("Mark", "Kwan"));
        persons.add(new Person("Darryl", "Eddie"));

    }

    public List<Person> getPersons() {
        return this.persons;
    }

    // Create
    public void addPerson(Person newPerson) {
        persons.add(new Person(newPerson.getFirstName(), newPerson.getLastName()));
    }

    // Delete
    public void removePerson(Person person) {

        Person foundPerson = persons.stream()
            .filter(p -> p.getId().equals(person.getId()))
            .findAny()
            .orElse(null);

        persons.remove(foundPerson);
    }

    // Update
    public void updatePerson(Person person) {

        Person foundPerson = persons.stream()
            .filter(p -> p.getId().equals(person.getId()))
            .findAny()
            .orElse(null);

        Person uPerson = new Person();
        uPerson.setId(person.getId());
        uPerson.setFirstName(person.getFirstName());
        uPerson.setLastName(person.getLastName());

        persons.remove(foundPerson);
        persons.add(uPerson);
    }
}
