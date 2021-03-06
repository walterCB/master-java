/*
 * Copyright (C) 2012 Miguel Angel Garcia<miguelangel.garcia@gmail.com>
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
import org.magmax.master.project.persistence.pojo.Product;
import org.magmax.master.project.persistence.pojo.SoldProduct;

/**
 *
 * @author Miguel Angel Garcia<miguelangel.garcia@gmail.com>
 */
public class SoldProductDAO extends GenericDAO<SoldProduct, Integer> {

    public SoldProductDAO(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void store(SoldProduct soldproduct) {
        Product product = soldproduct.getProduct();
        if (product != null) {
            ProductDAO productdao = new ProductDAO(getEntityManager());
            productdao.storeAndRefresh(product);
        }
        super.store(soldproduct);
    }
}
