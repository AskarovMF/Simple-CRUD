package ru.askarov.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.askarov.springcourse.models.Person;

import java.util.List;

@Component
public class PersonDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        String SQL = "SELECT * FROM person";
        return jdbcTemplate.query(SQL, new PersonMapper());
    }

    public Person show(int id){
        String SQL = "SELECT * FROM person WHERE id = ?";
        return jdbcTemplate.queryForObject(SQL, new BeanPropertyRowMapper<>(Person.class), id);
    }

    public void save(Person person) {
        String SQL = "INSERT INTO person (id, name, age, email) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(SQL, person.getId(), person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person updatePerson) {
        String SQL = "UPDATE person SET name = ?, age = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(SQL, updatePerson.getName(), updatePerson.getAge(), updatePerson.getEmail(), id);
    }

    public void delete(int id) {
        String SQL = "DELETE FROM person WHERE id = ?";
        jdbcTemplate.update(SQL, id);
    }
}
