package com.example.mystore.util;

import com.example.mystore.models.Person;
import com.example.mystore.services.PersonService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PersonService personService;

    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    // В данно методе указываем для какой модели предназначен данный валидатор
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        // Если метод по поиску пользователя по логину не равен 0 тогда такой логин уже занят
        if(personService.findByLogin(person) != null){
            errors.rejectValue("login", "", "Логин занят");
        }
    }

    public void findUser(Object target, Errors errors) {
        Person person = (Person) target;
        // Если метод по поиску пользователя по логину равен 0 тогда такой логин не найден
        if(personService.findByLogin(person) == null){
            errors.rejectValue("login", "", "Пользователь c таким логином не найден");
        }
    }
}
