package com.example.mystore.services;

import com.example.mystore.models.Person;
import com.example.mystore.repositories.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Person findByLogin(Person person){
        Optional<Person> person_db = personRepository.findByLogin(person.getLogin());
        return person_db.orElse(null);
    }

    @Transactional
    public void register(Person person){
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        personRepository.save(person);
    }

    // Данный метод позволяет получить всех пользователей
    public List<Person> getAllPerson(){
        return personRepository.findAll();
    }

    // Данный метод позволяет получить конкретного пользователя по id
    public Person getPersonById(int id){
        Optional<Person> optionalPerson = personRepository.findById(id);
        return optionalPerson.orElse(null);
    }

    @Transactional
    public void updatePerson(int id, Person person){
        person.setId(id);
        personRepository.save(person);
    }

    // Данный метод позволяет удалить пользовател по id
    @Transactional
    public void deletePerson(int id){
        personRepository.deleteById(id);
    }

    //Данный метод позволяет сменить пароль у пользовател с конкретным id
    @Transactional
    public void changePassword(int id, String password){
        personRepository.updatePersonById(id, passwordEncoder.encode(password));
    }

    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }

}
