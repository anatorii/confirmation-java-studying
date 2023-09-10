import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Confirmation extends JFrame {
    static int width = 800;
    static int height = 600;
    static int clientWidth;
    static int clientHeight;
    public JPanel panel;

    public Confirmation() {
        super("confirmation");
        initGui();
    }

    private void initGui() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(Confirmation.width, Confirmation.height));
        this.setLocation(d.width / 2 - Confirmation.width / 2, d.height / 2 - Confirmation.height / 2);
        this.getContentPane().setBackground(Color.lightGray);
        this.setResizable(false);

        panel = new JPanel();
        panel.setBackground(Color.lightGray);
        panel.setFocusable(true);

        JButton button = new JButton("Диалоговое окно");

        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonActionPerformed(e);
            }
        });

        panel.add(button);

        this.getContentPane().add(panel);
    }

    private void buttonActionPerformed(ActionEvent e) {
        int resultClose = JOptionPane.showConfirmDialog(
                Confirmation.this,
                "Close the application?",
                "Confirmation dialog",
                JOptionPane.YES_NO_OPTION
        );

        int resultData = JOptionPane.showConfirmDialog(
                Confirmation.this,
                "Save data?",
                "Confirmation dialog",
                JOptionPane.YES_NO_OPTION
        );

        String message = "не закрывать и не сохранять";
        if (resultClose == JOptionPane.YES_OPTION && resultData == JOptionPane.YES_OPTION) {
            message = "закрывать и сохранять";
        } else if (resultClose == JOptionPane.YES_OPTION && resultData == JOptionPane.NO_OPTION) {
            message = "закрывать и не сохранять";
        } else if (resultClose == JOptionPane.NO_OPTION && resultData == JOptionPane.YES_OPTION) {
            message = "не закрывать и сохранять";
        }

        JOptionPane.showMessageDialog(Confirmation.this, message,
                "Questioning result", JOptionPane.INFORMATION_MESSAGE);
    }

    public void setVisible(boolean b) {
        super.setVisible(b);
        clientWidth = Confirmation.width;
        clientHeight = Confirmation.height;
        if (isResizable()) {
            clientWidth = getContentPane().getWidth();
            clientHeight = getContentPane().getHeight();
        }
    }

    public static void main(String[] args) {
        UIManager.put("OptionPane.yesButtonText"   , "Да"    );
        UIManager.put("OptionPane.noButtonText"    , "Нет"   );
        UIManager.put("OptionPane.cancelButtonText", "Отмена");
        UIManager.put("OptionPane.okButtonText"    , "OK");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Confirmation frame = new Confirmation();
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

}
