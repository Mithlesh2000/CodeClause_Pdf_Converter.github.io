
package imagetopdfconverter;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.graphics.PdfImage;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageToPDF extends javax.swing.JFrame {
    private final JFileChooser openFileChooser;
    private final JFileChooser saveFileChooser;
    private BufferedImage originalBI;

    public ImageToPDF() {
        initComponents();

        openFileChooser = new JFileChooser();
        openFileChooser.setCurrentDirectory(new File("This PC"));
        openFileChooser.setFileFilter(new FileNameExtensionFilter("PNG images", "png"));

        saveFileChooser = new JFileChooser();
        saveFileChooser.setCurrentDirectory(new File("uploads/"));
        saveFileChooser.setFileFilter(new FileNameExtensionFilter("PNG images", "png"));
    }

    @SuppressWarnings("unchecked")

    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Open File...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Upload");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Convert");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jLabel3.setText("Image to PDF Converter");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("Note : While uploading save image name as image.png");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(141, 141, 141))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(jButton1)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                289, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(110, 110, 110)
                                                                .addComponent(jButton2,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 89,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel2,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 289,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(225, 225, 225)
                                                                .addComponent(jButton3))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(29, 29, 29)
                                                .addComponent(jLabel4)))
                                .addContainerGap(53, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jLabel3)
                                .addGap(91, 91, 91)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton1))
                                .addGap(55, 55, 55)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton2)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(76, 76, 76)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62,
                                        Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addGap(17, 17, 17)));

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        int returnValue = openFileChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                originalBI = ImageIO.read(openFileChooser.getSelectedFile());
                jLabel1.setText("Image File Successfully loaded");
            } catch (IOException ioe) {
                jLabel1.setText("Failed to load Image file");
            }
        } else {
            jLabel1.setText("No File Choosen");
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {

        saveImage();
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        imgtopdf();
    }

    private void imgtopdf() {
        PdfDocument pdf = new PdfDocument();

        PdfPageBase page = pdf.getPages().add();

        PdfImage image = PdfImage.fromFile("uploads\\image.png");

        double widthFitRate = image.getPhysicalDimension().getWidth() / page.getCanvas().getClientSize().getWidth();
        double heightFitRate = image.getPhysicalDimension().getHeight() / page.getCanvas().getClientSize().getHeight();
        double fitRate = Math.max(widthFitRate, heightFitRate);
        double fitWidth = image.getPhysicalDimension().getWidth() / fitRate;
        double fitHeight = image.getPhysicalDimension().getHeight() / fitRate;
        page.getCanvas().drawImage(image, new Rectangle2D.Double(0, 0, fitWidth, fitHeight));

        pdf.saveToFile("output/InsertedImage.pdf");
    }

    private void saveImage() {
        int returnValue = saveFileChooser.showSaveDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                ImageIO.write(originalBI, "png", saveFileChooser.getSelectedFile());
                jLabel2.setText("Image File uploaded Successfully");
            } catch (IOException ioe) {
                jLabel2.setText("Failed to upload Image file");
            }
        } else {
            jLabel2.setText("No File Choosen");
        }
    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ImageToPDF.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ImageToPDF.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ImageToPDF.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImageToPDF.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ImageToPDF().setVisible(true);
            }
        });
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;

}
