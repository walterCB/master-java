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
package org.magmax.master.project.persistence.dao;

import javax.persistence.EntityManager;
import org.magmax.master.project.persistence.pojo.Email;
import org.magmax.master.project.persistence.pojo.User;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class EmailDAO extends GenericDAO<Email, Integer> {

    public EmailDAO(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void store(Email email) {
        UserDAO userdao = new UserDAO(getEntityManager());
        User user = email.getUser();
        if (user != null) {
            user.addEmail(email);
            userdao.store(user);
            userdao.refresh(user);
        }
        super.store(email);
    }
}
