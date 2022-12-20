
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @author E.V. Belyi
 * @version 1.0
 *
 * Class that run ProgramFrame which grants user information
 * about Program
 */
public class AboutWindow extends JFrame {
    /*
     *create instance of Program frame
     *@param instance of mainWindow
     */
    public AboutWindow(JFrame parentFrame){
        /*
        set up Frame
         */
        setLocation(parentFrame.getLocation());
        setResizable(false);
        setSize(550,150);
        setVisible(true);
        /*
        create mainPanel
         */
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0,0,550,150);
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        /*
        create Label for showing information
         */
        JLabel jl1 = new JLabel("Программа для нахождения заработной платы");
        JLabel jl2 =new JLabel("Данная программа находит отпускные пользователя в зависимости от начисленной ");
        JLabel jl3 = new JLabel("заработной платы за 12 месяцев и выбранного месяца. На вход принимается только");
        JLabel jl4 = new JLabel("зарплата, остальное программа берет из базы данных.");
        /*
        create Close button
         */
        JButton bt = new JButton("Назад");
        /*
        add listener which close frame and show mainWindow
         */
        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    parentFrame.setVisible(true);
                    dispose();
            }
        });
        /*
        add Components to frame
         */
        mainPanel.add(jl1);
        mainPanel.add(Box.createRigidArea(new Dimension(0,5)));
        mainPanel.add(jl2);
        mainPanel.add(Box.createRigidArea(new Dimension(0,5)));
        mainPanel.add(jl3);
        mainPanel.add(Box.createRigidArea(new Dimension(0,5)));
        mainPanel.add(jl4);
        mainPanel.add(Box.createRigidArea(new Dimension(0,5)));
        mainPanel.add(bt);
        add(mainPanel);
    }
}
