/*
 * NovoJogoUI.java
 *
 * Created on August 14, 2008, 4:00 AM
 */

package yam.ui;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author  leandro
 */
public class NovoJogoUI extends JDialog {
       
    private String[] jogadores;
    private DefaultListModel lstMdlJogadores;
               
    /** Creates new form NovoJogoUI */
    public NovoJogoUI(Frame frame) {
        super(frame, "Jogadores", true);
        initComponents();
        
        lstMdlJogadores = new DefaultListModel();
        lstJogadores.setModel(lstMdlJogadores);
        setLocationRelativeTo(frame);
        setVisible(true);
    }
    
    public String[] getJogadores(){
        int qtdJogadores = lstMdlJogadores.getSize();
        if (qtdJogadores>0) {
            jogadores = new String[qtdJogadores];
            for (int idx = 0; idx < qtdJogadores; idx++) {
                jogadores[idx] = (String) lstMdlJogadores.get(idx);
            }
            return jogadores;
        }
        return null;
    }
            
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jScrollPane1 = new javax.swing.JScrollPane();
        lstJogadores = new javax.swing.JList();
        btnAdicionar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtJogador = new javax.swing.JTextPane();
        btnMoverParaCima = new javax.swing.JButton();
        btnMoverParaBaixo = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);

        lstJogadores.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, txtJogador, org.jdesktop.beansbinding.ObjectProperty.create(), lstJogadores, org.jdesktop.beansbinding.BeanProperty.create("elements"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(lstJogadores);

        btnAdicionar.setText("+");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(txtJogador);

        btnMoverParaCima.setText("▲");
        btnMoverParaCima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoverParaCimaActionPerformed(evt);
            }
        });

        btnMoverParaBaixo.setText("▼");
        btnMoverParaBaixo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoverParaBaixoActionPerformed(evt);
            }
        });

        btnRemover.setText("-");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnOK, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnMoverParaCima, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnMoverParaBaixo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRemover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(btnAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdicionar))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btnMoverParaCima)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMoverParaBaixo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRemover))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnOK)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnMoverParaBaixoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoverParaBaixoActionPerformed
    int idx = lstJogadores.getSelectedIndex();
    if (idx != -1 & idx < lstMdlJogadores.getSize()-1 ) {
        String tmp = (String) lstMdlJogadores.get(idx+1);
        lstMdlJogadores.set(idx+1, lstMdlJogadores.get(idx));
        lstMdlJogadores.set(idx, tmp);
        lstJogadores.setSelectedIndex(idx+1);
    }
}//GEN-LAST:event_btnMoverParaBaixoActionPerformed

private void btnMoverParaCimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoverParaCimaActionPerformed
    int idx = lstJogadores.getSelectedIndex();
    if (idx != -1 & idx > 0) {
        String tmp = (String) lstMdlJogadores.get(idx-1);
        lstMdlJogadores.set(idx-1, lstMdlJogadores.get(idx));
        lstMdlJogadores.set(idx, tmp);
        lstJogadores.setSelectedIndex(idx-1);
    }
}//GEN-LAST:event_btnMoverParaCimaActionPerformed

private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
    setVisible(false);
}//GEN-LAST:event_btnOKActionPerformed

private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
    String jogador = txtJogador.getText();
    if (!jogador.isEmpty()) {
        lstMdlJogadores.addElement(jogador);
        txtJogador.setText("");
        txtJogador.requestFocus();
    }
}//GEN-LAST:event_btnAdicionarActionPerformed

private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
    int idx = lstJogadores.getSelectedIndex();
    if (idx != -1 ) {
        lstMdlJogadores.remove(lstJogadores.getSelectedIndex());
        if (lstMdlJogadores.size()>0) {
            if (idx==lstMdlJogadores.size()) {
                lstJogadores.setSelectedIndex(idx-1);
            }   
            else {
                lstJogadores.setSelectedIndex(idx);
            }
                
        }
    }
}//GEN-LAST:event_btnRemoverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnMoverParaBaixo;
    private javax.swing.JButton btnMoverParaCima;
    private javax.swing.JButton btnOK;
    private javax.swing.JButton btnRemover;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList lstJogadores;
    private javax.swing.JTextPane txtJogador;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}