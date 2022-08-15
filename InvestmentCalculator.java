package question2.fa1q2;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;

class InvestmentPanel extends JPanel
{
        //declare textfields for customer name & amount
    private final JTextField customerName;
    private final JTextField amount;
        //declare combobox for type of investment (moderate/aggressive)
    private final JComboBox type;
        //declare radio button for investment years
    private final JRadioButton term_5years;
    private final JRadioButton term_10years;
    private final JRadioButton term_15years;
        //radio button group to hold each above radio buttons
    private final ButtonGroup group;
        //vector types for adding investment types
    private final Vector<Object> types = new Vector<>();

    //constructor
    public InvestmentPanel(){
        //create custome name & amount textfields
        customerName = new JTextField(20);
        amount = new JTextField(20);
        //add Moderate & Aggressive string to types vector object
        types.add("Moderate ");
        types.add("Aggressive ");
        //create combo box with types
        type = new JComboBox(types);
        //create radio buttons
        term_5years = new JRadioButton("5 Years");
        term_10years = new JRadioButton("10 Years");
        term_15years = new JRadioButton("15 Years");
        group = new ButtonGroup();

    }

    //returns this object by adding each created components
    //to this panel object
    public InvestmentPanel createInvestmentPanel(){
        //set default 5 years radio button selected to true
        term_5years.setSelected(true);
        //add each radio button to button group
        group.add(term_5years);
        group.add(term_10years);
        group.add(term_15years);

        //set flow layout for all components on panel
        setLayout(new FlowLayout(10));
        //add customer name text field with label
        add(new JLabel(" Customer Name: "));
        add(customerName);
        //add amount text field with label
        add(new JLabel(" Enter Amount: "));
        add(amount);
        //add combobox with label
        add(new JLabel(" Select Type: "));
        add(type);
        //add radio button group with label
        add(new JLabel(" Select Term: "));
        add(term_5years);
        add(term_10years);
        add(term_15years);
        return this;
    }

    //getter methods
    public String getCustomerName(){
        return customerName.getText();
    }

    public String getAmount(){
        return amount.getText();
    }

    public String getType(){
        return type.getSelectedItem().toString().trim();
    }

    public int getTerm(){
        if(term_5years.isSelected())
        return 5;
        else if(term_10years.isSelected())
        return 10;
        else
        return 15;
    }

    //setter methods
    public void setCustomerName(String s){
        customerName.setText(s);
    }

    public void setAmount(String s){
        amount.setText(s);
    }

    public void setType(int i){
        type.setSelectedIndex(i);
        }
    }

    /*
    InvestmentFrame class inherits from JFrame
    add InvestmentPanel on it
    */
    class InvestmentFrame extends JFrame
    {
        //declare menubar and its menus File, Tools
    private final JMenuBar menuBar;
    private final JMenu fileMenu;
    private final JMenu toolsMenu;
        //declare menu items File->Exit, Tools->Calculate, Tools->Clear
    private final JMenuItem file_exitMenuItem;
    private final JMenuItem tools_calculateMenuItem;
    private final JMenuItem tools_clearMenuItem;
        //declare InvestmentPanel object
    private InvestmentPanel investmentPanel;

    //constructor
    public InvestmentFrame(){
        //initialize all above declared objects
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        toolsMenu = new JMenu("Tools");
        file_exitMenuItem = new JMenuItem("Exit");
        tools_calculateMenuItem = new JMenuItem("Calculate");
        tools_clearMenuItem = new JMenuItem("Clear");

        //add each menu item to specific menu
        fileMenu.add(file_exitMenuItem);

        toolsMenu.add(tools_calculateMenuItem);
        toolsMenu.add(tools_clearMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);

    }

    public void showFrame(){
        //create investment panel object
        investmentPanel = new InvestmentPanel();
        //create MenuItemsAction object by passing investmentPanel object to it
        MenuItemsAction act = new MenuItemsAction(investmentPanel);
        //add action listener to each menu item
        file_exitMenuItem.addActionListener(act);
        tools_calculateMenuItem.addActionListener(act);
        tools_clearMenuItem.addActionListener(act);

        //set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(menuBar);
        getContentPane().add(investmentPanel.createInvestmentPanel());
        setTitle("Investment Calculator");
        setSize(405, 300);
        setLocation(300, 200);
        setVisible(true);
    }
    }

    //action class that inherits ActionListener
    class MenuItemsAction implements ActionListener
    {
    private InvestmentPanel investmentPanel;

        //get the InvestmentPanel object for accessing its
        //properties like customer name, amount
    public MenuItemsAction(InvestmentPanel panel){
        investmentPanel = panel;
    }

    @Override
        public void actionPerformed(ActionEvent evt){
            if(evt.getSource() instanceof JMenuItem){
                //perform action according to the menu item selected
                String item = evt.getActionCommand().trim();
            switch(item){
                //File->Exit menu item action
            case "Exit" : System.exit(0); break;
                //Tools->Calculate menu item action
            case "Calculate" :
                calculate();
            break;
                //Tools->Clear menu item action
            case "Clear" :
                clear();
            break;
                    }
                }
            }

    //calculate investment amount
    private void calculate(){
        //get amount from customer name text field
    double amount = Double.parseDouble(investmentPanel.getAmount());
        //get intereset rate from type
    double interest = 0.10;
        String type = investmentPanel.getType();
        if(type.equals("Moderate"))
            interest = 0.10;
        else if(type.equals("Aggressive"))
            interest = 0.15;

    //get investment years
    int years = investmentPanel.getTerm();

    double investment = amount * Math.pow(1 + (interest / 1), 1 * years);
    double compound_interest = investment - amount;

        String str = "INVESTMENT REPORT\n"
        + "\nCUSTOMER NAME: " + investmentPanel.getCustomerName()
        + "\nORIGINAL AMOUNT: " + investmentPanel.getAmount()
        + "\nYEARS Invested: " + years
        + "\nFINAL AMOUNT: R " + String.format("%.02f",investment);

    //show message box with all the details
    JOptionPane.showMessageDialog(null, str);
    }

    //clear all text fields
    private void clear(){
        investmentPanel.setCustomerName("");
        investmentPanel.setAmount("");
        investmentPanel.setType(0);
    }
    }

    //main class
    public class InvestmentCalculator
    {
    public static void main(String[] args) {
        //create investment frame and show frame
        InvestmentFrame invf = new InvestmentFrame();
        invf.showFrame();
    }
}
