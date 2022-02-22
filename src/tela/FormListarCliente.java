/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import controle.ControlePessoa;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Pessoa;

/**
 *
 * @author sala303b
 */
public class FormListarCliente extends javax.swing.JFrame {

    /**
     * Creates new form FormListarCliente
     */
    public FormListarCliente() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblListarPessoa = new javax.swing.JTable();
        btnExcluir = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tblListarPessoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nome Completo", "CPF", "Data Nascimento", "Escolaridade", "Sexo", "E-mail", "Telefone", "Cep", "Logradouro", "Numero", "Bairro", "Cidade", "UF", "Data Cadastro"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblListarPessoa);
        if (tblListarPessoa.getColumnModel().getColumnCount() > 0) {
            tblListarPessoa.getColumnModel().getColumn(0).setPreferredWidth(5);
            tblListarPessoa.getColumnModel().getColumn(5).setPreferredWidth(10);
            tblListarPessoa.getColumnModel().getColumn(13).setPreferredWidth(10);
        }

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(619, Short.MAX_VALUE)
                .addComponent(btnExcluir)
                .addGap(28, 28, 28)
                .addComponent(btnVoltar)
                .addGap(553, 553, 553))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir)
                    .addComponent(btnVoltar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        List<Pessoa> pessoa = ControlePessoa.ListarPaciente();
        DefaultTableModel dtmPessoa = (DefaultTableModel) tblListarPessoa.getModel();

        for (Pessoa p : pessoa) {
            String formato = "dd/MM/yyyy hh:mm:ss";
            DateFormat dateFormat = new SimpleDateFormat(formato);

            String[] dados = {String.valueOf(p.getId()),
                p.getNomeCompleto(),
                p.getCpf(),
                dateFormat.format(p.getDataNascimento()),
                p.getEscolariodade(),
                p.getSexo(),
                p.getEmail(),
                p.getTelefone(),
                p.getCep(),
                p.getLogradouro(),
                p.getNumero(),
                p.getBairro(),
                p.getCidade(),
                p.getUf(),
                dateFormat.format(p.getDataCadastro()),
            };
            dtmPessoa.addRow(dados);
        }
        tblListarPessoa.setModel(dtmPessoa);
    }//GEN-LAST:event_formWindowOpened

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int pac = Integer.parseInt(tblListarPessoa.getValueAt(this.tblListarPessoa.getSelectedRow(), 0).toString());
        Boolean apagou = ControlePessoa.Excluir(pac);

        
        if (apagou == true) { 

          getRecarregarLista();
                    
            JOptionPane.showMessageDialog(null,
                    "Paciente Removido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null,
                    "Houve um erro na remoção do Paciente!");
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void getRecarregarLista(){
        this.limpaTabela();
        List<Pessoa> pessoa = ControlePessoa.ListarPaciente();
        DefaultTableModel dtmPessoa = (DefaultTableModel) tblListarPessoa.getModel();

        for (Pessoa p : pessoa) {
            String formato = "dd/MM/yyyy hh:mm:ss";
            DateFormat dateFormat = new SimpleDateFormat(formato);

            String[] dados = {String.valueOf(p.getId()),
                p.getNomeCompleto(),
                p.getCpf(),
                dateFormat.format(p.getDataNascimento()),
                p.getEscolariodade(),
                p.getSexo(),
                p.getEmail(),
                p.getTelefone(),
                p.getCep(),
                p.getLogradouro(),
                p.getNumero(),
                p.getBairro(),
                p.getCidade(),
                p.getUf(),
                dateFormat.format(p.getDataCadastro()),
            };
            dtmPessoa.addRow(dados);
        }
        tblListarPessoa.setModel(dtmPessoa);
    }
    
    public void limpaTabela() {
        DefaultTableModel tblRemove = (DefaultTableModel) tblListarPessoa.getModel();
        int tamanho = tblRemove.getRowCount();
        if (tamanho > 0) {
            for (int i = tamanho - 1; i >= 0; i--) {
                tblRemove.removeRow(i);
            }
        }
    }
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormListarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormListarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormListarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormListarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormListarCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblListarPessoa;
    // End of variables declaration//GEN-END:variables
}