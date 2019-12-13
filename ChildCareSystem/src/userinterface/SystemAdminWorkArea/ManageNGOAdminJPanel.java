/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.SystemAdminWorkArea;

import Business.EcoSystem;
import Business.Employee.Employee;
import Business.Enterprise.Enterprise;
import Business.Enterprise.NGOEnterprise;
import Business.Network.City;
import Business.Network.Country;
import Business.Network.State;
import Business.Organization.AdminOrganization;
import Business.Organization.Organization;
import Business.Role.AdminRole;
import Business.Role.Role;
import Business.UserAccount.UserAccount;
import java.awt.CardLayout;
import java.awt.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vinod
 */
public class ManageNGOAdminJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ManageNGOAdminJPanel
     */
    private JPanel userProcessContainer;
    EcoSystem system;
    public ManageNGOAdminJPanel(JPanel userProcessContainer, EcoSystem system) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.userProcessContainer = userProcessContainer;
        this.system = system;
        populateCountry();
        populateState();
        populateCity();
        populateNGO();
        populateTable();
    }

     private void populateCountry(){
        
         cbCountry.removeAllItems();
        
        for (Country c : system.getNetworkList()){
            cbCountry.addItem(c);
        }
        
    }
     
    private void populateState(){
        
        cbState.removeAllItems();
        if(cbCountry.getSelectedItem() != null){
           for (Country c : system.getNetworkList()){
            if(c.equals(cbCountry.getSelectedItem())){
                  for(State s: c.getStateList()){
                        cbState.addItem(s);
                    }
            }
                  
        }
        }
       
        
    }
    
    private void populateCity(){
        
         cbCity.removeAllItems();
         
       if (cbState.getSelectedItem() != null){
        for (Country c : system.getNetworkList()){
            for(State s: c.getStateList()){
                if(s.equals(cbState.getSelectedItem())){
                    for(City city :  s.getCityList()){
                    cbCity.addItem(city);
                     }
                }    
            }
        }
       } 
    }
    
     private void populateNGO(){
        
         cbNGO.removeAllItems();
         
        if (cbCity.getSelectedItem() != null){
        for (Country c : system.getNetworkList()){
            for(State s: c.getStateList()){
                for(City city :  s.getCityList()){
                     if(city.equals(cbCity.getSelectedItem())){
                          for(Enterprise e: city.getEnterpriseDirectory().getEnterpriseList()){
                              if(e.getEnterpriseType().getValue().equals("NGO")){
                                cbNGO.addItem(e);
                            }
                        }
                     }
                }
            }
        }
       }  
    }
     
    private void populateTable(){
        DefaultTableModel dtm = (DefaultTableModel) tblNGOAdmin.getModel();
        dtm.setRowCount(0);
        
   
          for(Country c : system.getNetworkList()){
           
            for(State s: c.getStateList()){
                for(City city: s.getCityList()){
                    for(Enterprise e: city.getEnterpriseDirectory().getEnterpriseList()){
                    if(e.getEnterpriseType().getValue().equals("NGO")){
                    for(Organization o: e.getOrganizationDirectory().getOrganizationList()){
                        if(o instanceof  AdminOrganization){
                        for(UserAccount ua: o.getUserAccountDirectory().getUserAccountList()){
                        Object row[] = new Object[5];
                        row[0] = c;
                        row[1] = s;
                        row[2] = city;
                        row[3] = e;
                        row[4] = ua;
                        dtm.addRow(row); 
                    }
                        }
                    }
                    }
                    }
                    
                }
              
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCountry = new javax.swing.JLabel();
        cbCountry = new javax.swing.JComboBox();
        cbNGO = new javax.swing.JComboBox();
        lblCity = new javax.swing.JLabel();
        lblState = new javax.swing.JLabel();
        cbState = new javax.swing.JComboBox();
        lblName = new javax.swing.JLabel();
        cbCity = new javax.swing.JComboBox();
        lblNGO = new javax.swing.JLabel();
        lblUserName = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtFldName = new javax.swing.JTextField();
        txtFldUserName = new javax.swing.JTextField();
        txtFldPassword = new javax.swing.JTextField();
        btnDeleteAdmin = new javax.swing.JButton();
        tblScrollPane = new javax.swing.JScrollPane();
        tblNGOAdmin = new javax.swing.JTable();
        btnSubmit1 = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        lblHeader = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 255));
        setMaximumSize(new java.awt.Dimension(700, 700));
        setMinimumSize(new java.awt.Dimension(700, 700));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCountry.setText("Select Country:");
        add(lblCountry, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 340, -1, 20));

        cbCountry.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCountry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCountryActionPerformed(evt);
            }
        });
        add(cbCountry, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 340, 150, -1));

        cbNGO.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbNGO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNGOActionPerformed(evt);
            }
        });
        add(cbNGO, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 460, 150, -1));

        lblCity.setText("Select City:");
        add(lblCity, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 420, -1, 20));

        lblState.setText("Select State:");
        add(lblState, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 380, -1, 20));

        cbState.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbState.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cbStateFocusGained(evt);
            }
        });
        cbState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbStateActionPerformed(evt);
            }
        });
        add(cbState, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 380, 150, -1));

        lblName.setText("Name:");
        add(lblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 580, -1, -1));

        cbCity.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCityActionPerformed(evt);
            }
        });
        add(cbCity, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 420, 150, -1));

        lblNGO.setText("Select NGO:");
        add(lblNGO, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 460, -1, -1));

        lblUserName.setText("User Name:");
        add(lblUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 500, -1, -1));

        lblPassword.setText("Password:");
        add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 540, -1, -1));
        add(txtFldName, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 580, 150, -1));
        add(txtFldUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 500, 150, -1));
        add(txtFldPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 540, 150, -1));

        btnDeleteAdmin.setText("Delete Admin");
        btnDeleteAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteAdminActionPerformed(evt);
            }
        });
        add(btnDeleteAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 300, -1, -1));

        tblNGOAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Country", "State", "City", "NGO", "UserName"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNGOAdmin.setMaximumSize(new java.awt.Dimension(75, 0));
        tblScrollPane.setViewportView(tblNGOAdmin);

        add(tblScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, -1, 190));

        btnSubmit1.setText("Submit");
        btnSubmit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmit1ActionPerformed(evt);
            }
        });
        add(btnSubmit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 630, -1, -1));

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lblHeader.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeader.setText("Manage NGO Admin");
        add(lblHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 450, 26));
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAdminActionPerformed
        // TODO add your handling code here:
          
        int selectedRow = tblNGOAdmin.getSelectedRow();
        if(selectedRow >= 0){
            int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the Admin?","Warning", JOptionPane.YES_NO_OPTION);
            if(dialogResult == JOptionPane.YES_OPTION){
                NGOEnterprise e = (NGOEnterprise) tblNGOAdmin.getValueAt(selectedRow, 3);
                UserAccount ua = (UserAccount) tblNGOAdmin.getValueAt(selectedRow, 4);
                e.getUserAccountDirectory().deleteUserAccount(ua);
                for(Organization o: e.getOrganizationDirectory().getOrganizationList()){
                if(o instanceof AdminOrganization)
                     o.getUserAccountDirectory().deleteUserAccount(ua);
                }
           
                populateTable();
                JOptionPane.showMessageDialog(null, "NGO admin deleted successfully","Warning", JOptionPane.WARNING_MESSAGE);
                     
            }
        
        }else{
            JOptionPane.showMessageDialog(null, "Please select a row","Warning", JOptionPane.WARNING_MESSAGE);
        }
       
    }//GEN-LAST:event_btnDeleteAdminActionPerformed

    private void cbStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbStateActionPerformed
        // TODO add your handling code here:
             populateCity();
  
    }//GEN-LAST:event_cbStateActionPerformed

    private void cbCountryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCountryActionPerformed
        // TODO add your handling code here:
              populateState();
    }//GEN-LAST:event_cbCountryActionPerformed

    private void cbStateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbStateFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cbStateFocusGained

    private void cbNGOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNGOActionPerformed
        // TODO add your handling code here:
            
    }//GEN-LAST:event_cbNGOActionPerformed

    private void cbCityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCityActionPerformed
        // TODO add your handling code here:
        populateNGO();
    }//GEN-LAST:event_cbCityActionPerformed

    private void btnSubmit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmit1ActionPerformed
        // TODO add your handling code here:
        
            
        try{
          String name = txtFldName.getText();
          String username = txtFldUserName.getText();
          String password = txtFldPassword.getText();
          Country country = (Country) cbCountry.getSelectedItem();
          State state = (State) cbState.getSelectedItem();
          City city = (City) cbCity.getSelectedItem();
          Enterprise enterprise = (Enterprise) cbNGO.getSelectedItem();
        if(!name.equals("") && !username.equals("") && !password.equals("")){
            
                   // validate name
            String regex = "^[a-z A-Z]+$";
            Pattern namePattern = Pattern.compile(regex);
            Matcher nameMatcher = namePattern.matcher(name);
            Matcher unameMatcher = namePattern.matcher(username);

            if(!nameMatcher.matches()){
                 JOptionPane.showMessageDialog(null, "Invalid characters in name", "Warning", JOptionPane.WARNING_MESSAGE);
                 return;
            }
            if(!unameMatcher.matches()){
                 JOptionPane.showMessageDialog(null, "Invalid characters in user name", "Warning", JOptionPane.WARNING_MESSAGE);
                 return;
            }
            
             if(password.length() > 10){
                      JOptionPane.showMessageDialog(null, "Password should not have more than 10 characters", "Warning", JOptionPane.WARNING_MESSAGE);
                     return;
                }else if(password.length() < 4){
                      JOptionPane.showMessageDialog(null, "Password should have 4 to 10 characters", "Warning", JOptionPane.WARNING_MESSAGE);
                     return;
                }
            
            for(Country c: system.getNetworkList()){
               if(c.equals(country)){
                   for(State s: c.getStateList()){
                       if(s.equals(state)){
                          for(City cy: s.getCityList()){
                              if(cy.equals(city)){
                                 for(Enterprise e: cy.getEnterpriseDirectory().getEnterpriseList()){
                                    if(e.equals(enterprise)){
                                    Employee employee = e.getEmployeeDirectory().createEmployee(name);
                                    Role role = new AdminRole();
                                    
                                     if(e.getOrganizationDirectory().getOrganizationList().isEmpty())
                                         e.getOrganizationDirectory().createOrganization(Organization.OrgType.Admin);
                                           
                                        
                                    // check if username is unique
                                    if(system.getUserAccountDirectory().checkIfUsernameIsUnique(username)){
                                       for(Organization o: e.getOrganizationDirectory().getOrganizationList()){
                                         if(o instanceof AdminOrganization){
                                             // dont add admin is already exists
                                        if(!o.getUserAccountDirectory().checkIfAdminExists(o)){
                                            // creating employee in enterprise as well as ecosystem
                                            o.getUserAccountDirectory().createUserAccount(username, password, employee, role);
                                            e.getUserAccountDirectory().createUserAccount(username, password, employee, role);
                                            system.getUserAccountDirectory().createUserAccount(username, password, employee, role);
                                            
                                            // create admin org 
                                            JOptionPane.showMessageDialog(null, "NGO admin added successfully", "Info", JOptionPane.INFORMATION_MESSAGE);
                                            
                                        }else{
                                               JOptionPane.showMessageDialog(null, "NGO admin already exists", "Info", JOptionPane.INFORMATION_MESSAGE);
                                        }
                                         }
                                        }
                                    }else{
                                         JOptionPane.showMessageDialog(null, "User name already exists", "Info", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                    }
                                   
                                 }
                              }
                          }
                       }
                   }   
               }
              
            }
            populateTable();
            txtFldName.setText("");
            txtFldUserName.setText("");
            txtFldPassword.setText("");

        }else{
            JOptionPane.showMessageDialog(null, "Please enter text", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Please enter values for all fields", "Warning", JOptionPane.WARNING_MESSAGE); 
        }
    }//GEN-LAST:event_btnSubmit1ActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
         userProcessContainer.remove(this);
        Component[] componentArray = userProcessContainer.getComponents();
        Component component = componentArray[componentArray.length - 1];
        SystemAdminWorkAreaJPanel sawajp = (SystemAdminWorkAreaJPanel) component;
        sawajp.populateTree();
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_btnBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDeleteAdmin;
    private javax.swing.JButton btnSubmit1;
    private javax.swing.JComboBox cbCity;
    private javax.swing.JComboBox cbCountry;
    private javax.swing.JComboBox cbNGO;
    private javax.swing.JComboBox cbState;
    private javax.swing.JLabel lblCity;
    private javax.swing.JLabel lblCountry;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblNGO;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblState;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JTable tblNGOAdmin;
    private javax.swing.JScrollPane tblScrollPane;
    private javax.swing.JTextField txtFldName;
    private javax.swing.JTextField txtFldPassword;
    private javax.swing.JTextField txtFldUserName;
    // End of variables declaration//GEN-END:variables
}
