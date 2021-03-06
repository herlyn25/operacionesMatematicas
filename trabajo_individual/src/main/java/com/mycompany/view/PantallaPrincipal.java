package com.mycompany.view;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.*;

public class PantallaPrincipal extends javax.swing.JFrame implements Runnable {
    String hora,minutos,segundos;
    Calendar calendario;
    Thread h1;
    public PantallaPrincipal() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        initComponents();
        h1=new Thread(this);
        h1.start();
        lbl_fecha.setText(formatter.format(new Date(Calendar.getInstance().getTimeInMillis())));
        lbl_fecha.setForeground(Color.WHITE);
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbl_nick = new javax.swing.JLabel();
        lbl_fecha = new javax.swing.JLabel();
        lbl_reloj = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        item_registrar_usuario = new javax.swing.JMenuItem();
        item_login = new javax.swing.JMenuItem();
        item_logout = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        menu_oper_aritmeticas = new javax.swing.JMenu();
        item_oper_aritmeticas = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registro de Usuarios");

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_nick.setBackground(new java.awt.Color(255, 255, 255));
        lbl_nick.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_nick, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, 90, 20));

        lbl_fecha.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 30, 90, 20));

        lbl_reloj.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_reloj, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 50, 90, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Herly Castillo C\\Documents\\NetBeansProjects\\trabajo_individual\\src\\main\\java\\imagenes\\fondo2.png")); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 480));

        jMenu1.setText("Iniciar Sesion");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        item_registrar_usuario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        item_registrar_usuario.setText("Registrar Usuario");
        item_registrar_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_registrar_usuarioActionPerformed(evt);
            }
        });
        jMenu1.add(item_registrar_usuario);

        item_login.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        item_login.setText("Iniciar Sesion");
        item_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_loginActionPerformed(evt);
            }
        });
        jMenu1.add(item_login);

        item_logout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        item_logout.setText("Cerrar Sesi??n");
        item_logout.setEnabled(false);
        item_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_logoutActionPerformed(evt);
            }
        });
        jMenu1.add(item_logout);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        menu_oper_aritmeticas.setText("Operaciones");

        item_oper_aritmeticas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        item_oper_aritmeticas.setText("Operaciones Aritmeticas");
        item_oper_aritmeticas.setEnabled(false);
        item_oper_aritmeticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_oper_aritmeticasActionPerformed(evt);
            }
        });
        menu_oper_aritmeticas.add(item_oper_aritmeticas);

        jMenuBar1.add(menu_oper_aritmeticas);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void item_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_loginActionPerformed
        LoginForm lf=new LoginForm();
        lf.setVisible(true);
    }//GEN-LAST:event_item_loginActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        LoginForm lf=new LoginForm();        
        lf.setVisible(true);
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void item_oper_aritmeticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_oper_aritmeticasActionPerformed
        OperacionesForm opf=new OperacionesForm();
        opf.setVisible(true);
    }//GEN-LAST:event_item_oper_aritmeticasActionPerformed

    private void item_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_logoutActionPerformed
            PantallaPrincipal.item_oper_aritmeticas.setEnabled(false);
            PantallaPrincipal.item_logout.setEnabled(false);
            PantallaPrincipal.item_login.setEnabled(true);
            PantallaPrincipal.item_oper_aritmeticas.setEnabled(false);
            PantallaPrincipal.lbl_nick.setText("");
    }//GEN-LAST:event_item_logoutActionPerformed

    private void item_registrar_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_registrar_usuarioActionPerformed
        UsuarioForm uf=new UsuarioForm();
        uf.setVisible(true);
    }//GEN-LAST:event_item_registrar_usuarioActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

     @Override
    public void run() {
       Thread ct=Thread.currentThread();
       while(ct==h1){
           calcula();
           lbl_reloj.setText(hora+":"+minutos+":"+segundos);
           lbl_reloj.setForeground(Color.WHITE);
           try{
               Thread.sleep(1000);
           }catch(InterruptedException e){
               
           }
       }
    }

    private void calcula() {
        Calendar calendario=new GregorianCalendar();
        Date fechaActual=new Date();
        calendario.setTime(fechaActual);
        hora=calendario.get(Calendar.HOUR_OF_DAY)+"";
        minutos=calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
        segundos=calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);

    }    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JMenuItem item_login;
    public static javax.swing.JMenuItem item_logout;
    public static javax.swing.JMenuItem item_oper_aritmeticas;
    private javax.swing.JMenuItem item_registrar_usuario;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JLabel lbl_fecha;
    public static javax.swing.JLabel lbl_nick;
    public static javax.swing.JLabel lbl_reloj;
    private javax.swing.JMenu menu_oper_aritmeticas;
    // End of variables declaration//GEN-END:variables

    
}
