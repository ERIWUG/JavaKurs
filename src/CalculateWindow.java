import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
/**
 * @author E.V. Belyi
 * @version 1.0
 *
 * Class that run mainWindow.
 */
public class CalculateWindow extends JFrame{
    private JFrame mainFrame;
    private JPanel tablePanel = new JPanel();
    private JRadioButton jrbYes = new JRadioButton("Оклад постоянный");
    private JRadioButton jrbNo = new JRadioButton("Оклад изменяется");
    private JTextArea[] taSal = new JTextArea[12];
    private JLabel jlSal = new JLabel("Sal");
    private String qual;
    private int sal;
    private JComboBox nameComboBox;
    private JLabel jk = new JLabel("Введите оклад:");
    private JTextArea jta = new JTextArea();
    private JPanel GetRadioPanel(){
        JPanel radioTable = new JPanel();
        radioTable.setLayout(new GridLayout(2,1));
        radioTable.setBounds(430,10,200,70);
        jrbYes.setSelected(true);
        jrbNo.addActionListener(new Listener());
        jrbYes.addActionListener(new Listener());
        radioTable.add(jrbYes); radioTable.add(jrbNo);
        return radioTable;
    }

    /**
     *Create mainMenu instance.
     */
    public CalculateWindow(){
        setSize(600,420);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
        /*
        Setting mainWindow
         */
        setResizable(false);
        /*
        get instance of mainWindow in mainFrame
         */
        mainFrame = this;



        /*
         * take data from db
         */
        String[] names = DBcontroller.DBGetNames();
        String[] month = {"Янв","Фев","Мар","Апр","Май","Июн","Июл","Авг","Сен","Окт","Ноя","Дек"};
        /*
        *make Menu bar
         */
        JMenuBar menuBar = new JMenuBar();
        JMenu help = new JMenu("Help");
        JMenuItem author = new JMenuItem("Об авторе");

        JMenuItem helpp = new JMenuItem("Об программе");
        help.add(author);help.add(helpp);
        menuBar.add(help);
        setJMenuBar(menuBar);


        /*
        *add listener for author block that create UserFrame
         */
        author.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                AboutAuthor g = new AboutAuthor(mainFrame);
            }
        });
        /*
        add listener for helpp block which create ProgramFrame
         */
        helpp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                AboutWindow g = new AboutWindow(mainFrame);
            }
        });



        /*
        Create and set mainPanel of mainWindow
         */
        JPanel mainPanel  = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(0,0,600,400);

        /*
        *add Window listener for repaint nameComboBox
        *when window Activate
         */
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                super.windowActivated(e);
                mainPanel.remove(nameComboBox);
                nameComboBox = new JComboBox(DBcontroller.DBGetNames());
                nameComboBox.setBounds(10,10,150,30);
                mainPanel.add(nameComboBox);
                revalidate();
                repaint();
            }
        });
        /*
        Create radioTable for radioButton
         */
        JPanel radioTable = GetRadioPanel();

        /*
        *create array of JLabel for paint tablePanel
        *tablePanel - table of payments and salarys
         */
        JLabel[] labelMonth = new JLabel[12];
        tablePanel.setLayout(new GridLayout(3,13));
        tablePanel.setBounds(10,80,500,100);
        tablePanel.add(new JLabel("/"));
        /*
        create array of Month labels
         */
        for(int i=0;i<12;i++){
            labelMonth[i] = new JLabel(month[i]);
            tablePanel.add(labelMonth[i]);
        }
        tablePanel.add(new JLabel("Pay"));
        /*
        create array of textArea for payments
         */
        JTextArea[] taPay = new JTextArea[12];
        for(int i=0;i<12;i++){
            taPay[i] = new JTextArea();
            taPay[i].setText("100");
            tablePanel.add(taPay[i]);
        }
        jlSal.setVisible(false);
        tablePanel.add(jlSal);
        /*
        create array of textarea for salarys
         */
        for(int i=0;i<12;i++){
            taSal[i] = new JTextArea();taSal[i].setText("100");
            taSal[i].setVisible(false);tablePanel.add(taSal[i]);
        }
        /*
        create ComboBox for showing staff
         */
        nameComboBox = new JComboBox(names);
        nameComboBox.setBounds(10,10,150,30);
        nameComboBox.setSelectedIndex(0);
        /*
        create monthComboBox for request user to choose current month
         */
        JComboBox monthComboBox = new JComboBox(month);
        monthComboBox.setBounds(10,250,150,30);
        monthComboBox.setSelectedIndex(0);
        /*
        create label and textarea for static salary
         */
        jk.setBounds(170,10,100,30);
        jta.setBounds(280,10,100,25);

        /*
        create textarea for showing answer of calculating
         */
        JTextArea jaAns = new JTextArea("0");
        jaAns.setBounds(170,250,150,30);
        jaAns.setFont(new Font("Times New Roman",0,24));


        /*
        create buttons for calculation, exit and edit
         */
        JButton jbtCalc = new JButton("Calculate");
        jbtCalc.setBounds(10,320,100,30);
        JButton jbtEditDB = new JButton("Edit Data");
        jbtEditDB.setBounds(120,320,100,30);
        JButton jbtClose = new JButton("Close");
        jbtClose.setBounds(480,320,100,30);
        /*
        add listener for close button which close the app
         */
        jbtClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
         /*
        add listener for edit button which create AddFrame
         */
        jbtEditDB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                JFrame b = new AddFrame(mainFrame);
                b.setLocation(getLocation());
                b.setSize(getSize());
                b.setLayout(null);
                b.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                b.setVisible(true);
            }
        });
        /*
        add listener for calculation which perform calculation
         */
        jbtCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double[] a = new double[12];
                double sum = 0;
                double m_okl=Double.parseDouble(jta.getText());
                /*
                get days from database
                 */
                int days = DBcontroller.DBGetDay(nameComboBox.getSelectedItem().toString(),monthComboBox.getSelectedIndex()+2);
                if(jrbYes.isSelected()){
                    for(int i=0;i<12;i++){
                        try {
                            a[i] = Double.parseDouble(taPay[i].getText());
                        }
                        catch(NumberFormatException formatException){
                            a[i] = 0;
                        }
                        sum+=a[i];
                    }
                    sum/=12*29.7;
                    jaAns.setText(String.format("%.6f",sum*days));
                }
                else{
                    for(int i=0;i<12;i++){
                        try{
                        if(Double.parseDouble(taSal[i].getText())>m_okl){
                            m_okl = Double.parseDouble(taSal[i].getText());
                        }}
                        catch(NumberFormatException formatException){

                        }
                    }
                    for(int i=0;i<12;i++){
                        try {
                            sum += Double.parseDouble(taPay[i].getText()) * (m_okl / Double.parseDouble(taSal[i].getText()));
                        }
                        catch (NumberFormatException formatException){}
                    }
                    sum/=12*29.7;
                    jaAns.setText(String.format("%.6f",sum*days));
                }
            }
        });

        /*
        initialization of component
         */
        mainPanel.add(monthComboBox);
        mainPanel.add(jaAns);
        mainPanel.add(jbtCalc);mainPanel.add(jbtEditDB);
        mainPanel.add(jbtClose);
        mainPanel.add(nameComboBox);
        mainPanel.add(tablePanel);
        mainPanel.add(radioTable);
        mainPanel.add(jta);mainPanel.add(jk);
        add(mainPanel);
    }
    /*
    *Listener for radioButton which check validation of buttons
    *and set elements visible
     */
    class Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if (e.getSource() == jrbYes){

                jrbNo.setSelected(!jrbYes.isSelected());
            }
            else{
                jlSal.setVisible(false);
                jrbYes.setSelected(!jrbNo.isSelected());
            }

            if(jrbNo.isSelected()){
                for(int i=0;i<12;i++){
                    taSal[i].setVisible(true);
                }
                jlSal.setVisible(true);
                jta.setVisible(false);
                jk.setVisible(false);
            }
            else{
                for(int i=0;i<12;i++){
                    taSal[i].setVisible(false);
                }
                jta.setVisible(true);
                jk.setVisible(true);
                jlSal.setVisible(false);
            }
        }
    }
}