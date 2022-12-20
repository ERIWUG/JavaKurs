import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author E.V. Belyi
 * @version 1.0
 *
 * Class that run InfoFrame.
 */
public class StartWindow extends JFrame {
    /**
     *Run main window
     */
    public static void main(String []arg) {
        JFrame jf = new StartWindow();

    }


    /*
    *Create instance of InfoFrame
    *@param mainFrame instance
    */
    public StartWindow(){
        setResizable(false);
        Font font = new Font("System", Font.BOLD, 18);
        Font font1 = new Font("System", Font.BOLD, 20);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(null);
        /**
         * Timer for closing
         */

        Timer timer = new Timer(60000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        timer.start();

        /*
        Setting all components
         */
        JLabel label1 = new JLabel("Белорусский государственный технический университет");
        JLabel label2 = new JLabel("Факультет информационных технологий и робототехники");
        JLabel label3 = new JLabel("Кафедра програмного обеспечения информауионных систем и техгологий");
        JLabel label4 = new JLabel("Курсовая работа");
        JLabel label5 = new JLabel("Расчет начисления заработной\n" );
        JLabel prod = new JLabel("платы за дни отпуска");
        label5.setFont(font1);
        JLabel label11 = new JLabel("по дисциплине «Программирование на языке java»");
        JLabel label6 = new JLabel("Выполнил: Студент группы 10702120");
        JLabel label7 = new JLabel("Белый Егор Викторович");
        JLabel label8 = new JLabel("Преподаватель: к.ф.-м.н., доц.");
        JLabel label9 = new JLabel("Сидорик Валерий Владимирович");
        JLabel label10 = new JLabel("Минск 2022");
        label4.setFont(font);
        label1.setBounds(93, 20, 500, 15);
        label2.setBounds(89, 37, 500, 15);
        label3.setBounds(55, 54, 500, 15);
        label4.setBounds(172, 188, 500, 25);
        label5.setBounds(100, 235, 500, 25);
        prod.setBounds(190,250,500,25);
        label6.setBounds(282, 289, 500, 15);
        label7.setBounds(282, 306, 500, 15);
        label8.setBounds(282, 330, 500, 15);
        label9.setBounds(282, 347, 500, 15);
        label10.setBounds(216, 401, 500, 15);
        label11.setBounds(124, 216, 500, 15);
        /*
        create button for fall back
         */
        JButton but2 = new JButton("Выход");
        but2.setBounds(350, 450, 150, 35);
        /*
        add listener for button which show mainWindow
         */
        but2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JButton but1 = new JButton("Далее");
        but1.setBounds(10,450,150,35);
        but1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                JFrame n = new CalculateWindow();
                n.setLocation(getLocation());
                dispose();
            }
        });
        /*
        create Image
         */
        BufferedImage myPicture;
        try
        {
            myPicture = ImageIO.read(new File("maney.png"));
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        JLabel cart = new JLabel(new ImageIcon(myPicture));
        cart.setBounds(30,289,100,100);
        /*
        add all components on Frame
         */
        this.add(prod);this.add(but1);
        this.add(but2);
        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.add(label4);
        this.add(label5);
        this.add(label6);this.add(cart);
        this.add(label11);
        this.add(label7);
        this.add(label8);
        this.add(label9);
        this.add(label10);
        this.setResizable(false);
        this.setSize(550, 550);
        this.setLocation(400, 100);
        this.getContentPane();
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
    }
}


