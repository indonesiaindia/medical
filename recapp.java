package medical.management;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
public class recapp extends MouseAdapter{
    JFrame f;
    JScrollPane s;
    static String un;
    String name, smail;
    JButton b2,bin,bup,badd;
    Connection con;
    JButton bcon,bsel;
    static JTable tab;
    JTextField t1,t2;
    static DefaultTableModel tm;
    ImageIcon i = new ImageIcon(((new ImageIcon("doc.jpg")).getImage()).getScaledInstance(1366, 768, Image.SCALE_SMOOTH));
    Border border = BorderFactory.createLineBorder(Color.black, 1, true);
    recapp()
    {}
    recapp(String u)
    {
        un = u;
    }
    public void Interface() throws Exception
    {
        f = new JFrame("Doctor");
        f.setLayout(null);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setSize(600,400);
        
        JLabel l1 = new JLabel(" Medical Assistance System");
        l1.setBounds(80,25,1200,120);
        l1.setFont(new Font(Font.SERIF,Font.BOLD,100));
        l1.setForeground(Color.black);
        f.add(l1);
        l1.setBorder(BorderFactory.createMatteBorder(1,1,2, 2, Color.black));
        
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manish");
        PreparedStatement stmn = con.prepareStatement("select name from docsign where username = ?");
        stmn.setString(1,un);
        ResultSet rsn = stmn.executeQuery();
        rsn.next();
        name = rsn.getString(1);
        JLabel l2 = new JLabel("Welcome "+name+"!!");
        l2.setBounds(66,180,620,30);
        l2.setFont(new Font(Font.SERIF,Font.BOLD,30));
        f.add(l2);
        
        JLabel l3 = new JLabel("Recent Appointments: ");
        l3.setBounds(66,225,620,40);
        l3.setFont(new Font(Font.SERIF,Font.BOLD,30));
        f.add(l3);
        
        ImageIcon bi2 = new ImageIcon(((new ImageIcon("prev.png")).getImage()).getScaledInstance(50,50,Image.SCALE_SMOOTH));
        Insets in = new Insets(0,0,0,0);
        
        b2 = new JButton(bi2);
        b2.setMargin(new Insets(0,0,0,0));
        b2.setBounds(10,10,30,30);
        b2.setMargin(in);
        b2.setBorder(null);
        b2.setContentAreaFilled(false);
        b2.addMouseListener(this);
        f.add(b2);
        
        tab = new JTable();
        
        s = new JScrollPane(tab);
        s.setBounds(66,280, 1244, 373);
        
        tm = new DefaultTableModel(new Object[] {},0);
        tm.addTableModelListener(tab);
        tm.addColumn("Name");
        tm.addColumn("Age");
        tm.addColumn("Gender");
        tm.addColumn("Contact No.");
        tm.addColumn("Email ID");
        tm.addColumn("Address");
        tm.addColumn("Appointment Date");
        tm.addColumn("Time");
        tab.setModel(tm);
        
        tab.setSelectionMode(0);
                
        String name2, age, gender, mail, add, uname, phone,date,time;
        long p;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manish");
        PreparedStatement stm = con.prepareStatement("select * from recapp where docid = ?");
        stm.setString(1,un);
        ResultSet rs = stm.executeQuery();
        while(rs.next())
        {
            name2 = rs.getString(1);
            age = rs.getString(2);
            gender = rs.getString(3);
            p = rs.getLong(4);
            phone = String.valueOf(p);
            mail = rs.getString(5);
            add = rs.getString(6);
            date = rs.getString(7);
            time = rs.getString(8);
            uname = rs.getString(9);
            tm.addRow(new Object[] {name2,age,gender,phone,mail,add,date,time});
        }
        
        f.add(b2);
        f.add(s);

        tab.setBackground(new Color(0,0,0,200));
        tab.setForeground(Color.white);
        JLabel back = new JLabel(i);
        back.setBounds(0, 0, 1366, 768);
        f.add(back);
        
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //con.close();
    }
    public static void main(String args[])throws Exception
    {
        String str = "d.doc7";
        recapp p = new recapp(str);
        p.Interface();
    }

    @Override
    public void mousePressed(MouseEvent me)
    {
        if(me.getSource()==b2)
        {
             b2.setBounds(8,8,30,30);
        }
        
    }
    @Override
    public void mouseReleased(MouseEvent me)
    {
        if(me.getSource()==b2)
        {
            b2.setBounds(10,10,30,30);
            try {
                b2.setBounds(10,10,30,30);
                DocPage p = new DocPage(un);
                p.Data();
                p.Interface();
                f.dispose();
            } catch (Exception ex) {  }
            f.dispose();
        }
    }
    
}
