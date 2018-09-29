/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ruleta;

import com.panamahitek.PanamaHitek_Arduino;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsDevice.WindowTranslucency;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.io.File;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JWindow;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaListPlayerComponent;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

/**
 *
 * @author VBarrera
 */
public class NewApplication extends JFrame {

    /**
     * Creates new form NewApplication
     */
    private EmbeddedMediaPlayerComponent playerLan;
    private EmbeddedMediaPlayerComponent playerAleatorio;
    private File file;
    PanamaHitek_Arduino puertoCom = new PanamaHitek_Arduino();
    SerialPortEventListener event = new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent spe) {
            if (puertoCom.isMessageAvailable()) {
                String dato = puertoCom.printMessage();
                System.out.println(dato);
                if (dato.equals("onlucky")) {
                    lucky();
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(NewApplication.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }
    };

    static {
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:\\Program Files (x86)\\VideoLAN\\VLC\\");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
    }
    //bandera para controlar la reproduccion de video y el cambio en el avance de video
    private boolean band = true;

    final boolean perPixelTranslucencySupported = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().isWindowTranslucencySupported(WindowTranslucency.PERPIXEL_TRANSLUCENT);

    public void attach(LibVlc libvlc, MediaPlayer mediaPlayer, long componentId) {
        // Logger.debug("attach(componentId={})", componentId);
        libvlc.libvlc_media_player_set_xwindow(mediaPlayer.mediaPlayerInstance(), (int) componentId);
    }

    public NewApplication() {
        initComponents();
        setAlwaysOnTop(true);
        puertoCom.showMessageDialogs(false);
        try {
            puertoCom.arduinoRX("COM2", 9600, event);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "No se ha encontrado ningún Boton conectado en el puerto COM3. Verifique el puerto en el que está conectado");
//Logger.getLogger(NewApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        jButtonRandom.setVisible(false);
        setLocationRelativeTo(null);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        gs[0].setFullScreenWindow(this);
        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        setBounds(getGraphicsConfiguration().getBounds());
        getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
        playerLan = new EmbeddedMediaPlayerComponent();

        //jPanelVideoLan.add(playerLan);
        //  playerLan.setSize(jPanelVideoLan.getSize());
//        playerLan.getMediaPlayer().enableOverlay(true);
//        playerLan.getMediaPlayer().playMedia("C:\\Media\\Movies\\lucky number Plazuela.jpg");
        jLabelResultadoIzq.setText("");
        jLabelResultadoDer.setText("");
        playerAleatorio = new EmbeddedMediaPlayerComponent();
        jButtonRandom.setFocusable(true);
        jPanelVideoAleatorio.add(playerAleatorio);
        playerAleatorio.setSize(600, 540);
        playerAleatorio.setVisible(true);
        file = new File("C:\\Media\\Movies\\principal.avi");
//        String absolutePath = file.getAbsolutePath();
//        String newPath = absolutePath.substring(0, absolutePath.length() - 4) + "_" + System.currentTimeMillis() + ".png";
//        System.out.println("" + file.getAbsolutePath());
        //if( player.getMediaPlayer().saveSnapshot(new File(newPath))) 
        playerAleatorio.getMediaPlayer().setRepeat(true);
        playerAleatorio.getMediaPlayer().playMedia(file.getAbsolutePath());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelVideoAleatorio = new javax.swing.JPanel();
        jLabelResultadoDer = new javax.swing.JLabel();
        jButtonRandom = new javax.swing.JButton();
        jLabelResultadoIzq = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 102, 51));
        setUndecorated(true);

        jPanelVideoAleatorio.setBackground(new java.awt.Color(255, 255, 102));
        jPanelVideoAleatorio.setPreferredSize(new java.awt.Dimension(600, 540));

        javax.swing.GroupLayout jPanelVideoAleatorioLayout = new javax.swing.GroupLayout(jPanelVideoAleatorio);
        jPanelVideoAleatorio.setLayout(jPanelVideoAleatorioLayout);
        jPanelVideoAleatorioLayout.setHorizontalGroup(
            jPanelVideoAleatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        jPanelVideoAleatorioLayout.setVerticalGroup(
            jPanelVideoAleatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );

        jLabelResultadoDer.setFont(new java.awt.Font("Arial", 1, 200)); // NOI18N
        jLabelResultadoDer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelResultadoDer.setText("00");

        jButtonRandom.setText("Random");
        jButtonRandom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRandomActionPerformed(evt);
            }
        });

        jLabelResultadoIzq.setFont(new java.awt.Font("Arial", 1, 200)); // NOI18N
        jLabelResultadoIzq.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelResultadoIzq.setText("00");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ruleta/imagenes/luckynumber.PNG"))); // NOI18N
        jLabel1.setRequestFocusEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelResultadoIzq)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelVideoAleatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelResultadoDer)
                            .addComponent(jButtonRandom)))
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelResultadoIzq)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabelResultadoDer)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonRandom))
                        .addComponent(jPanelVideoAleatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRandomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRandomActionPerformed
        lucky();
        //jButtonRandom.setVisible(false);
        /*   new Thread(new Runnable() {
            @Override
            public void run() {
                //jLabelResultadoIzq.setText("");
                //jLabelResultadoDer.setText("");
                try {
                    file = new File("C:\\Media\\Movies\\principal.avi");
                    playerAleatorio.getMediaPlayer().playMedia(file.getAbsolutePath());
                    Thread.sleep(10000);
                    Random aleatorio = new Random();
                    int resultado = (0 + aleatorio.nextInt((37 + 1) - 0));
                    String datos = null;
                    datos = "" + resultado;
                    if (datos.length() == 1) {

                        if (datos == "0") {

                        } else {

                            datos = "0" + datos;
                            System.out.println(".run() " + datos);
                        }
                    }
                    if (resultado == 37) {
                        datos = "" + resultado;
                        jLabelResultadoIzq.setText("00");
                        jLabelResultadoDer.setText("00");
                    } else if (resultado == 0) {
                        datos = "00";
                        jLabelResultadoIzq.setText("0");
                        jLabelResultadoDer.setText("0");
                    } else {
                        // datos = "" + resultado;
                        jLabelResultadoIzq.setText("" + resultado);
                        jLabelResultadoDer.setText("" + resultado);
                    }

                    System.out.println("" + datos);

                    file = new File("C:\\Media\\Movies\\Win 1-1 DZ Mercury Pockets Mahogany\\win_" + datos + ".avi");
                    playerAleatorio.getMediaPlayer().playMedia(file.getAbsolutePath());
                    Thread.sleep(8000);
                    file = new File("C:\\Media\\Movies\\principal.avi");
                    playerAleatorio.getMediaPlayer().setRepeat(true);
                    playerAleatorio.getMediaPlayer().playMedia(file.getAbsolutePath());

                } catch (InterruptedException ex) {
                    Logger.getLogger(NewApplication.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();*/

//jButtonRandom.setVisible(true);
//jButtonRandom.setFocusable(true);
    }//GEN-LAST:event_jButtonRandomActionPerformed
    public void lucky() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //jLabelResultadoIzq.setText("");
                //jLabelResultadoDer.setText("");
                try {

                    Random aleatorio = new Random();
                    int resultado = (0 + aleatorio.nextInt((37 + 1) - 0));
                    String datos = null;
                    datos = "" + resultado;
                    if (datos.length() == 1) {

                        if (datos == "0") {

                        } else {

                            datos = "0" + datos;
                            System.out.println(".run() " + datos);
                        }
                    }
                    if (resultado == 37) {
                        datos = "" + resultado;

                    } else if (resultado == 0) {
                        datos = "00";

                    } else {
                        // datos = "" + resultado;

                    }
                    file = new File("C:\\Media\\Movies\\principal.avi");
                    playerAleatorio.getMediaPlayer().playMedia(file.getAbsolutePath());
                    System.out.println("" + datos);
                    Thread.sleep(10000);
                    file = new File("C:\\Media\\Movies\\Win 1-1 DZ Mercury Pockets Mahogany\\win_" + datos + ".avi");
                    playerAleatorio.getMediaPlayer().playMedia(file.getAbsolutePath());
                    Thread.sleep(1000);
                    if (resultado == 37) {

                        jLabelResultadoIzq.setText("00");
                        jLabelResultadoDer.setText("00");
                    } else if (resultado == 0) {

                        jLabelResultadoIzq.setText("0");
                        jLabelResultadoDer.setText("0");
                    } else {

                        jLabelResultadoIzq.setText("" + resultado);
                        jLabelResultadoDer.setText("" + resultado);
                    }
                    Thread.sleep(7000);
                    file = new File("C:\\\\Media\\\\Movies\\\\lucky number Plazuela.jpg");
                    //playerAleatorio.getMediaPlayer().setRepeat(true);
                    playerAleatorio.getMediaPlayer().playMedia(file.getAbsolutePath());

                } catch (InterruptedException ex) {
                    Logger.getLogger(NewApplication.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();

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
            java.util.logging.Logger.getLogger(NewApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewApplication().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonRandom;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelResultadoDer;
    private javax.swing.JLabel jLabelResultadoIzq;
    private javax.swing.JPanel jPanelVideoAleatorio;
    // End of variables declaration//GEN-END:variables

}
