/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.GuestWorkArea;

import Business.DB4OUtil.DB4OUtil;
import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Job.Job;
import Business.Network.City;
import Business.Network.Country;
import Business.Network.State;
import Business.Organization.AdminOrganization;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.JobWorkRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import java.util.ConcurrentModificationException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rugved
 */
public class ApplyForJobJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private EcoSystem system;
    private UserAccount userAccount;
    private DB4OUtil dB4OUtil;
    private String user;

    /**
     * Creates new form ApplyForJobJPanel
     */
    public ApplyForJobJPanel(JPanel userProcessContainer, EcoSystem system, DB4OUtil dB4OUtil, UserAccount userAccount, String user) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.system = system;
        this.dB4OUtil = dB4OUtil;
        this.userAccount = userAccount;
        this.user = user;
        //this.enterprise = enterprise;
        populateTable();
    }

    private void populateTable() {
        DefaultTableModel model = (DefaultTableModel) tblJobs.getModel();

        model.setRowCount(0);

        if (system.getJobDirectory().getJobList() != null) {
            for (Job job : system.getJobDirectory().getJobList()) {
                Object[] row = new Object[5];
                row[3] = job.getEnterprise();
                row[4] = job;

                for (Country c : system.getNetworkList()) {
                    for (State s : c.getStateList()) {
                        for (City city : s.getCityList()) {
                            for (Enterprise e : city.getEnterpriseDirectory().getEnterpriseList()) {
                                if (e.equals(job.getEnterprise())) {
                                    row[0] = c;
                                    row[1] = s;
                                    row[2] = city;
                                }
                            }
                        }
                    }
                }
                model.addRow(row);
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

        lblHeader = new javax.swing.JLabel();
        tblScrollPane = new javax.swing.JScrollPane();
        tblJobs = new javax.swing.JTable();
        btnApply = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 255));
        setMaximumSize(new java.awt.Dimension(700, 700));
        setMinimumSize(new java.awt.Dimension(700, 700));
        setPreferredSize(new java.awt.Dimension(700, 700));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHeader.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeader.setText("Apply For Job");
        add(lblHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 450, 26));

        tblJobs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Country", "State", "City", "NGO", "Job Title"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblJobs.setMinimumSize(new java.awt.Dimension(375, 0));
        tblJobs.getTableHeader().setReorderingAllowed(false);
        tblScrollPane.setViewportView(tblJobs);
        if (tblJobs.getColumnModel().getColumnCount() > 0) {
            tblJobs.getColumnModel().getColumn(0).setResizable(false);
            tblJobs.getColumnModel().getColumn(1).setResizable(false);
            tblJobs.getColumnModel().getColumn(2).setResizable(false);
            tblJobs.getColumnModel().getColumn(3).setResizable(false);
            tblJobs.getColumnModel().getColumn(4).setResizable(false);
        }

        add(tblScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, -1, 178));

        btnApply.setText("Apply");
        btnApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyActionPerformed(evt);
            }
        });
        add(btnApply, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 240, -1, -1));

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyActionPerformed
        // TODO add your handling code here:

        int selectedRow = tblJobs.getSelectedRow();

        if (selectedRow >= 0) {
            Job job = (Job) tblJobs.getValueAt(selectedRow, 4);
            Enterprise enterprise = (Enterprise) tblJobs.getValueAt(selectedRow, 3);

            if (user.equals("G")) {
                SignUpJPanel sujp = new SignUpJPanel(userProcessContainer, system, dB4OUtil, enterprise, job);
                userProcessContainer.add("SignUpJPanel", sujp);
                CardLayout layout = (CardLayout) userProcessContainer.getLayout();
                layout.next(userProcessContainer);
            } else {
                try{
                for (WorkRequest wr : userAccount.getWorkQueue().getWorkRequestList()) {
                    if (wr instanceof JobWorkRequest) {
                        if (((JobWorkRequest) wr).getJob().equals(job)) {
                            if (wr.getSender().equals(userAccount)) {
                                JOptionPane.showMessageDialog(this, "You have already applied for this job", "Information", JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to apply for the job?", "Information", JOptionPane.YES_NO_OPTION);
                            if (dialogResult == JOptionPane.YES_OPTION) {
                                sendWorkRequest(userAccount, job, enterprise);
                            }

                        }
                    }

                }

            }catch(ConcurrentModificationException ex){
                
            }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to continue", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnApplyActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_btnBackActionPerformed

    public void sendWorkRequest(UserAccount userAccount, Job job, Enterprise enterprise) {

        JobWorkRequest request = new JobWorkRequest();

        request.setSender(userAccount);
        request.setJob(job);
        request.setStatus("Sent");

        Organization org = null;
        for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
            if (organization instanceof AdminOrganization) {
                org = organization;
//                for(UserAccount ua: enterprise.getUserAccountDirectory().getUserAccountList()){
//                    if(ua.getRole().getName().equals("Admin Type")){
//                         request.setReceiver(ua);
//                         ua.getWorkQueue().getWorkRequestList().add(request);
//                }
//                }

                break;
            }
        }
        if (org != null) {
            org.getWorkQueue().getWorkRequestList().add(request);
            userAccount.getWorkQueue().getWorkRequestList().add(request);
        }

        JOptionPane.showMessageDialog(null, "Job applied Successfully", "Information", JOptionPane.INFORMATION_MESSAGE);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JTable tblJobs;
    private javax.swing.JScrollPane tblScrollPane;
    // End of variables declaration//GEN-END:variables
}
