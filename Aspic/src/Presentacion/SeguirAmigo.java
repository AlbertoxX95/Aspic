package Presentacion;

import cliente.GestorCliente;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usuario
 */
public class SeguirAmigo extends javax.swing.JFrame {
	private GestorCliente gc;
	private String user;
    /**
     * Creates new form SeguirAmigo
     */
    public SeguirAmigo(GestorCliente g, String user) {
    	this.gc = g;
    	this.user = user;
        initComponents();      
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(630, 537));
        getContentPane().setLayout(null);
        jLabel1.setBounds(0, 0, 630, 490);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfSeguirAmigo = new javax.swing.JTextField();
        btSeguir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 0));
        jLabel2.setText("ASPIC");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(260, 30, 100, 50);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 0));
        jLabel3.setText("Nombre de usuario:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(110, 190, 240, 80);

        
        tfSeguirAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSeguirAmigoActionPerformed(evt);
            }
        });
        getContentPane().add(tfSeguirAmigo);
        tfSeguirAmigo.setBounds(300, 210, 260, 30);

        btSeguir.setText("Seguir");
        btSeguir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSeguirActionPerformed(evt);
            }
        });
        getContentPane().add(btSeguir);
        btSeguir.setBounds(310, 360, 170, 40);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aplicacion/fondo-degradado.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 630, 490);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfSeguirAmigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSeguirAmigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSeguirAmigoActionPerformed

    private void btSeguirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSeguirActionPerformed
        // TODO add your handling code here:
    	if(gc.seguirAmigo(this.user, this.tfSeguirAmigo.getText())) {
    		PrincipalApp prin = new PrincipalApp(this.gc, this.user);
    		prin.setVisible(true);
    		this.dispose();
    	}else {
    		System.out.println("Comprueba que existe o que no sois amigos ya.");
    	}
    	System.out.println(this.user + ", " + this.tfSeguirAmigo.getText());
    	
    }//GEN-LAST:event_btSeguirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SeguirAmigo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SeguirAmigo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SeguirAmigo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SeguirAmigo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSeguir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField tfSeguirAmigo;
    // End of variables declaration//GEN-END:variables
}
