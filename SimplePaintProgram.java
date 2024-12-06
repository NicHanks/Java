import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimplePaintProgram extends JFrame {

    private int startX, startY, endX, endY;
    private Color currentColor = Color.BLACK;
    private JButton colorButton;
    private JPanel drawingPanel;

    public SimplePaintProgram() {
        setTitle("Simple Paint Program");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel controlPanel = new JPanel();
        colorButton = new JButton("Choose Color");
        colorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentColor = JColorChooser.showDialog(null, "Choose a color", currentColor);
            }
        });
        controlPanel.add(colorButton);

        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(currentColor);
                g.drawLine(startX, startY, endX, endY);
            }
        };
        drawingPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                startX = e.getX();
                startY = e.getY();
            }
        });
        drawingPanel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                endX = e.getX();
                endY = e.getY();
                repaint();
            }
        });

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(controlPanel, BorderLayout.NORTH);
        getContentPane().add(drawingPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SimplePaintProgram().setVisible(true);
            }
        });
    }
}
