package org.example.view;

import org.example.controller.ControllerUsuario;
import org.example.model.Usuario;

public class TelaLogin extends javax.swing.JFrame {

    public TelaLogin() {
        initComponents();
        setLocationRelativeTo(null);
        mensageERROR.setVisible(false); // Inicializa a mensagem de erro como invisível
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jIconTenis = new javax.swing.JLabel();
        jTextFieldLogin = new javax.swing.JTextField();
        jPassword = new javax.swing.JPasswordField();
        jButtonConfirma = new javax.swing.JButton();
        jButtonCadastro = new javax.swing.JButton();
        mensageERROR = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(25, 105, 158));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 450));

        jIconTenis.setIcon(new javax.swing.ImageIcon("C:\\Users\\Luis\\Desktop\\9322763 (1).png")); // NOI18N

        jTextFieldLogin.setBackground(new java.awt.Color(41, 47, 54));
        jTextFieldLogin.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        jTextFieldLogin.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldLogin.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldLogin.setText("Login");
        jTextFieldLogin.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextFieldLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLoginActionPerformed(evt);
            }
        });

        jPassword.setBackground(new java.awt.Color(41, 47, 54));
        jPassword.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        jPassword.setForeground(new java.awt.Color(255, 255, 255));
        jPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPassword.setText("jPasswordField1");
        jPassword.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPassword.setSelectedTextColor(new java.awt.Color(255, 255, 255));

        jButtonConfirma.setBackground(new java.awt.Color(243, 222, 138));
        jButtonConfirma.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonConfirma.setForeground(new java.awt.Color(41, 47, 54));
        jButtonConfirma.setText("Confirmar");
        jButtonConfirma.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonConfirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmaActionPerformed(evt);
            }
        });

        jButtonCadastro.setBackground(new java.awt.Color(201, 123, 132));
        jButtonCadastro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButtonCadastro.setForeground(new java.awt.Color(41, 47, 54));
        jButtonCadastro.setText("Cadastrar");
        jButtonCadastro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastroActionPerformed(evt);
            }
        });

        mensageERROR.setForeground(new java.awt.Color(255, 0, 0)); // Configura a cor da mensagem de erro para vermelho
        mensageERROR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER); // Alinha a mensagem de erro no centro
        mensageERROR.setText(""); // Inicializa a mensagem de erro como vazia

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(109, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jIconTenis)
                                                .addGap(192, 192, 192))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jButtonConfirma, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                                                                .addComponent(jButtonCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jTextFieldLogin)
                                                        .addComponent(jPassword))
                                                .addGap(97, 97, 97))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(mensageERROR)
                                                .addGap(241, 241, 241))))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jIconTenis)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(mensageERROR)
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonConfirma, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(125, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void jTextFieldLoginActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jButtonConfirmaActionPerformed(java.awt.event.ActionEvent evt) {
        String login = jTextFieldLogin.getText();
        String senha = new String(jPassword.getPassword());

        ControllerUsuario controllerUsuario = new ControllerUsuario();

        if (controllerUsuario.validaUsuario(login, senha)) {
            MainWindow telaPrincipal = new MainWindow();
            telaPrincipal.setVisible(true);
            this.dispose();
        } else {
            mensageERROR.setText("Login ou senha inválidos!");
            mensageERROR.setForeground(new java.awt.Color(255, 0, 0)); // Cor vermelha para erro
            mensageERROR.setVisible(true);
        }
    }

    private void jButtonCadastroActionPerformed(java.awt.event.ActionEvent evt) {
        String login = jTextFieldLogin.getText();
        String senha = new String(jPassword.getPassword());

        ControllerUsuario controllerUsuario = new ControllerUsuario();

        if (!controllerUsuario.validaUsuario(login, senha)) { // Verifica se o login já existe
            if (controllerUsuario.incluirUsuario(login, senha)) { // Se não existe, inclui
                mensageERROR.setText("Usuário cadastrado com sucesso!");
                mensageERROR.setForeground(new java.awt.Color(0, 128, 0)); // Cor verde para sucesso
                mensageERROR.setVisible(true);
            } else {
                mensageERROR.setText("Erro ao cadastrar usuário!");
                mensageERROR.setForeground(new java.awt.Color(255, 0, 0)); // Cor vermelha para erro
                mensageERROR.setVisible(true);
            }
        } else {
            mensageERROR.setText("Usuário já existe!");
            mensageERROR.setForeground(new java.awt.Color(255, 0, 0)); // Cor vermelha para erro
            mensageERROR.setVisible(true);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    private javax.swing.JButton jButtonCadastro;
    private javax.swing.JButton jButtonConfirma;
    private javax.swing.JLabel jIconTenis;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPassword;
    private javax.swing.JTextField jTextFieldLogin;
    private javax.swing.JLabel mensageERROR;
}
