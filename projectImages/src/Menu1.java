import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/*
 * Created by JFormDesigner on Sat May 27 13:52:42 EEST 2017
 */


/**
 * @author Bianca Lungu
 */
public class Menu1 extends JFrame {
    public Menu1() {
        initComponents();
    }

    ImageTransformer imageTransformer = new ImageTransformer();
    BufferedImage currentImage;
    BufferedImage resultedImage;

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("LogIn");
        mainFrame.setContentPane(new Menu1().mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setSize(1200, 700);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);

    }

    private void uploadActionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("H:/school/semestrul VI/projectImages"));
        int returnvalue = fileChooser.showOpenDialog(this);

        if (returnvalue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            BufferedImage bufferedImage;

            try {
                bufferedImage = ImageIO.read(file);
                currentImage = bufferedImage;
                imageTransformer.histogram(bufferedImage);

                ImageIcon imageIcon1 = new ImageIcon(bufferedImage.getScaledInstance(600, 500, java.awt.Image.SCALE_SMOOTH));
                image.setIcon(imageIcon1);

            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

    }

    private void blurActionPerformed(ActionEvent e) {

        blackWhiteSlider.setVisible(false);
        contrastSlider.setVisible(false);

        if (currentImage != null) {
            resultedImage = imageTransformer.blurImage(currentImage);

            ImageIcon imageIcon1 = new ImageIcon(resultedImage.getScaledInstance(600, 500, java.awt.Image.SCALE_SMOOTH));
            result.setIcon(imageIcon1);
        }
    }

    private void edgeDetectionActionPerformed(ActionEvent e) {
        blackWhiteSlider.setVisible(false);
        contrastSlider.setVisible(false);

        if (currentImage != null) {
            resultedImage = imageTransformer.edgeDetection(currentImage);

            ImageIcon imageIcon1 = new ImageIcon(resultedImage.getScaledInstance(600, 500, java.awt.Image.SCALE_SMOOTH));
            result.setIcon(imageIcon1);
        }
    }

    private void glowActionPerformed(ActionEvent e) {
        blackWhiteSlider.setVisible(false);
        contrastSlider.setVisible(false);

        if (currentImage != null) {
            resultedImage = imageTransformer.glowFilter(currentImage);

            ImageIcon imageIcon1 = new ImageIcon(resultedImage.getScaledInstance(600, 500, java.awt.Image.SCALE_SMOOTH));
            result.setIcon(imageIcon1);
        }
    }

    private void blackAndWhiteActionPerformed(ActionEvent e) {
        blackWhiteSlider.setVisible(true);
        contrastSlider.setVisible(false);
    }

    private void blackWhiteSliderStateChanged(ChangeEvent e) {

        if (currentImage != null) {
            resultedImage = imageTransformer.imageBinarization(currentImage, blackWhiteSlider.getValue());

            ImageIcon imageIcon1 = new ImageIcon(resultedImage.getScaledInstance(600, 500, java.awt.Image.SCALE_SMOOTH));
            result.setIcon(imageIcon1);
        }
    }

    private void contrastActionPerformed(ActionEvent e) {
        contrastSlider.setVisible(true);
        blackWhiteSlider.setVisible(false);
    }

    private void contrastSliderStateChanged(ChangeEvent e) {
        if (currentImage != null) {
            resultedImage = imageTransformer.briteningFactor(currentImage, contrastSlider.getValue());

            ImageIcon imageIcon1 = new ImageIcon(resultedImage.getScaledInstance(600, 500, java.awt.Image.SCALE_SMOOTH));
            result.setIcon(imageIcon1);
        }
    }

    private void greyScaleActionPerformed(ActionEvent e) {
        blackWhiteSlider.setVisible(false);
        contrastSlider.setVisible(false);

        if (currentImage != null) {
            resultedImage = imageTransformer.greyScale(currentImage);

            ImageIcon imageIcon1 = new ImageIcon(resultedImage.getScaledInstance(600, 500, java.awt.Image.SCALE_SMOOTH));
            result.setIcon(imageIcon1);
        }
    }

    private void negativeActionPerformed(ActionEvent e) {
        blackWhiteSlider.setVisible(false);
        contrastSlider.setVisible(false);

        if (currentImage != null) {
            resultedImage = imageTransformer.negative(currentImage);

            ImageIcon imageIcon1 = new ImageIcon(resultedImage.getScaledInstance(600, 500, java.awt.Image.SCALE_SMOOTH));
            result.setIcon(imageIcon1);
        }
    }

    private void histogramActionPerformed(ActionEvent e) {

        if (currentImage != null) {
            double[] histogram = imageTransformer.histogram(currentImage);

            HistogramDataset dataset = new HistogramDataset();
            dataset.setType(HistogramType.FREQUENCY);
            dataset.addSeries("Histogram", histogram, histogram.length);

            JFreeChart chart = ChartFactory.createHistogram("Histogram", "", "", dataset,
                    PlotOrientation.VERTICAL, false, false, false);
//        setTheme(chart);

            JFrame jFrame = new JFrame("Histogram");
            jFrame.getContentPane().add(new ChartPanel(chart));
            jFrame.pack();
            jFrame.setVisible(true);
        }
    }

    static private final void setTheme(final JFreeChart chart) {
        XYPlot plot = (XYPlot) chart.getPlot();
        XYBarRenderer r = (XYBarRenderer) plot.getRenderer();
        StandardXYBarPainter bp = new StandardXYBarPainter();
        r.setBarPainter(bp);
        r.setSeriesOutlinePaint(0, Color.lightGray);
        r.setShadowVisible(false);
        r.setDrawBarOutline(false);
        setBackgroundDefault(chart);
    }

    static private final void setBackgroundDefault(final JFreeChart chart) {
        BasicStroke gridStroke = new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, new float[]{2.0f, 1.0f}, 0.0f);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setRangeGridlineStroke(gridStroke);
        plot.setDomainGridlineStroke(gridStroke);
        plot.setBackgroundPaint(new Color(235, 235, 235));
        plot.setRangeGridlinePaint(Color.white);
        plot.setDomainGridlinePaint(Color.white);
        plot.setOutlineVisible(false);
        plot.getDomainAxis().setAxisLineVisible(false);
        plot.getRangeAxis().setAxisLineVisible(false);
        plot.getDomainAxis().setLabelPaint(Color.gray);
        plot.getRangeAxis().setLabelPaint(Color.gray);
        plot.getDomainAxis().setTickLabelPaint(Color.gray);
        plot.getRangeAxis().setTickLabelPaint(Color.gray);
        plot.getRangeAxis().setRange(0, 255);
    }

    private void saveActionPerformed(ActionEvent e) {

        if(resultedImage != null) {
            File output = new File("result.jpg");
            try {
                ImageIO.write(resultedImage, "jpg", output);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Denisa Ungur
        mainPanel = new JPanel();
        menuBar1 = new JMenuBar();
        filters = new JMenu();
        blur = new JMenuItem();
        edgeDetection = new JMenuItem();
        glow = new JMenuItem();
        blackAndWhite = new JMenuItem();
        contrast = new JMenuItem();
        greyScale = new JMenuItem();
        negative = new JMenuItem();
        options = new JMenu();
        upload = new JMenuItem();
        histogram = new JMenuItem();
        image = new JLabel();
        result = new JLabel();
        blackWhiteSlider = new JSlider();
        contrastSlider = new JSlider();
        save = new JButton();

        //======== this ========
        setBackground(Color.black);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== mainPanel ========
        {
            mainPanel.setBackground(Color.darkGray);

            // JFormDesigner evaluation mark
            mainPanel.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), mainPanel.getBorder())); mainPanel.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            mainPanel.setLayout(null);

            //======== menuBar1 ========
            {
                menuBar1.setBackground(Color.darkGray);
                menuBar1.setBorderPainted(false);
                menuBar1.setForeground(Color.white);

                //======== filters ========
                {
                    filters.setText("Filters");
                    filters.setBackground(new Color(102, 102, 102));
                    filters.setBorderPainted(false);
                    filters.setForeground(Color.white);

                    //---- blur ----
                    blur.setText("Blur");
                    blur.setForeground(Color.white);
                    blur.setBackground(Color.darkGray);
                    blur.addActionListener(e -> blurActionPerformed(e));
                    filters.add(blur);

                    //---- edgeDetection ----
                    edgeDetection.setText("Edge Detection");
                    edgeDetection.setBackground(Color.darkGray);
                    edgeDetection.setForeground(Color.white);
                    edgeDetection.addActionListener(e -> edgeDetectionActionPerformed(e));
                    filters.add(edgeDetection);

                    //---- glow ----
                    glow.setText("Glow Filter");
                    glow.setBackground(Color.darkGray);
                    glow.setForeground(Color.white);
                    glow.addActionListener(e -> glowActionPerformed(e));
                    filters.add(glow);

                    //---- blackAndWhite ----
                    blackAndWhite.setText("Black and White");
                    blackAndWhite.setBackground(Color.darkGray);
                    blackAndWhite.setForeground(Color.white);
                    blackAndWhite.addActionListener(e -> blackAndWhiteActionPerformed(e));
                    filters.add(blackAndWhite);

                    //---- contrast ----
                    contrast.setText("Brightness & Contrast");
                    contrast.setBackground(Color.darkGray);
                    contrast.setForeground(Color.white);
                    contrast.addActionListener(e -> contrastActionPerformed(e));
                    filters.add(contrast);

                    //---- greyScale ----
                    greyScale.setText("Grey Scale");
                    greyScale.setBackground(Color.darkGray);
                    greyScale.setForeground(Color.white);
                    greyScale.addActionListener(e -> greyScaleActionPerformed(e));
                    filters.add(greyScale);

                    //---- negative ----
                    negative.setText("Negative");
                    negative.setBackground(Color.darkGray);
                    negative.setForeground(Color.white);
                    negative.addActionListener(e -> negativeActionPerformed(e));
                    filters.add(negative);
                }
                menuBar1.add(filters);

                //======== options ========
                {
                    options.setText("Options");
                    options.setForeground(Color.white);

                    //---- upload ----
                    upload.setText("Upload Image");
                    upload.setForeground(Color.white);
                    upload.setBackground(Color.darkGray);
                    upload.addActionListener(e -> uploadActionPerformed(e));
                    options.add(upload);

                    //---- histogram ----
                    histogram.setText("Histogram");
                    histogram.setBackground(Color.darkGray);
                    histogram.setForeground(Color.white);
                    histogram.addActionListener(e -> histogramActionPerformed(e));
                    options.add(histogram);
                }
                menuBar1.add(options);
            }
            mainPanel.add(menuBar1);
            menuBar1.setBounds(0, 0, 103, menuBar1.getPreferredSize().height);
            mainPanel.add(image);
            image.setBounds(10, 50, 500, 600);
            mainPanel.add(result);
            result.setBounds(555, 50, 500, 600);

            //---- blackWhiteSlider ----
            blackWhiteSlider.setForeground(Color.white);
            blackWhiteSlider.setBackground(Color.darkGray);
            blackWhiteSlider.setMaximum(255);
            blackWhiteSlider.setVisible(false);
            blackWhiteSlider.setValue(127);
            blackWhiteSlider.addChangeListener(e -> blackWhiteSliderStateChanged(e));
            mainPanel.add(blackWhiteSlider);
            blackWhiteSlider.setBounds(370, 25, blackWhiteSlider.getPreferredSize().width, 16);

            //---- contrastSlider ----
            contrastSlider.setBackground(Color.darkGray);
            contrastSlider.setForeground(Color.white);
            contrastSlider.setVisible(false);
            contrastSlider.setMaximum(200);
            contrastSlider.addChangeListener(e -> contrastSliderStateChanged(e));
            mainPanel.add(contrastSlider);
            contrastSlider.setBounds(new Rectangle(new Point(415, 20), contrastSlider.getPreferredSize()));

            //---- save ----
            save.setText("Save");
            save.setBackground(Color.darkGray);
            save.setForeground(Color.white);
            save.addActionListener(e -> saveActionPerformed(e));
            mainPanel.add(save);
            save.setBounds(new Rectangle(new Point(1065, 140), save.getPreferredSize()));

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < mainPanel.getComponentCount(); i++) {
                    Rectangle bounds = mainPanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = mainPanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                mainPanel.setMinimumSize(preferredSize);
                mainPanel.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(mainPanel);
        mainPanel.setBounds(0, -5, 1200, 700);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Denisa Ungur
    private JPanel mainPanel;
    private JMenuBar menuBar1;
    private JMenu filters;
    private JMenuItem blur;
    private JMenuItem edgeDetection;
    private JMenuItem glow;
    private JMenuItem blackAndWhite;
    private JMenuItem contrast;
    private JMenuItem greyScale;
    private JMenuItem negative;
    private JMenu options;
    private JMenuItem upload;
    private JMenuItem histogram;
    private JLabel image;
    private JLabel result;
    private JSlider blackWhiteSlider;
    private JSlider contrastSlider;
    private JButton save;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
