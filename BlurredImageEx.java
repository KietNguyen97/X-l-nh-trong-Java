/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doan;

/**
 *
 * @author KietNguyen
 */
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
class Test3 extends JPanel {
 
    private BufferedImage mshi;
    private BufferedImage bluri;
 
    public Test3() {
 
        loadImage();
        createBlurredImage();
        setSurfaceSize();
    }
 
    private void loadImage() {
         
        try {
             
            mshi = ImageIO.read(new File("mushrooms.jpg"));
        } catch (IOException ex) {
             
            Logger.getLogger(Test3.class.getName()).log(
                    Level.WARNING, null, ex);
        }
    }
     
    private void createBlurredImage() {
 
        float[] blurKernel = {
            1 / 6f, 1 / 6f, 1 / 6f,
            1 / 6f, 1 / 6f, 1 / 6f,
            1 / 6f, 1 / 6f, 1 / 6f
        };
 
        BufferedImageOp blur = new ConvolveOp(new Kernel(3, 3, blurKernel));
        bluri = blur.filter(mshi, new BufferedImage(mshi.getWidth(),
                mshi.getHeight(), mshi.getType()));
    }
     
    private void setSurfaceSize() {
         
        Dimension d = new Dimension();
        d.width = mshi.getWidth(null);
        d.height = mshi.getHeight(null);
        setPreferredSize(d);        
    }
 
    private void doDrawing(Graphics g) {
 
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(bluri, null, 0, 0);
    }
 
    @Override
    public void paintComponent(Graphics g) {
 
        super.paintComponent(g);
        doDrawing(g);
    }
}
 
public class BlurredImageEx extends JFrame {
 
    public BlurredImageEx() {
 
        setTitle("Blurred image");
        add(new Test3());
 
        pack();
         
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 
    public static void main(String[] args) {
 
        EventQueue.invokeLater(() -> {
            BlurredImageEx ex = new BlurredImageEx();
            ex.setVisible(true);
        });
    }
}