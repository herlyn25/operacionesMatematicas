package com.mycompany.view;

import com.mycompany.controller.OperacionesJpaController;
import com.mycompany.model.*;
import com.mycompany.utilidades.*;
import com.mycompany.controller.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class OperacionesForm extends javax.swing.JFrame {
    RegistroJpaController registroOperaciones=new RegistroJpaController(ConnectionHelper.getEMF());
    TipoRegistroJpaController ojc=new TipoRegistroJpaController(ConnectionHelper.getEMF());
    SistemaJpaController sjc=new SistemaJpaController(ConnectionHelper.getEMF());
    OperacionesJpaController  rOperaciones=new OperacionesJpaController(ConnectionHelper.getEMF());
    UsuarioJpaController ujc=new UsuarioJpaController(ConnectionHelper.getEMF());        
    SistemasController sc=new SistemasController();
    Operaciones ope=new Operaciones();
    Registro registro=new Registro();
    DefaultComboBoxModel modelito = new DefaultComboBoxModel();
    DefaultComboBoxModel modelito2 = new DefaultComboBoxModel();  
    
    public OperacionesForm() {       
        initComponents();
        this.setLocationRelativeTo(null);
        List <TipoRegistro> operacion= ojc.findTipoRegistroEntities();
        List <Sistema> sistema= sjc.findSistemaEntities(); 
        modelito.addElement("SELECCIONAR");
        String item="";
        for(int i=0;i<operacion.size();i++){
            item=operacion.get(i).getDescripcion();
            if("INICIO SESION".equals(item)){                
            }else{
               modelito.addElement(item); 
            }          
        }
        for(Sistema system:sistema){
            modelito2.addElement(system.getDescSistema());
        }      
        cmb_operaciones.setModel(modelito);
        cmb_sistemas.setModel(modelito2);
        cmb_operaciones.setSelectedItem(modelito.getElementAt(0));
        cmb_sistemas.setSelectedItem(modelito2.getElementAt(0));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmb_operaciones = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmb_sistemas = new javax.swing.JComboBox<>();
        lbl_num1 = new javax.swing.JLabel();
        txt_numero1 = new javax.swing.JTextField();
        lbl_signo = new javax.swing.JLabel();
        lbl_num2 = new javax.swing.JLabel();
        txt_numero2 = new javax.swing.JTextField();
        lbl_result = new javax.swing.JLabel();
        txt_resultado = new javax.swing.JTextField();
        btn_cancelar = new javax.swing.JButton();
        btn_calcular = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Aplicativo de Operaciones Aritmeticas ");
        setBackground(new java.awt.Color(255, 255, 0));
        setForeground(new java.awt.Color(255, 255, 0));
        setResizable(false);

        cmb_operaciones.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmb_operaciones.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_operacionesItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setText("Operacion");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setText("Sistema");

        cmb_sistemas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmb_sistemas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_sistemasItemStateChanged(evt);
            }
        });

        lbl_num1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lbl_num1.setText("Numero 1");

        txt_numero1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_numero1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_numero1KeyTyped(evt);
            }
        });

        lbl_signo.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbl_signo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_signo.setText("?");

        lbl_num2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lbl_num2.setText("Numero 2");

        txt_numero2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_numero2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_numero2KeyTyped(evt);
            }
        });

        lbl_result.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lbl_result.setText("Resultado");

        txt_resultado.setEditable(false);
        txt_resultado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btn_cancelar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_cancelar.setText("Cancelar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        btn_calcular.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_calcular.setText("Calcular");
        btn_calcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_calcularActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_operaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_sistemas, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(btn_cancelar)
                        .addGap(28, 28, 28)
                        .addComponent(btn_calcular))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_num2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_result, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_resultado, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_numero2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addComponent(lbl_signo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_num1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_numero1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_operaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(cmb_sistemas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_num1)
                    .addComponent(txt_numero1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_signo, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_num2)
                            .addComponent(txt_numero2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_result)
                            .addComponent(txt_resultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cancelar)
                    .addComponent(btn_calcular))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_calcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_calcularActionPerformed
        String operation=cmb_operaciones.getSelectedItem().toString();
        String miSistema=cmb_sistemas.getSelectedItem().toString();
        String x=txt_numero1.getText();
        String y=txt_numero2.getText();
        List<Usuario> lista=null;
        Usuario user=new Usuario();
        String nickname=PantallaPrincipal.lbl_nick.getText();
        lista=ujc.validarLoginByNick(nickname,"");
        Iterator it=lista.iterator();
        while(it.hasNext()){
                    user=(Usuario) it.next();
                    if(user.getNickname().equals(nickname)){
                    registro.setCedulaUsuario(new Usuario(user.getCedula()));    
                }                   
        }        
        long a=0;
        long b=0;
        
        if("SELECCIONAR".equals(miSistema)||"Seleccionar".equals(operation) || "".equals(x) || "".equals(y)){
            JOptionPane.showMessageDialog(null,"Llene los campos por favor");
        }else{                
            
                if("DECIMAL".equals(miSistema)){
                    a=Long.parseLong(x);
                    b=Long.parseLong(y);
                    txt_resultado.setText(sc.validarSistemaDecimal(operation, miSistema, a, b));
                }else if("BINARIO".equals(miSistema)){
                    a=Long.parseLong(x,2);
                    b=Long.parseLong(y,2);
                    txt_resultado.setText(sc.validarSistemaBinario(operation, miSistema, a, b));
                }else if("HEXADECIMAL".equals(miSistema)){
                    a=Long.parseLong(x,16);
                    b=Long.parseLong(y,16);
                    txt_resultado.setText(sc.validarSistemaHexadecimal(operation, miSistema, a, b));
                }
               //rOperaciones.setIdRegistroOperaciones("1");
               int idSistema=cmb_sistemas.getSelectedIndex();
               int operacion=cmb_operaciones.getSelectedIndex();
               String valor1=txt_numero1.getText();
               String valor2=txt_numero2.getText();
               String result=txt_resultado.getText();
               ope.setIdSistema(new Sistema(idSistema));
               ope.setIdTipoOperacion(new TipoRegistro(operacion));
               ope.setCedulaId(user);
               ope.setOperador1(valor1);
               ope.setOperador2(valor2);
               ope.setResultado(result);
               try{
               registro.setCedulaUsuario(user);
               registro.setIdTipoRegistro(new TipoRegistro(operacion));               
               registro.setFecha(PantallaPrincipal.lbl_fecha.getText());
               registro.setHora(PantallaPrincipal.lbl_reloj.getText());
               registroOperaciones.create(registro);
               JOptionPane.showMessageDialog(null, "Registro Auditoria Exitoso");
               }catch(Exception e){
                   JOptionPane.showMessageDialog(null, "Son estos datos los del error");
               }
            try {
                rOperaciones.create(ope);
                JOptionPane.showMessageDialog(null, "Operacion registrada con Exitoso");
                
            } catch (Exception ex) {
                Logger.getLogger(OperacionesForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_calcularActionPerformed

    private void txt_numero1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_numero1KeyTyped
        String system=cmb_sistemas.getSelectedItem().toString();
        String operation=cmb_operaciones.getSelectedItem().toString();
        char caracter = evt.getKeyChar();
        
        if("SELECCIONAR".equals(system)||"Seleccionar".equals(operation)){
            JOptionPane.showMessageDialog(null, "Slecciona un sistema o una operación");
        }    
        if("DECIMAL".equals(system)){
           if(((caracter < '0') || (caracter > '9')) && (caracter != '\b')){
                evt.consume();  // ignorar el evento de teclado
            }
       }
       if("BINARIO".equals(system)){
           if(((caracter < '0') || (caracter > '1')) && (caracter != '\b')){
                evt.consume();  // ignorar el evento de teclado
            }
       }
       if("HEXADECIMAL".equals(system)){
            byte[] byt;
            byt = (""+caracter).getBytes(StandardCharsets.US_ASCII);
            if(((caracter < '0') || (caracter > '9')&&(byt[0]<97 || byt[0]>103)&&(byt[0]<65 || byt[0]>70))&& (caracter != '\b')){
               evt.consume();
           }           
       }      
    }//GEN-LAST:event_txt_numero1KeyTyped

    private void txt_numero2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_numero2KeyTyped
        String system=cmb_sistemas.getSelectedItem().toString();
        String operation=cmb_operaciones.getSelectedItem().toString();
        char caracter = evt.getKeyChar();
        
        if("SELECCIONAR".equals(system)||"Seleccionar".equals(operation)){
            JOptionPane.showMessageDialog(null, "Slecciona un sistema o una operación");
        }       
        if("DECIMAL".equals(system)){
           if(((caracter < '0') || (caracter > '9')) && (caracter != '\b')){
                evt.consume();  // ignorar el evento de teclado
            }
        }
       if("BINARIO".equals(system)){
           if(((caracter < '0') || (caracter > '1')) && (caracter != '\b')){
                evt.consume();  // ignorar el evento de teclado
            }
       }
       if("HEXADECIMAL".equals(system)){
            byte[] byt;
            byt = (""+caracter).getBytes(StandardCharsets.US_ASCII);
            if(((caracter < '0') || (caracter > '9')&&(byt[0]<97 || byt[0]>103)&&(byt[0]<65 || byt[0]>70))&& (caracter != '\b')){
               evt.consume();
           }           
       }
    }//GEN-LAST:event_txt_numero2KeyTyped

    private void cmb_operacionesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_operacionesItemStateChanged
        UsuarioJpaController ujc=new UsuarioJpaController(ConnectionHelper.getEMF());
        PartesOperacionesJpaController poc=new PartesOperacionesJpaController(ConnectionHelper.getEMF());
        PartesOperaciones pope=new PartesOperaciones();        
        int id=cmb_operaciones.getSelectedIndex();
        pope=poc.findPartesOperaciones(id);
        lbl_signo.setText(pope.getSigno());
        lbl_num1.setText(pope.getOperador1()+":");
        lbl_num2.setText(pope.getOperador2()+":");
        lbl_result.setText(pope.getResultado()+":");  
        
    }//GEN-LAST:event_cmb_operacionesItemStateChanged

    private void cmb_sistemasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_sistemasItemStateChanged
        txt_numero1.setText("");
        txt_numero2.setText("");
        txt_resultado.setText("");
    }//GEN-LAST:event_cmb_sistemasItemStateChanged

    public static void main(String[] args) {
        new OperacionesForm().setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_calcular;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JComboBox<String> cmb_operaciones;
    private javax.swing.JComboBox<String> cmb_sistemas;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lbl_num1;
    private javax.swing.JLabel lbl_num2;
    private javax.swing.JLabel lbl_result;
    private javax.swing.JLabel lbl_signo;
    private javax.swing.JTextField txt_numero1;
    private javax.swing.JTextField txt_numero2;
    private javax.swing.JTextField txt_resultado;
    // End of variables declaration//GEN-END:variables
}
