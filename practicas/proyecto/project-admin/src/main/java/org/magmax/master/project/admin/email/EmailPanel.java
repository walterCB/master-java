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
package org.magmax.master.project.admin.email;

import org.magmax.master.project.admin.BaseCrudPanel;

/**
 *
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class EmailPanel extends BaseCrudPanel<Email> {

    public static final String TITLE_CREATION = "Email creation";
    public static final String TITLE_DETAILS = "Email Details";
    public static final String TITLE_UPDATE = "Email User";

    /**
     * Creates new form UserUI
     */
    public EmailPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        nameEntry = new javax.swing.JTextField();

        jLabel1.setText("Nombre:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameEntry, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(nameEntry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField nameEntry;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTitleCreation() {
        return TITLE_CREATION;
    }

    @Override
    public String getTitleUpdate() {
        return TITLE_UPDATE;
    }

    @Override
    public String getTitleDetails() {
        return TITLE_DETAILS;
    }

    @Override
    public Email getCrudObject() {
        Email result = new Email();
        updateCrudObject(result);
        return result;
    }

    @Override
    public void setCrudObject(Email item) {
        nameEntry.setText(item.getAddress());
    }

    @Override
    public void updateCrudObject(Email item) {
        item.setAddress(nameEntry.getText());
    }

    @Override
    public void setWidgetsEnabled(boolean enabled) {
        nameEntry.setEnabled(enabled);
    }

    @Override
    public void clearWidgets() {
        nameEntry.setText("");
    }
}
