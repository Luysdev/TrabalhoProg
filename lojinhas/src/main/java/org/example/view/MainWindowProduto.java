package org.example.view;

import org.example.controller.ControllerProduto;
import org.example.model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class MainWindowProduto extends javax.swing.JFrame {

    /**
     * Creates new form MainWindowProduto
     */
    public MainWindowProduto() {
        initComponents();
        listarProdutos();
        configurarTabela();
    }

    private void listarProdutos() {
        ControllerProduto controllerProduto = new ControllerProduto();
        try {
            List<Produto> produtos = controllerProduto.listaProdutos();
            DefaultTableModel model = (DefaultTableModel) jTableProduto.getModel();
            model.setRowCount(0); // Limpa a tabela antes de adicionar os novos dados
            for (Produto produto : produtos) {
                model.addRow(new Object[]{ produto.getNome(), produto.getDescricao(), produto.getPreco(),produto.getId()});
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void configurarTabela() {
        jTableProduto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
                    int rowIndex = jTableProduto.getSelectedRow();
                    DefaultTableModel model = (DefaultTableModel) jTableProduto.getModel();

                    String nomeAntigo = model.getValueAt(rowIndex, 0).toString();
                    String descricaoAntiga = model.getValueAt(rowIndex, 1).toString();
                    double precoAntigo = Double.parseDouble(model.getValueAt(rowIndex, 2).toString());

                    JPanel panel = new JPanel(new GridLayout(0, 1));
                    JTextField nomeField = new JTextField(nomeAntigo);
                    JTextField descricaoField = new JTextField(descricaoAntiga);
                    JTextField precoField = new JTextField(String.valueOf(precoAntigo));

                    panel.add(new JLabel("Nome:"));
                    panel.add(nomeField);
                    panel.add(new JLabel("Descrição:"));
                    panel.add(descricaoField);
                    panel.add(new JLabel("Preço:"));
                    panel.add(precoField);

                    JButton deleteButton = new JButton("Excluir");
                    panel.add(deleteButton);

                    JDialog dialog = new JDialog();
                    dialog.setContentPane(panel);
                    dialog.pack();
                    dialog.setModal(true);

                    deleteButton.addActionListener(actionEvent -> {
                        try {
                            Produto produto = new Produto();
                            produto.setNome(nomeAntigo);
                            produto.setDescricao(descricaoAntiga);
                            produto.setPreco(precoAntigo);
                            ControllerProduto controllerProduto = new ControllerProduto();
                            int idProduto = controllerProduto.buscarIdProduto(produto);

                            controllerProduto.deletaProduto(idProduto);

                            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                            dialog.dispose();  // Fecha o diálogo de edição/exclusão
                            listarProdutos();
                        } catch (RuntimeException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro ao excluir produto", JOptionPane.ERROR_MESSAGE);
                        }
                    });

                    dialog.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            listarProdutos();
                        }
                    });

                    int option = JOptionPane.showConfirmDialog(null, panel, "Editar Produto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    if (option == JOptionPane.OK_OPTION) {
                        try {
                            String novoNome = nomeField.getText();
                            String novaDescricao = descricaoField.getText();
                            double novoPreco = Double.parseDouble(precoField.getText());

                            Produto produto = new Produto();
                            produto.setNome(nomeAntigo);
                            produto.setDescricao(descricaoAntiga);
                            produto.setPreco(precoAntigo);
                            ControllerProduto controllerProduto = new ControllerProduto();
                            int idProduto = controllerProduto.buscarIdProduto(produto);

                            produto.setId(idProduto);
                            produto.setNome(novoNome);
                            produto.setDescricao(novaDescricao);
                            produto.setPreco(novoPreco);

                            controllerProduto.atualizaPedido(produto);

                            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                            listarProdutos();
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Por favor, insira um valor numérico válido para o preço.", "Erro ao atualizar produto", JOptionPane.ERROR_MESSAGE);
                        } catch (IllegalArgumentException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro ao atualizar produto", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    dialog.setVisible(true);
                }
            }
        });
    }

    private void jProtutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MainWindowPedido pedidoWindow = new MainWindowPedido();
        pedidoWindow.setSize(1200, 800);
        pedidoWindow.setLocationRelativeTo(null);
        pedidoWindow.setVisible(true);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.dispose();
        MainWindow produtoWindow = new MainWindow();
        produtoWindow.setVisible(true);

    }//GEN-LAST:event_jButton4ActionPerformed


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // Código para fechar a aplicação
        System.exit(0);
    }

    private void cadastrarProduto() {
        try {
            String nome = jTextField3.getText();
            String descricao = jTextField1.getText();
            String precoText = jTextField2.getText();

            // Validação dos campos
            if (nome.isEmpty() || descricao.isEmpty() || precoText.isEmpty()) {
                throw new IllegalArgumentException("Por favor, preencha todos os campos.");
            }

            double preco = Double.parseDouble(precoText);

            if (preco <= 0) {
                throw new IllegalArgumentException("O preço deve ser maior que zero.");
            }

            Produto produto = new Produto();
            produto.setNome(nome);
            produto.setDescricao(descricao);
            produto.setPreco(preco);
            ControllerProduto controllerProduto = new ControllerProduto();
            controllerProduto.incluirProduto(produto);
            JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            listarProdutos(); // Atualiza a tabela após o cadastro
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um valor numérico válido para o preço.", "Erro ao cadastrar produto", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro ao cadastrar produto", JOptionPane.ERROR_MESSAGE);
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

        jPanel1 = new javax.swing.JPanel();
        jPanelProduto = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableProduto = new javax.swing.JTable();
        jButtonCadastroProduto = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButtonClientes = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1200, 800));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 131, 174));
        jPanel1.setPreferredSize(new java.awt.Dimension(1600, 1200));

        jPanelProduto.setBackground(new java.awt.Color(255, 255, 255));

        jTableProduto.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null}
                },
                new String [] {
                        "Nome", "Descrição", "Preço"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableProduto);

        jButtonCadastroProduto.setBackground(new java.awt.Color(41, 47, 54));
        jButtonCadastroProduto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButtonCadastroProduto.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCadastroProduto.setText("Cadastro");
        jButtonCadastroProduto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButtonCadastroProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastroProdutoActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Nome");

        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Descrição");

        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Preço");

        javax.swing.GroupLayout jPanelProdutoLayout = new javax.swing.GroupLayout(jPanelProduto);
        jPanelProduto.setLayout(jPanelProdutoLayout);
        jPanelProdutoLayout.setHorizontalGroup(
                jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelProdutoLayout.createSequentialGroup()
                                .addGap(154, 154, 154)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelProdutoLayout.createSequentialGroup()
                                                .addGap(91, 91, 91)
                                                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel4)
                                                        .addComponent(jLabel2))
                                                .addGap(22, 22, 22)
                                                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanelProdutoLayout.createSequentialGroup()
                                                .addGap(178, 178, 178)
                                                .addComponent(jButtonCadastroProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(457, Short.MAX_VALUE))
        );
        jPanelProdutoLayout.setVerticalGroup(
                jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelProdutoLayout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelProdutoLayout.createSequentialGroup()
                                                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(16, 16, 16)
                                                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel3))
                                                .addGap(30, 30, 30)
                                                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel4))
                                                .addGap(36, 36, 36)
                                                .addComponent(jButtonCadastroProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(165, Short.MAX_VALUE))
        );

        jButton1.setBackground(new java.awt.Color(41, 47, 54));
        jButton1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Pedidos");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jProtutoActionPerformed(evt);
            }
        });

        jButtonClientes.setBackground(new java.awt.Color(41, 47, 54));
        jButtonClientes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButtonClientes.setForeground(new java.awt.Color(255, 255, 255));
        jButtonClientes.setText("Clientes");
        jButtonClientes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButtonClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(41, 47, 54));
        jButton4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Sair");
        jButton4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jButtonClientes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(69, 69, 69)))
                                .addComponent(jPanelProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanelProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(jLabel1)
                                .addGap(28, 28, 28)
                                .addComponent(jButtonClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1583, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButtonClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonClientesActionPerformed
    private void jButtonCadastroProdutoActionPerformed(java.awt.event.ActionEvent evt) {
        cadastrarProduto();
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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindowProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindowProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindowProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindowProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                MainWindowProduto mainWindowProduto = new MainWindowProduto();
                mainWindowProduto.setVisible(true);
                mainWindowProduto.setSize(1200, 800); // Define o tamanho da janela
                mainWindowProduto.setVisible(true);
                mainWindowProduto.setLocationRelativeTo(null); // Centraliza a janela
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonCadastroProduto;
    private javax.swing.JButton jButtonClientes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelProduto;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableProduto;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
