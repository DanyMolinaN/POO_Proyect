package juego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class MenúPrincipal extends JPanel {
    private JPanel panel1;
    private JPanel panel2;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JLabel label;
    private Timer timer;
    private Timer fadeTimer;
    private List<Image> backgroundImages;
    private int currentImageIndex = 0;
    private float alpha = 1.0f;
    private double scaleX;
    private double scaleY;
    private JFrame parentFrame;  // Referencia al JFrame contenedor
    private JButton button4;

    MenúPrincipal(JFrame parentFrame) {  // Constructor
        this.parentFrame = parentFrame;  // Inicializar la referencia al JFrame
        initScales();
        loadBackgroundImages();
        createPanels();
        createButtons();
        createLabel();
        positionComponents();
        startBackgroundTimer();
    }

    private void initScales() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        scaleX = screenSize.getWidth() / 1080.0;
        scaleY = screenSize.getHeight() / 720.0;
    }

    private void loadBackgroundImages() {
        backgroundImages = new ArrayList<>();
        backgroundImages.add(new ImageIcon("src/recursos_gráficos/logos/Fondo1Titulo.jpg").getImage().getScaledInstance((int)(1080 * scaleX), (int)(720 * scaleY), Image.SCALE_DEFAULT));
        backgroundImages.add(new ImageIcon("src/recursos_gráficos/logos/Titulo2.jpg").getImage().getScaledInstance((int)(1080 * scaleX), (int)(720 * scaleY), Image.SCALE_DEFAULT));
        backgroundImages.add(new ImageIcon("src/recursos_gráficos/logos/Titulo3.jpg").getImage().getScaledInstance((int)(1080 * scaleX), (int)(720 * scaleY), Image.SCALE_DEFAULT));
    }

    private void createPanels() {
        panel2 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
                g2d.drawImage(backgroundImages.get(currentImageIndex), -350, 0, getWidth(), getHeight(), this);
                g2d.dispose();
            }
        };
        panel2.setBackground(Color.BLACK);
        panel2.setLayout(null);
    }

    private void createButtons() {
        button1 = createImageButton("src/recursos_gráficos/botones/jugar2.png");
        button2 = createImageButton("src/recursos_gráficos/botones/SCORE1.png");
        button3 = createImageButton("src/recursos_gráficos/botones/CREDITS.png");
        button4 = createImageButton("src/recursos_gráficos/botones/Settings.png");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJuego();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void iniciarJuego() {
        ControladorJuego controlador = new ControladorJuego();
        PanelJuego panel = new PanelJuego(controlador);
        controlador.setPanel(panel);
        JFrame juegoFrame = new JFrame("Space Invaders");
        juegoFrame.add(panel);
        juegoFrame.setSize(1080, 720);
        juegoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        juegoFrame.setLocationRelativeTo(null);
        juegoFrame.setVisible(true);
        controlador.pausarJuego();
        parentFrame.dispose();  // Cerrar el menú principal
        new javax.swing.Timer(10, e -> {
            controlador.actualizar();
            panel.repaint();
        }).start();
    }

    private void createLabel() {
        ImageIcon gifIcon = new ImageIcon("src/recursos_gráficos/logos/tituloSpaceInavders.png");
        Image gifImage = gifIcon.getImage().getScaledInstance((int)(250 * scaleX), (int)(250 * scaleY), Image.SCALE_DEFAULT);
        label = new JLabel(new ImageIcon(gifImage));
    }

    private void positionComponents() {
        label.setBounds((int)(800 * scaleX), 0, (int)(250 * scaleX), (int)(250 * scaleY));
        button1.setBounds((int)(830 * scaleX), (int)(250 * scaleY), (int)(230 * scaleX), (int)(130 * scaleY));
        button2.setBounds((int)(800 * scaleX), (int)(350 * scaleY), (int)(300 * scaleX), (int)(200 * scaleY));
        button3.setBounds((int)(800 * scaleX), (int)(500 * scaleY), (int)(300 * scaleX), (int)(200 * scaleY));
        button4.setBounds((int)(10 * scaleX), (int)(550 * scaleY), (int)(200 * scaleX), (int)(100 * scaleY));

        panel2.add(label);
        panel2.add(button1);
        panel2.add(button2);
        panel2.add(button3);
        panel2.add(button4);
    }

    private void startBackgroundTimer() {
        timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fadeOut();
            }
        });
        timer.start();
    }

    private void fadeOut() {
        fadeTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alpha -= 0.1f;
                if (alpha <= 0.0f) {
                    alpha = 0.0f;
                    fadeTimer.stop();
                    currentImageIndex = (currentImageIndex + 1) % backgroundImages.size();
                    fadeIn();
                }
                panel2.repaint();
            }
        });
        fadeTimer.start();
    }

    private void fadeIn() {
        fadeTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alpha += 0.1f;
                if (alpha >= 1.0f) {
                    alpha = 1.0f;
                    fadeTimer.stop();
                }
                panel2.repaint();
            }
        });

        fadeTimer.start();
    }

    private JButton createImageButton(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage().getScaledInstance((int)(225 * scaleX), (int)(125* scaleY), Image.SCALE_DEFAULT);
        JButton button = new JButton(new ImageIcon(img));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
       button.addMouseListener(new MouseListener() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setOpaque(true);
                button.setBackground(new Color(0, 100, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setOpaque(false);
                button.setBackground(new Color(0, 0, 0, 0));
            }

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}
        });
        return button;
    }



    public JPanel getPanel2() {
        return panel2;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Menú Principal");
                MenúPrincipal menu = new MenúPrincipal(frame);
                frame.setContentPane(menu.getPanel2());
                frame.setSize(1080, 720);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}

