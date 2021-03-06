/*
 * Copyright (C) 2012 Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.magmax.practica7.aceptance;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.magmax.practica7.pojo.Person;
import static org.junit.Assert.*;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class PersonAgregationSteps extends BaseSteps {

    @Given("added the user $name/$phone/$dni")
    public void givenAddedUser(String name, String phone, String dni) throws Exception {
        Person person = new Person();
        person.setName(name);
        person.setPhone(phone);
        person.setDni(dni);

        persistence.create(person);
    }

    @When("somebody press on agregate button")
    public void whenSomebodyPressOnAgregateButton() {
        this.exception = null;
        try {
            persistence.create(person);
        } catch (Exception ex) {
            this.exception = ex;
        }
    }

    @Then("the person $name/$phone/$dni is added")
    public void thenThePersonMiguelIsAdded(String name, String phone, String dni) throws Exception {
        person = persistence.retrievePerson(dni);
        assertEquals(name, person.getName());
        assertEquals(phone, person.getPhone());
        assertEquals(dni, person.getDni());
    }
}
