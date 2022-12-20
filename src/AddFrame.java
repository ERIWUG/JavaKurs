import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
/**
 * @author E.V. Belyi
 * @version 1.0
 *
 * Class that run AddFrame which grants user ability
 * to Edit all data from database
 */
public class AddFrame extends JFrame {
    private int[] days={0,0,0,0,0,0,0,0,0,0,0,0};
    private  JTextArea[] jtDays = new JTextArea[12];
    /*
    create instance of AddFrame
    @param instance of mainWindow
     */
    public AddFrame(JFrame parentFrame){
        /*
        set up Frame
         */
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        /*
        create mainPanel
         */
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0,0,600,400);
        mainPanel.setLayout(null);
        /*
        get staff name from database
         */
        String[] names = DBcontroller.DBGetNames();
        /*
        create panel for radio button
         */
        JPanel radioTable = new JPanel();
        radioTable.setLayout(new GridLayout(2,1));
        radioTable.setBounds(370,10,200,70);
        /*
        create two radioButton
         */
        JRadioButton jrNew = new JRadioButton("Новый пользователь");
        JRadioButton jrEdit = new JRadioButton("Изменение существующих");
        jrEdit.setSelected(true);
        radioTable.add(jrNew);radioTable.add(jrEdit);
        /*
        add listener to check radiobuttons
         */
        jrEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jrNew.setSelected(!jrEdit.isSelected());
            }
        });
        /*
        add listener to check radiobuttons
         */
        jrNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jrEdit.setSelected(!jrNew.isSelected());
            }
        });
        /*
         *create 2 labels and 2 textarea to get information
         *about new staff member
         */
        JTextArea jaName = new JTextArea();
        jaName.setBounds(10,200,150,30);
        JTextArea jaLastName = new JTextArea();
        jaLastName.setBounds(170,200,150,30);
        JLabel jlName = new JLabel("Имя");
        jlName.setBounds(10,150,150,30);
        JLabel jlLast = new JLabel("Фамилия");
        jlLast.setBounds(170,150,150,30);
        /*
        create combobox which contains all staff members
         */
        JComboBox nameComboBox = new JComboBox(names);
        nameComboBox.setBounds(10,10,150,30);
        nameComboBox.setSelectedIndex(0);
        /*
        add listener which repaint table of user vacationdays
         */
        nameComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                days = DBcontroller.DBGetDays(nameComboBox.getSelectedItem().toString());
                for(int i=0;i<12;i++){
                    jtDays[i].setText(""+days[i]);
                }
                jaName.setText(nameComboBox.getSelectedItem().toString().split("\\s")[1]);
                jaLastName.setText(nameComboBox.getSelectedItem().toString().split("\\s")[0]);
            }
        });

        /*
        set up labels of user information
         */
        jaName.setText(nameComboBox.getSelectedItem().toString().split("\\s")[1]);
        jaLastName.setText(nameComboBox.getSelectedItem().toString().split("\\s")[0]);
        /*
        create close button
         */
        JButton jbtClose = new JButton("Close");
        jbtClose.setBounds(480,320,100,30);
        /*
        add action listener whick close frame and show mainWindow
         */
        jbtClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requestFocus();
                parentFrame.setLocation(getLocation());
                parentFrame.setVisible(true);
                dispose();
            }
        });
        /*
        create button which perform edit
         */
        JButton jbtEdit = new JButton("Edit");
        jbtEdit.setBounds(10,320,100,30);
        /*
        add listener which make new user of egit ald
         */
        jbtEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Confirmation
                int ans = JOptionPane.showConfirmDialog(mainPanel,"Уверены?");

                if(ans==0) {
                    if (jrEdit.isSelected()) {
                        //Add new User
                        int[] newdays = new int[12];
                        for (int i = 0; i < 12; i++) {
                            try {
                                newdays[i] = Integer.parseInt(jtDays[i].getText());
                            }
                            catch (NumberFormatException formatException){
                                newdays[i] = 0;
                            }
                        }
                        DBcontroller.DBUpdateDays(nameComboBox.getSelectedItem().toString(), newdays);
                        if (jaLastName.getText() + " " + jaName.getText() != nameComboBox.getSelectedItem().toString()) {
                            DBcontroller.DBUpdateName(jaLastName.getText(), jaName.getText());
                        }
                        requestFocus();
                        parentFrame.setLocation(getLocation());
                        parentFrame.setVisible(true);
                        dispose();
                    }
                    else{
                        //Edit
                        int[] newdays = new int[12];
                        for (int i = 0; i < 12; i++) {
                            newdays[i] = Integer.parseInt(jtDays[i].getText());
                        }
                        System.out.println(jaLastName.getText()+" "+jaName.getText());
                        if (Arrays.asList(names).contains(jaLastName.getText()+" "+jaName.getText())){
                            JOptionPane.showMessageDialog(mainPanel,"Такой пользователь уже существует!");
                        }
                        else {
                            DBcontroller.DBInsertName(jaName.getText(),jaLastName.getText(),newdays);
                            requestFocus();
                            parentFrame.setLocation(getLocation());
                            parentFrame.setVisible(true);
                            dispose();
                        }
                    }
                }

            }
        });
        /*
        Add remove button
         */
        JButton jbtRemove = new JButton("Remove");
        jbtRemove.setBounds(120,320,100,30);
        /*
        add listener which remove user from database
         */
        jbtRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ans = JOptionPane.showConfirmDialog(mainPanel,"Уверены?");
                if(ans==0){
                    DBcontroller.DBDeleteName(nameComboBox.getSelectedItem().toString());
                }
            }
        });
        /*
        *create panel for showing information about
        *vacation days
         */
        JPanel tablePanel = new JPanel();
        tablePanel.setBounds(10,80,400,50);
        tablePanel.setLayout(new GridLayout(2,12));
        //make array of Month
        String[] month = {"Янв","Фев","Мар","Апр","Май","Июн","Июл","Авг","Сен","Окт","Ноя","Дек"};
        JLabel[] jlMonth = new JLabel[12];
        for(int i=0;i<12;i++){
            jlMonth[i] = new JLabel(month[i]);
            tablePanel.add(jlMonth[i]);
        }
        JLabel jlText1 = new JLabel("Количество дней отпуска по месяцам:");
        jlText1.setBounds(10,50,300,30);
        jlText1.setFont(new Font("Times New Roman",Font.BOLD,15));
        //Take days from database
        days = DBcontroller.DBGetDays(nameComboBox.getSelectedItem().toString());
        for(int i=0;i<12;i++) {
            jtDays[i] = new JTextArea("" + days[i]);
            tablePanel.add(jtDays[i]);
        }
        /*
        add component on mainPanel
         */
        mainPanel.add(radioTable);
        mainPanel.add(jbtClose);mainPanel.add(jaLastName);
        mainPanel.add(jbtEdit);mainPanel.add(jbtRemove);
        mainPanel.add(jaName);mainPanel.add(jlName);
        mainPanel.add(jlLast);mainPanel.add(jlText1);
        mainPanel.add(nameComboBox);mainPanel.add(tablePanel);
        add(mainPanel);
    }
}
