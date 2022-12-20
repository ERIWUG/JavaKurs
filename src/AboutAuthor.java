import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/**
 * @author E.V. Belyi
 * @version 1.0
 *
 * Class that run UserFrame which grants user information
 * about Author
 */
public class AboutAuthor extends JFrame {
    /*
    create instance of UserFrame
    @param mainWindow frame
     */
    public AboutAuthor(JFrame parentFrame){
        /*
        set up UserFrame
         */
        setLocation(parentFrame.getLocation());
        setResizable(false);
        setSize(300,300);
        setLayout(null);
        setVisible(true);
        /*
        create Image
         */
        BufferedImage myPicture;
        try
        {myPicture = ImageIO.read(new File("Athor.png"));} catch (IOException e)
        {throw new RuntimeException(e);}
        JLabel cart = new JLabel(new ImageIcon(myPicture));
        cart.setBounds(50,10,90,90);
        /*
        Create some Label for showing information
         */
        JLabel name = new JLabel("Автор:Белый Егор");
        name.setBounds(10,100,150,20);
        add(name);

        JLabel email = new JLabel("Email: e.belyi2013@gmail.com");
        email.setBounds(10,125,200,20);
        add(email);

        JLabel gr = new JLabel("Студент гр. 10702120");
        gr.setBounds(10,150,150,20);
        add(gr);
        /*
        create Close button
         */
        JButton b = new JButton("Назад");
        b.setBounds(10,190,150,30);
        /*
        add listener which close frame and show mainFrame
         */
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.setVisible(true);
                dispose();
            }
        });
        add(b);
        add(cart);
    }
}
