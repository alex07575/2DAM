/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.ImageIcon;

public class Login extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Login.class.getName());

    public Login() {
        initComponents();
        configurarFuentes();
        ImageIcon imagen = new ImageIcon(getClass().getResource("/img/solo_logo_GP.png"));
        Image imagenEscalada = imagen.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        ImageIcon mando = new ImageIcon(getClass().getResource("/img/imagen_lateral_original.png"));
        Image imagenEscalada2 = mando.getImage().getScaledInstance(360, 360, Image.SCALE_SMOOTH);
        labelMandoFoto.setIcon(new ImageIcon(imagenEscalada2));
        labelLogo.setIcon(new ImageIcon(imagenEscalada));
    }

    private void configurarFuentes() {
        Font fuente = cargarFuente();
        labelGamers.setFont(fuente.deriveFont(Font.BOLD, 44f));
        labelParadise.setFont(fuente.deriveFont(Font.BOLD, 44f));
        labelInicSesion.setFont(fuente.deriveFont(Font.BOLD, 20f));
        BotonInicSesion.setFont(fuente.deriveFont(Font.BOLD, 12f));
        labelUsuario.setFont(fuente.deriveFont(Font.BOLD, 14f));
        labelContraseña.setFont(fuente.deriveFont(Font.BOLD, 14f));
        CampoContraseña.setFont(fuente.deriveFont(Font.PLAIN, 12f));
        CampoUsuario.setFont(fuente.deriveFont(Font.PLAIN, 12f));
        labelExit.setFont(fuente.deriveFont(Font.BOLD, 26f));
    }

    private Font cargarFuente() {
        try (InputStream archivo = getClass().getResourceAsStream("/fonts/Roboto-Regular.ttf")) {
            if (archivo != null) {
                return Font.createFont(Font.TRUETYPE_FONT, archivo);
            }
        } catch (FontFormatException | IOException e) {
            logger.warning("Error al cargar la fuente");
        }
        return new Font("Arial", Font.PLAIN, 12);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelMain = new javax.swing.JPanel();
        labelInicSesion = new javax.swing.JLabel();
        CampoUsuario = new javax.swing.JTextField();
        labelUsuario = new javax.swing.JLabel();
        labelContraseña = new javax.swing.JLabel();
        CampoContraseña = new javax.swing.JPasswordField();
        labelLogo = new javax.swing.JLabel();
        BotonInicSesion = new javax.swing.JLabel();
        PanelSuperior = new javax.swing.JPanel();
        labelExit = new javax.swing.JLabel();
        PanelTotal = new javax.swing.JPanel();
        PanelDerecho = new javax.swing.JPanel();
        labelGamers = new javax.swing.JLabel();
        labelMandoFoto = new javax.swing.JLabel();
        labelParadise = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GamerParadise");
        setBackground(new java.awt.Color(0, 0, 0));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(800, 500));

        PanelMain.setBackground(new java.awt.Color(0, 0, 0));
        PanelMain.setPreferredSize(new java.awt.Dimension(420, 459));

        labelInicSesion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelInicSesion.setForeground(new java.awt.Color(255, 51, 102));
        labelInicSesion.setText("INICIAR SESIÓN");

        CampoUsuario.setBackground(new java.awt.Color(255, 204, 255));
        CampoUsuario.setForeground(new java.awt.Color(102, 102, 102));
        CampoUsuario.setText("  Ingrese su nombre de usuario");
        CampoUsuario.setBorder(null);
        CampoUsuario.setCaretPosition(8);
        CampoUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                CampoUsuarioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                CampoUsuarioFocusLost(evt);
            }
        });
        CampoUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CampoUsuarioKeyReleased(evt);
            }
        });

        labelUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelUsuario.setForeground(new java.awt.Color(255, 51, 102));
        labelUsuario.setText("USUARIO");

        labelContraseña.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelContraseña.setForeground(new java.awt.Color(255, 51, 102));
        labelContraseña.setText("CONTRASEÑA");

        CampoContraseña.setBackground(new java.awt.Color(255, 204, 255));
        CampoContraseña.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CampoContraseña.setForeground(new java.awt.Color(102, 102, 102));
        CampoContraseña.setText("********");
        CampoContraseña.setBorder(null);
        CampoContraseña.setMargin(new java.awt.Insets(4, 10, 4, 10));
        CampoContraseña.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                CampoContraseñaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                CampoContraseñaFocusLost(evt);
            }
        });
        CampoContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CampoContraseñaKeyReleased(evt);
            }
        });

        labelLogo.setBackground(new java.awt.Color(0, 0, 0));
        labelLogo.setText("jLabel2");
        labelLogo.setPreferredSize(new java.awt.Dimension(183, 166));

        BotonInicSesion.setBackground(new java.awt.Color(153, 153, 153));
        BotonInicSesion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BotonInicSesion.setForeground(new java.awt.Color(255, 255, 255));
        BotonInicSesion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BotonInicSesion.setText("INICIAR SESIÓN");
        BotonInicSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonInicSesion.setEnabled(false);
        BotonInicSesion.setOpaque(true);
        BotonInicSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotonInicSesionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BotonInicSesionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BotonInicSesionMouseExited(evt);
            }
        });

        javax.swing.GroupLayout PanelMainLayout = new javax.swing.GroupLayout(PanelMain);
        PanelMain.setLayout(PanelMainLayout);
        PanelMainLayout.setHorizontalGroup(
            PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMainLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BotonInicSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelContraseña)
                    .addComponent(labelUsuario)
                    .addComponent(labelInicSesion)
                    .addComponent(CampoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                    .addComponent(labelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CampoContraseña, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(223, Short.MAX_VALUE))
        );
        PanelMainLayout.setVerticalGroup(
            PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMainLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(labelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(labelInicSesion)
                .addGap(18, 18, 18)
                .addComponent(labelUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CampoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelContraseña)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CampoContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(BotonInicSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        getContentPane().add(PanelMain, java.awt.BorderLayout.CENTER);

        PanelSuperior.setBackground(new java.awt.Color(0, 0, 0));
        PanelSuperior.setMaximumSize(new java.awt.Dimension(800, 10));
        PanelSuperior.setMinimumSize(new java.awt.Dimension(100, 10));
        PanelSuperior.setPreferredSize(new java.awt.Dimension(800, 40));
        PanelSuperior.setRequestFocusEnabled(false);

        labelExit.setBackground(new java.awt.Color(0, 0, 0));
        labelExit.setForeground(new java.awt.Color(255, 51, 102));
        labelExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelExit.setText("X");
        labelExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelExit.setOpaque(true);
        labelExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelExitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelExitMouseExited(evt);
            }
        });

        javax.swing.GroupLayout PanelSuperiorLayout = new javax.swing.GroupLayout(PanelSuperior);
        PanelSuperior.setLayout(PanelSuperiorLayout);
        PanelSuperiorLayout.setHorizontalGroup(
            PanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSuperiorLayout.createSequentialGroup()
                .addComponent(labelExit, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 888, Short.MAX_VALUE))
        );
        PanelSuperiorLayout.setVerticalGroup(
            PanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSuperiorLayout.createSequentialGroup()
                .addComponent(labelExit, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        getContentPane().add(PanelSuperior, java.awt.BorderLayout.PAGE_START);

        PanelTotal.setBackground(new java.awt.Color(0, 0, 0));
        PanelTotal.setPreferredSize(new java.awt.Dimension(800, 1));

        javax.swing.GroupLayout PanelTotalLayout = new javax.swing.GroupLayout(PanelTotal);
        PanelTotal.setLayout(PanelTotalLayout);
        PanelTotalLayout.setHorizontalGroup(
            PanelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 927, Short.MAX_VALUE)
        );
        PanelTotalLayout.setVerticalGroup(
            PanelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        getContentPane().add(PanelTotal, java.awt.BorderLayout.PAGE_END);

        PanelDerecho.setBackground(new java.awt.Color(0, 0, 0));
        PanelDerecho.setPreferredSize(new java.awt.Dimension(380, 459));

        labelGamers.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        labelGamers.setForeground(new java.awt.Color(255, 51, 102));
        labelGamers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelGamers.setText("Gamers");

        labelMandoFoto.setText("jLabel1");
        labelMandoFoto.setMaximumSize(new java.awt.Dimension(3700, 1600));
        labelMandoFoto.setName(""); // NOI18N
        labelMandoFoto.setPreferredSize(new java.awt.Dimension(345, 445));

        labelParadise.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        labelParadise.setForeground(new java.awt.Color(255, 51, 102));
        labelParadise.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelParadise.setText("Paradise");

        javax.swing.GroupLayout PanelDerechoLayout = new javax.swing.GroupLayout(PanelDerecho);
        PanelDerecho.setLayout(PanelDerechoLayout);
        PanelDerechoLayout.setHorizontalGroup(
            PanelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDerechoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelGamers, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelParadise, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(PanelDerechoLayout.createSequentialGroup()
                .addComponent(labelMandoFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PanelDerechoLayout.setVerticalGroup(
            PanelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDerechoLayout.createSequentialGroup()
                .addComponent(labelGamers, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelParadise, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelMandoFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        getContentPane().add(PanelDerecho, java.awt.BorderLayout.LINE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CampoUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CampoUsuarioKeyReleased
        boolean usuarioRelleno = !CampoUsuario.getText().trim().isEmpty()
                && !CampoUsuario.getText().equals("  Ingrese su nombre de usuario");
        boolean contraseñaRellena = CampoContraseña.getPassword().length > 0
                && !String.valueOf(CampoContraseña.getPassword()).equals("********");
        if (usuarioRelleno && contraseñaRellena) {
            BotonInicSesion.setEnabled(true);
            BotonInicSesion.setBackground(new java.awt.Color(255, 51, 102));
            BotonInicSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        } else {
            BotonInicSesion.setEnabled(false);
            BotonInicSesion.setBackground(java.awt.Color.GRAY);
            BotonInicSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_CampoUsuarioKeyReleased

    private void CampoContraseñaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CampoContraseñaKeyReleased
        boolean usuarioRelleno = !CampoUsuario.getText().trim().isEmpty()
                && !CampoUsuario.getText().equals("  Ingrese su nombre de usuario");
        boolean contraseñaRellena = CampoContraseña.getPassword().length > 0
                && !String.valueOf(CampoContraseña.getPassword()).equals("********");
        if (usuarioRelleno && contraseñaRellena) {
            BotonInicSesion.setEnabled(true);
            BotonInicSesion.setBackground(new java.awt.Color(255, 51, 102));
            BotonInicSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        } else {
            BotonInicSesion.setEnabled(false);
            BotonInicSesion.setBackground(java.awt.Color.GRAY);
            BotonInicSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_CampoContraseñaKeyReleased

    private void BotonInicSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonInicSesionMouseClicked
        String usuario = CampoUsuario.getText();
        String contraseña = String.valueOf(CampoContraseña.getPassword());
        javax.swing.JOptionPane.showOptionDialog(this,
                "Datos del Login:" + "\nUsuario: " + usuario + "\nContraseña: " + contraseña, "Mensaje",
                javax.swing.JOptionPane.INFORMATION_MESSAGE, javax.swing.JOptionPane.DEFAULT_OPTION,
                null, new Object[]{"Aceptar"}, "Aceptar");
    }//GEN-LAST:event_BotonInicSesionMouseClicked

    private void BotonInicSesionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonInicSesionMouseEntered
        if (BotonInicSesion.isEnabled()) {
            BotonInicSesion.setBackground(java.awt.Color.WHITE);
            BotonInicSesion.setForeground(new java.awt.Color(255, 51, 102));
        }
    }//GEN-LAST:event_BotonInicSesionMouseEntered

    private void BotonInicSesionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonInicSesionMouseExited
        if (BotonInicSesion.isEnabled()) {
            BotonInicSesion.setBackground(new java.awt.Color(255, 51, 102));
            BotonInicSesion.setForeground(java.awt.Color.WHITE);
        }
    }//GEN-LAST:event_BotonInicSesionMouseExited

    private void labelExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_labelExitMouseClicked

    private void labelExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelExitMouseEntered
        labelExit.setBackground(java.awt.Color.WHITE);
    }//GEN-LAST:event_labelExitMouseEntered

    private void labelExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelExitMouseExited
        labelExit.setBackground(java.awt.Color.BLACK);
    }//GEN-LAST:event_labelExitMouseExited

    private void CampoUsuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CampoUsuarioFocusGained
        if (CampoUsuario.getText().equals("  Ingrese su nombre de usuario")) {
            CampoUsuario.setText("");
        }
        CampoUsuario.setForeground(Color.BLACK);
    }//GEN-LAST:event_CampoUsuarioFocusGained

    private void CampoUsuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CampoUsuarioFocusLost
        if (CampoUsuario.getText().trim().isEmpty()) {
            CampoUsuario.setText("  Ingrese su nombre de usuario");
            CampoUsuario.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_CampoUsuarioFocusLost

    private void CampoContraseñaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CampoContraseñaFocusGained
        if (String.valueOf(CampoContraseña.getPassword()).equals("********")) {
            CampoContraseña.setText("");
        }
        CampoContraseña.setForeground(Color.BLACK);
    }//GEN-LAST:event_CampoContraseñaFocusGained

    private void CampoContraseñaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CampoContraseñaFocusLost
        if (CampoContraseña.getPassword().length == 0) {
            CampoContraseña.setText("********");
            CampoContraseña.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_CampoContraseñaFocusLost

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Login().setVisible(true));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BotonInicSesion;
    private javax.swing.JPasswordField CampoContraseña;
    private javax.swing.JTextField CampoUsuario;
    private javax.swing.JPanel PanelDerecho;
    private javax.swing.JPanel PanelMain;
    private javax.swing.JPanel PanelSuperior;
    private javax.swing.JPanel PanelTotal;
    private javax.swing.JLabel labelContraseña;
    private javax.swing.JLabel labelExit;
    private javax.swing.JLabel labelGamers;
    private javax.swing.JLabel labelInicSesion;
    private javax.swing.JLabel labelLogo;
    private javax.swing.JLabel labelMandoFoto;
    private javax.swing.JLabel labelParadise;
    private javax.swing.JLabel labelUsuario;
    // End of variables declaration//GEN-END:variables
}
