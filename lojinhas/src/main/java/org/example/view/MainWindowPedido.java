/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.example.view;

import org.example.controller.ControllerCliente;
import org.example.controller.ControllerItemPedido;
import org.example.controller.ControllerPedido;
import org.example.controller.ControllerProduto;
import org.example.model.Cliente;
import org.example.model.Pedido;
import org.example.model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Luis
 */
public class MainWindowPedido extends javax.swing.JFrame {

    /**
     * Creates new form MainWindowPedido
     */
    public MainWindowPedido() {
        initComponents();
        atualizarTabelaPedidos();
        atualizarListaClientes();
        atualizarListaProdutos();
        jTableProduto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    int pedidoId = (int) target.getValueAt(row, 2);

                    exibirProdutosDoPedido(pedidoId);
                }
            }
        });
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Código para abrir a janela de Produto
        MainWindowProduto produtoWindow = new MainWindowProduto();
        produtoWindow.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Código para fechar a aplicação
        System.exit(0);
    }
    private Cliente obterClienteSelecionado() {
        int selectedIndex = jList1.getSelectedIndex();
        if (selectedIndex != -1) {
            ControllerCliente controllerCliente = new ControllerCliente();
            List<Cliente> clientes = controllerCliente.listaClientes();
            return clientes.get(selectedIndex);
        }
        return null;
    }

    private void exibirProdutosDoPedido(int pedidoId) {
        ControllerItemPedido controllerItemPedido = new ControllerItemPedido();
        try {
            List<Integer> idsProdutos = controllerItemPedido.buscaItensById(pedidoId);

            StringBuilder message = new StringBuilder("Produtos do Pedido #" + pedidoId + ":\n");

            ControllerProduto controllerProduto = new ControllerProduto();
            for (Integer idProduto : idsProdutos) {
                Produto produto = controllerProduto.buscarProdutoById(idProduto);
                if (produto != null) {
                    message.append(produto.getNome()).append("\n");
                }
            }
            JOptionPane.showMessageDialog(this, message.toString(), "Produtos do Pedido #" + pedidoId, JOptionPane.INFORMATION_MESSAGE);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro ao buscar produtos do pedido", JOptionPane.ERROR_MESSAGE);
        }
    }

    private List<Produto> obterProdutosSelecionados() {
        List<Produto> produtosSelecionados = new ArrayList<>();
        int[] selectedIndices = jList2.getSelectedIndices();
        if (selectedIndices.length > 0) {
            ControllerProduto controllerProduto = new ControllerProduto();
            List<Produto> produtos = controllerProduto.listaProdutos();
            for (int index : selectedIndices) {
                produtosSelecionados.add(produtos.get(index));
            }
        }
        return produtosSelecionados;
    }

    private void cadastrarPedido() {
        Cliente cliente = obterClienteSelecionado();
        List<Produto> produtos = obterProdutosSelecionados();
        if (cliente == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (produtos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione pelo menos um produto.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Pedido novoPedido = new Pedido();
        novoPedido.setClienteId(cliente.getId());
        novoPedido.setProdutos(produtos);
        novoPedido.setData(new Date());

        ControllerPedido controllerPedido = new ControllerPedido();
        try {
            controllerPedido.incluirPedido(novoPedido);
            atualizarTabelaPedidos();
            JOptionPane.showMessageDialog(this, "Pedido cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar pedido: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarTabelaPedidos() {
        ControllerPedido controllerPedido = new ControllerPedido();
        List<Pedido> pedidos = controllerPedido.listaPedidos();
        DefaultTableModel model = (DefaultTableModel) jTableProduto.getModel();
        model.setRowCount(0); // Limpa a tabela
        ControllerCliente controllerCliente = new ControllerCliente();

        for (Pedido pedido : pedidos) {
            Cliente cliente = controllerCliente.buscaClienteById(pedido.getClienteId());

            Object[] row = {
                    cliente.getNome(),
                    "Luis",
                    pedido.getId(),
                    pedido.getData()
            };
            model.addRow(row);
        }
    }

    private void atualizarListaClientes() {
        ControllerCliente controllerCliente = new ControllerCliente();
        List<Cliente> clientes = controllerCliente.listaClientes();
        DefaultListModel<String> model = new DefaultListModel<>();

        for (Cliente cliente : clientes) {
            model.addElement(cliente.getNome());
        }
        jList1.setModel(model);
    }

    private void atualizarListaProdutos() {
        ControllerProduto controllerProduto = new ControllerProduto();
        List<Produto> produtos = controllerProduto.listaProdutos();
        DefaultListModel<String> model = new DefaultListModel<>();

        for (Produto produto : produtos) {
            model.addElement(produto.getNome());
        }
        jList2.setModel(model);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButtonClientes = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 131, 174));
        jPanel1.setPreferredSize(new java.awt.Dimension(1600, 1200));

        jPanelProduto.setBackground(new java.awt.Color(255, 255, 255));

        jTableProduto.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String [] {
                        "Cliente", "Atendente", "Numero Pedido", "Data"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false, true, false
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
                atualizarTabelaPedidos();
            }
        });
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Cliente");

        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Produtos");

        jScrollPane1.setViewportView(jList1);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(200, 500)); // Aumenta o tamanho da lista de clientes

        jScrollPane3.setViewportView(jList2);
        jScrollPane3.setPreferredSize(new java.awt.Dimension(200, 300)); // Aumenta o tamanho da lista de produtos

        javax.swing.GroupLayout jPanelProdutoLayout = new javax.swing.GroupLayout(jPanelProduto);
        jPanelProduto.setLayout(jPanelProdutoLayout);
        jPanelProdutoLayout.setHorizontalGroup(
                jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelProdutoLayout.createSequentialGroup()
                                .addGap(154, 154, 154)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelProdutoLayout.createSequentialGroup()
                                                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanelProdutoLayout.createSequentialGroup()
                                                                .addGap(91, 91, 91)
                                                                .addComponent(jLabel2)
                                                                .addGap(11, 11, 11))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProdutoLayout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel3)))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanelProdutoLayout.createSequentialGroup()
                                                .addGap(175, 175, 175)
                                                .addComponent(jButtonCadastroProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(852, Short.MAX_VALUE))
        );
        jPanelProdutoLayout.setVerticalGroup(
                jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelProdutoLayout.createSequentialGroup()
                                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelProdutoLayout.createSequentialGroup()
                                                .addGap(80, 80, 80)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanelProdutoLayout.createSequentialGroup()
                                                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanelProdutoLayout.createSequentialGroup()
                                                                .addGap(140, 140, 140)
                                                                .addComponent(jLabel2)
                                                                .addGap(49, 49, 49))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProdutoLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(35, 35, 35)))
                                                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanelProdutoLayout.createSequentialGroup()
                                                                .addGap(55, 55, 55)
                                                                .addComponent(jLabel3)))
                                                .addGap(42, 42, 42)
                                                .addComponent(jButtonCadastroProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(165, Short.MAX_VALUE))
        );

        jButton1.setBackground(new java.awt.Color(41, 47, 54));
        jButton1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Produtos");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton2.setBackground(new java.awt.Color(41, 47, 54));
        jButton2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Sair");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButtonClientes.setBackground(new java.awt.Color(41, 47, 54));
        jButtonClientes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButtonClientes.setForeground(new java.awt.Color(255, 255, 255));
        jButtonClientes.setText("Clientes");
        jButtonClientes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButtonClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClientesActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(41, 47, 54));
        jButton4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Pedido");
        jButton4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

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
                                                                .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addGap(39, 39, 39)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1974, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClientesActionPerformed
        // Código para abrir a janela de Produto
        MainWindow produtoWindow = new MainWindow();
        produtoWindow.setVisible(true);
    }//GEN-LAST:event_jButtonClientesActionPerformed
    private void jButtonCadastroProdutoActionPerformed(java.awt.event.ActionEvent evt) {
        cadastrarPedido();
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
            java.util.logging.Logger.getLogger(MainWindowPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindowPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindowPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindowPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainWindowPedido frame = new MainWindowPedido();
                frame.setSize(1200, 800);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonCadastroProduto;
    private javax.swing.JButton jButtonClientes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelProduto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableProduto;
    // End of variables declaration//GEN-END:variables
}
