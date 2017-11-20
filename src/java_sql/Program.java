/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java_sql.Main.ActualPermission;
import static java_sql.Main.ActualUser;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import javax.swing.text.Position;

/**
 *
 * @author User
 */
public class Program extends javax.swing.JFrame {

    String username = Main.username;
    String password = Main.password;
    String url = Main.url;
    String ActualPermission = Main.ActualPermission;
    String ActualUser = Main.ActualUser;

    public final void update_table(String Zdarzenie, String użytkownik, String pozwolenie) {
        DefaultTableModel model = (DefaultTableModel) ZdarzeniaModel.getModel();
        model.addRow(new Object[]{użytkownik, Zdarzenie, pozwolenie});
    }

    final void Dodaj_Rzedy() {
    }

    final void AdminPermissionsList(String NAME) {
        try {

            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/GoogleData", "admin3", "admin3");
            Statement stmt = con.createStatement();

            String Query = "SELECT * FROM ADMINS WHERE NAME = '" + NAME + "'";
            ResultSet rs = stmt.executeQuery(Query);
            DefaultListModel DLM = new DefaultListModel();

            while (rs.next()) {
                DLM.addElement("Permission number. " + rs.getString(2));
                Main.ActualPermission = "2";
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    final void FillList() {
        try {

            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();

            String Query = "SELECT * FROM EMPLOYEE";
            ResultSet rs = stmt.executeQuery(Query);

            DefaultListModel DLM = new DefaultListModel();

            while (rs.next()) {
                DLM.addElement(rs.getString(1));
            }

            List1.setModel(DLM);

        } catch (SQLException ex) {
            setVisible(false);
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    final void FillList2() {
        try {

            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();

            String Query = "SELECT * FROM DEALS";
            ResultSet rs = stmt.executeQuery(Query);

            DefaultListModel DLM = new DefaultListModel();

            while (rs.next()) {
                DLM.addElement(rs.getString(1));
            }

            List2.setModel(DLM);

        } catch (SQLException ex) {
            setVisible(false);
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    final void List1ValueChanged() {
        try {

            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();

            String Query = "SELECT * FROM EMPLOYEE WHERE NAME = '" + List1.getSelectedValue() + "'";
            ResultSet rs = stmt.executeQuery(Query);
            while (rs.next()) {
                Name1.setText(rs.getString(1));
                BirthDay1.setSelectedItem(rs.getString(2));
                BirthMonth1.setSelectedItem(rs.getString(3));
                BirthYear1.setSelectedItem(rs.getString(4));
                MOBILE1.setText(rs.getString(5));
                Adress1.setText(rs.getString(6));
                Sex1.setSelectedItem(rs.getString(7));
                Department1.setSelectedItem(rs.getString(8));
                ID1.setText(rs.getString(9));

            }

        } catch (SQLException ex) {
            setVisible(false);
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    final void List2ValueChanged() {
        try {

            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();

            String Query = "SELECT * FROM DEALS WHERE NAME = '" + List2.getSelectedValue() + "'";
            ResultSet rs = stmt.executeQuery(Query);
            while (rs.next()) {
                ID2.setText(rs.getString(1));
                BirthDay2.setSelectedItem(rs.getString(2));
                BirthMonth2.setSelectedItem(rs.getString(3));
                BirthYear2.setSelectedItem(rs.getString(4));
                MOBILE2.setText(rs.getString(5));
                Adress2.setText(rs.getString(6));
                NIP.setText(rs.getString(7));
                Description.setText(rs.getString(8));
                Name2.setText(rs.getString(9));

            }

        } catch (SQLException ex) {
            setVisible(false);
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        // TODO add your handling code here:
    }

    final void JTabbedPaneMouseClicked() {
        EmployeePane.setSelectedIndex(0);
        DealPane.setSelectedIndex(0);
    }

    final void AddEmployee() {
        update_table(Main.ActualUser, "DODODANO DO BAZY [PRACOWNICY]= " + Name.getText(), Main.ActualPermission);

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            String Query = "INSERT INTO EMPLOYEE (NAME, BIRTHDAY, BIRTHMONTH, BIRTHYEAR, ID,MOBILE, ADDRESS, SEX, DEPARTMENT) VALUES ('" + Name.getText() + "', '" + BirthDay.getSelectedItem() + "', '" + BirthMonth.getSelectedItem() + "' , '" + BirthYear.getSelectedItem() + "' , '" + ID.getText() + "' ,'" + MOBILE.getText() + "', '" + Adress.getText() + "' ,'" + Sex.getSelectedItem() + "' ,'" + Department.getSelectedItem() + "' )";

            stmt.execute(Query);

            JOptionPane.showMessageDialog(null, "Pracownik dodany do bazy danych ! ");
            Name.setText(null);
            BirthDay.setSelectedItem(01);
            BirthMonth.setSelectedItem(01);
            BirthYear.setSelectedItem(0000);
            ID.setText(null);
            MOBILE.setText(null);
            Sex.setSelectedItem(null);
            Department.setSelectedItem(null);

            FillList();

        } catch (SQLException ex) {
            setVisible(false);
            JOptionPane.showMessageDialog(null, ex.toString());
        }

    }

    final void UpdateEmployee() {
        jPanel8.setVisible(false);
        update_table(Main.ActualUser, "ZMIENIONO DANE BAZY [PRACOWNICY]= " + Name1.getText(), Main.ActualPermission);

        try {

            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();

            String Query = "UPDATE EMPLOYEE SET NAME = '" + Name1.getText() + "', BIRTHDAY = '" + BirthDay1.getSelectedItem() + "', BIRTHMONTH = '" + BirthMonth1.getSelectedItem() + "', BIRTHYEAR = '" + BirthYear1.getSelectedItem() + "', ID = '" + ID1.getText() + "',MOBILE = '" + MOBILE1.getText() + "', ADDRESS = '" + Adress1.getText() + "', SEX = '" + Sex1.getSelectedItem() + "', DEPARTMENT = '" + Department1.getSelectedItem() + "' WHERE NAME = '" + List1.getSelectedValue() + "' ";
            stmt.execute(Query);

            JOptionPane.showMessageDialog(null, "Pracownik Zaktualizowany ! ");
            FillList();

        } catch (SQLException ex) {
            setVisible(false);
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    final void DeleteEmployee() {
        update_table(Main.ActualUser, "USUNIETO DANE BAZY [PRACOWNICY]= " + Name1.getText(), Main.ActualPermission);

        try {

            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();

            String Query = "DELETE FROM EMPLOYEE WHERE NAME = '" + List1.getSelectedValue() + "'";
            stmt.execute(Query);

            JOptionPane.showMessageDialog(null, "Pracownik usunięty z Bazy Danych ! ");
            FillList();

        } catch (SQLException ex) {
            setVisible(false);
            JOptionPane.showMessageDialog(null, ex.toString());
        }

        // TODO add your handling code here:
    }

    final void AddDeals() {

        try {

            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            String Query = "INSERT INTO DEALS (ID, CARDAY, CARMONTH, CARYEAR, MOBILE, ADDRESS, NIP, DESCRIPTION, NAME) VALUES ('" + ID3.getText() + "', '" + BirthDay3.getSelectedItem() + "', '" + BirthMonth3.getSelectedItem() + "' , '" + BirthYear3.getSelectedItem() + "' , '" + MOBILE3.getText() + "' ,'" + Adress3.getText() + "', '" + NIP2.getText() + "' ,'" + Description2.getText() + "' ,'" + Name3.getText() + "' )";

            stmt.execute(Query);

            JOptionPane.showMessageDialog(null, "Employee Added to database");
            Name3.setText(null);
            BirthDay3.setSelectedItem(01);
            BirthMonth3.setSelectedItem(01);
            BirthYear3.setSelectedItem(0000);
            ID3.setText(null);
            MOBILE3.setText(null);
            Description2.setText(null);
            Adress3.setText(null);
            NIP2.setText(null);
        } catch (SQLException ex) {
            setVisible(false);
            JOptionPane.showMessageDialog(null, ex.toString());
        }

        FillList2();
    }

    final void UpdateDeals() {
        update_table(ActualUser, "ZMIENIONO DANE [UMOWY]= " + Name2.getText(), Main.ActualPermission);
        try {

            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();

            String Query = "UPDATE DEALS SET NAME = '" + Name2.getText() + "', BIRTHDAY = '" + BirthDay2.getSelectedItem() + "', BIRTHMONTH = '" + BirthMonth2.getSelectedItem() + "', BIRTHYEAR = '" + BirthYear2.getSelectedItem() + "', ID = '" + ID2.getText() + "',MOBILE = '" + MOBILE2.getText() + "', ADDRESS = '" + Adress2.getText() + "', NIP = '" + NIP.getText() + "', DESCRIPTION = '" + Description.getText() + "' , ID = '" + ID2.getText() + "' WHERE NAME = '" + List2.getSelectedValue() + "' ";
            stmt.execute(Query);

            JOptionPane.showMessageDialog(null, "Pracownik Zauktualizowany ! ");
            FillList2();

        } catch (SQLException ex) {
            setVisible(false);
            JOptionPane.showMessageDialog(null, ex.toString());
        }

    }

    final void DeleteDeals() {
        update_table(ActualUser, "UMOWA USYNIĘTA Z BAZY [UMOWY]= " + List2.getSelectedValue(), Main.ActualPermission);

        try {

            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();

            String Query = "DELETE FROM DEAL WHERE NAME = '" + List2.getSelectedValue() + "'";
            stmt.execute(Query);

            JOptionPane.showMessageDialog(null, "Umowa usunięta z bazy danych ! ");
            FillList2();

        } catch (SQLException ex) {
            setVisible(false);
            JOptionPane.showMessageDialog(null, ex.toString());
        }

        // TODO add your handling code here:
    }

    /**
     * Creates new form NewJFrame
     */
    public Program() {

        initComponents();
        setLocationRelativeTo(null);
        //   setLocationRelativeTo(null);
        setVisible(true);

        for (int i = 1900; i <= 2016; i++) {
            
            BirthYear.addItem(Integer.toString(i));
            BirthYear1.addItem(Integer.toString(i));
            BirthYear2.addItem(Integer.toString(i));
            BirthYear3.addItem(Integer.toString(i));
        }

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        FillList();
        FillList2();
        //  setLocationRelativeTo(null);
        update_table(ActualUser, "ZALOGOWANO DO BAZY PRZEZ= " + Main.ActualUser, Main.ActualPermission);

        txtEmployee.getDocument().addDocumentListener(new DocumentListener() {

            void wykonaj() {
                int result = List1.getNextMatch(txtEmployee.getText(), 0, Position.Bias.Forward);
                List1.setSelectedIndex(result);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                wykonaj();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                wykonaj();
            }

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                wykonaj();

            }

        });

        txtEmployee1.getDocument().addDocumentListener(new DocumentListener() {

            void wykonaj() {
                int result = List1.getNextMatch(txtEmployee.getText(), 0, Position.Bias.Forward);
                List1.setSelectedIndex(result);
            }

            void wykonaj2() {
                int result = List2.getNextMatch(txtEmployee1.getText(), 0, Position.Bias.Forward);
                List2.setSelectedIndex(result);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                wykonaj();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                wykonaj();
            }

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                wykonaj();
            }

        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JTabbedPane = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        DealPane = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtEmployee1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        List2 = new javax.swing.JList<>();
        BirthDay2 = new javax.swing.JComboBox<>();
        BirthMonth2 = new javax.swing.JComboBox<>();
        BirthYear2 = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        NIP = new javax.swing.JTextField();
        Adress2 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        UpdateDeal = new javax.swing.JButton();
        DeleteDeal = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        MOBILE2 = new javax.swing.JTextField();
        Name2 = new javax.swing.JTextField();
        ID2 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        Description = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        Name3 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        BirthDay3 = new javax.swing.JComboBox<>();
        BirthMonth3 = new javax.swing.JComboBox<>();
        BirthYear3 = new javax.swing.JComboBox<>();
        NIP2 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        MOBILE3 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        AddDeal = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        Adress3 = new javax.swing.JTextField();
        ID3 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        Description2 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        EmployeePane = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        txtEmployee = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Name1 = new javax.swing.JTextField();
        BirthDay1 = new javax.swing.JComboBox<>();
        BirthMonth1 = new javax.swing.JComboBox<>();
        BirthYear1 = new javax.swing.JComboBox<>();
        ID1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        MOBILE1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        Adress1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        UpdateEmployee = new javax.swing.JButton();
        DeleteEmployee = new javax.swing.JButton();
        Department1 = new javax.swing.JComboBox<>();
        Sex1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        List1 = new javax.swing.JList<>();
        jLabel22 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Name = new javax.swing.JTextField();
        BirthDay = new javax.swing.JComboBox<>();
        BirthMonth = new javax.swing.JComboBox<>();
        BirthYear = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ID = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Adress = new javax.swing.JTextField();
        MOBILE = new javax.swing.JTextField();
        Sex = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Department = new javax.swing.JComboBox<>();
        AddEmployee = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ZdarzeniaModel = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ZdarzeniaModel1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("WARSZTAT - zalogowano jako " + username + " dostępny poziom uprawnień użytkownika = " + ActualPermission);

        JTabbedPane.setFont(new java.awt.Font("Sitka Small", 0, 18)); // NOI18N
        setVisible(true);
        JTabbedPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTabbedPaneMouseClicked(evt);
            }
        });

        DealPane.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel15.setText("Wyszukaj");

        List2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        List2.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                List2ValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(List2);

        BirthDay2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        BirthDay2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BirthDay2ActionPerformed(evt);
            }
        });

        BirthMonth2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel17.setText("Numer Identyfikacyjny");

        jLabel18.setText("Telefon");

        NIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NIPActionPerformed(evt);
            }
        });

        jLabel19.setText("Adress");

        jLabel21.setText("NIP");

        jLabel23.setText("Data Zawarcia");

        UpdateDeal.setText("Zaktualizuj");
        UpdateDeal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateDealActionPerformed(evt);
            }
        });

        DeleteDeal.setText("Usuń");
        DeleteDeal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteDealActionPerformed(evt);
            }
        });

        jLabel30.setText("Opis");

        MOBILE2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MOBILE2ActionPerformed(evt);
            }
        });

        jLabel32.setText("Imię Naziwsko");

        Description.setColumns(20);
        Description.setRows(5);
        jScrollPane5.setViewportView(Description);
        Description.setLineWrap(true);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(UpdateDeal, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DeleteDeal, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(txtEmployee1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(40, 40, 40)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(MOBILE2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NIP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Adress2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                                .addGap(179, 179, 179)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Name2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ID2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(jScrollPane5)))
                        .addGap(320, 320, 320))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BirthDay2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BirthMonth2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BirthYear2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(325, 325, 325))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addGap(16, 16, 16)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmployee1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(ID2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Name2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(BirthDay2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BirthMonth2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BirthYear2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UpdateDeal, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeleteDeal, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NIP, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(MOBILE2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(Adress2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71))))))
        );

        DealPane.addTab("Znajdz ", jPanel7);

        jPanel6.setToolTipText("Warsztat");
        jPanel6.setName("Warsztat"); // NOI18N

        jLabel24.setText("Opis");

        BirthDay3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        BirthDay3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BirthDay3ActionPerformed(evt);
            }
        });

        BirthMonth3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        NIP2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NIP2ActionPerformed(evt);
            }
        });

        jLabel25.setText("Numer Identyfikacyjny");

        jLabel26.setText("Data zawarcia");

        jLabel27.setText("Mobile");

        MOBILE3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MOBILE3ActionPerformed(evt);
            }
        });

        jLabel28.setText("Adres");

        AddDeal.setText("Dodaj Umowę");
        AddDeal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddDealActionPerformed(evt);
            }
        });

        jLabel29.setText("Imię Naziwsko");

        jLabel31.setText("NIP");

        Description2.setColumns(20);
        Description2.setRows(5);
        jScrollPane6.setViewportView(Description2);
        Description2.setLineWrap(true);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(AddDeal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(jLabel31)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                        .addComponent(BirthDay3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(BirthMonth3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(BirthYear3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(MOBILE3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                                    .addComponent(NIP2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Name3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(ID3)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(280, 280, 280)
                                .addComponent(jScrollPane6))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Adress3, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(474, 474, 474))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ID3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Name3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BirthDay3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BirthMonth3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BirthYear3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NIP2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MOBILE3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(AddDeal, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Adress3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 91, Short.MAX_VALUE)))
                .addContainerGap())
        );

        DealPane.addTab("Dodaj ", jPanel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DealPane)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(DealPane, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        JTabbedPane.addTab("UMOWY", jPanel1);

        EmployeePane.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmployeeActionPerformed(evt);
            }
        });

        jLabel8.setText("Wyszukaj");

        jLabel9.setText("Imię Naziwsko");

        Name1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Name1ActionPerformed(evt);
            }
        });

        BirthDay1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        BirthDay1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BirthDay1ActionPerformed(evt);
            }
        });

        BirthMonth1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel10.setText("Numer ID");

        MOBILE1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MOBILE1ActionPerformed(evt);
            }
        });

        jLabel11.setText("Telefon");

        jLabel12.setText("Adres");

        jLabel13.setText("Płeć");

        jLabel14.setText("Stanowisko");

        UpdateEmployee.setText("Zaktualizuj");
        UpdateEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateEmployeeActionPerformed(evt);
            }
        });

        DeleteEmployee.setText("Usuń");
        DeleteEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteEmployeeActionPerformed(evt);
            }
        });

        Department1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IT", "CS", "IS", "HR" }));
        Department1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Department1ActionPerformed(evt);
            }
        });

        Sex1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mężczyzna", "Kobieta" }));
        Sex1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sex1ActionPerformed(evt);
            }
        });

        List1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        List1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                List1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(List1);

        jLabel22.setText("Data Urodzenia");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(Name1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(59, 59, 59)
                                        .addComponent(BirthDay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BirthMonth1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(BirthYear1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(372, 372, 372))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(302, 302, 302))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(27, 27, 27))
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel14)
                                                .addGap(49, 49, 49)))
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(Sex1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Department1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Adress1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                                                .addGap(28, 28, 28)))
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ID1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(MOBILE1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(368, 368, 368))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(UpdateEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(DeleteEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Name1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BirthDay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BirthMonth1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BirthYear1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ID1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(MOBILE1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Adress1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Sex1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Department1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(88, 88, 88)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(UpdateEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DeleteEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(92, 92, 92))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(11, 11, 11)
                        .addComponent(txtEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))))
        );

        EmployeePane.addTab("Znajdz ", jPanel5);

        jLabel1.setText("Imię");

        Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameActionPerformed(evt);
            }
        });

        BirthDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        BirthDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BirthDayActionPerformed(evt);
            }
        });

        BirthMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel2.setText("Data Urodzenia");

        jLabel3.setText("Numer ID");

        jLabel4.setText("Telefon");

        jLabel5.setText("Adres");

        MOBILE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MOBILEActionPerformed(evt);
            }
        });

        Sex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mężczyzna", "Kobieta" }));
        Sex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SexActionPerformed(evt);
            }
        });

        jLabel6.setText("Płeć");

        jLabel7.setText("Stanowisko");

        Department.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IT", "CS", "IS", "HR" }));
        Department.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DepartmentActionPerformed(evt);
            }
        });

        AddEmployee.setText("Dodaj Pracownika");
        AddEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddEmployeeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(3, 3, 3))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(BirthDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BirthMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BirthYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(MOBILE, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                    .addComponent(Adress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(77, 77, 77)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Sex, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Department, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(AddEmployee, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ID, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Name, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(589, 589, 589))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BirthDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BirthMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BirthYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MOBILE, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Adress, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(39, 39, 39)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Sex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Department, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(AddEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(159, Short.MAX_VALUE))
        );

        EmployeePane.addTab("Dodaj ", jPanel4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(EmployeePane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(EmployeePane, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        JTabbedPane.addTab("PRACOWNICY", jPanel2);

        ZdarzeniaModel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Użytkownik", "Zdarzenie", "Pozwolenie"
            }
        ));
        jScrollPane3.setViewportView(ZdarzeniaModel);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1301, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(202, Short.MAX_VALUE))
        );

        JTabbedPane.addTab("ZDARZENIA", jPanel3);

        ZdarzeniaModel1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Użytkownik", "Zdarzenie", "Pozwolenie"
            }
        ));
        jScrollPane4.setViewportView(ZdarzeniaModel1);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1301, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(202, Short.MAX_VALUE))
        );

        JTabbedPane.addTab("ADMINISTRATORZY", jPanel8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JTabbedPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(JTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void List1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_List1ValueChanged
        List1ValueChanged();
    }//GEN-LAST:event_List1ValueChanged

    private void Sex1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sex1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Sex1ActionPerformed

    private void Department1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Department1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Department1ActionPerformed

    private void DeleteEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteEmployeeActionPerformed
        DeleteEmployee();
    }//GEN-LAST:event_DeleteEmployeeActionPerformed

    private void UpdateEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateEmployeeActionPerformed
        UpdateEmployee();
    }//GEN-LAST:event_UpdateEmployeeActionPerformed

    private void MOBILE1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MOBILE1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MOBILE1ActionPerformed

    private void BirthDay1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BirthDay1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BirthDay1ActionPerformed

    private void Name1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Name1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Name1ActionPerformed

    private void AddEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddEmployeeActionPerformed
        AddEmployee();
    }//GEN-LAST:event_AddEmployeeActionPerformed

    private void DepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DepartmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DepartmentActionPerformed

    private void SexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SexActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SexActionPerformed

    private void MOBILEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MOBILEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MOBILEActionPerformed

    private void BirthDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BirthDayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BirthDayActionPerformed

    private void NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NameActionPerformed

    private void NIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NIPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NIPActionPerformed

    private void BirthDay2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BirthDay2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BirthDay2ActionPerformed

    private void List2ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_List2ValueChanged
        List2ValueChanged();
    }//GEN-LAST:event_List2ValueChanged

    private void AddDealActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddDealActionPerformed
        AddDeals();
    }//GEN-LAST:event_AddDealActionPerformed

    private void MOBILE3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MOBILE3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MOBILE3ActionPerformed

    private void BirthDay3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BirthDay3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BirthDay3ActionPerformed

    private void UpdateDealActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateDealActionPerformed
        UpdateDeals();
    }//GEN-LAST:event_UpdateDealActionPerformed

    private void DeleteDealActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteDealActionPerformed
        DeleteDeals();
    }//GEN-LAST:event_DeleteDealActionPerformed

    private void JTabbedPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTabbedPaneMouseClicked
        // TODO add your handling code here:
        JTabbedPaneMouseClicked();


    }//GEN-LAST:event_JTabbedPaneMouseClicked

    private void txtEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmployeeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmployeeActionPerformed

    private void NIP2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NIP2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NIP2ActionPerformed

    private void MOBILE2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MOBILE2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MOBILE2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Program.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Program.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Program.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Program.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Program().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddDeal;
    private javax.swing.JButton AddEmployee;
    private javax.swing.JTextField Adress;
    private javax.swing.JTextField Adress1;
    private javax.swing.JTextField Adress2;
    private javax.swing.JTextField Adress3;
    private javax.swing.JComboBox<String> BirthDay;
    private javax.swing.JComboBox<String> BirthDay1;
    private javax.swing.JComboBox<String> BirthDay2;
    private javax.swing.JComboBox<String> BirthDay3;
    private javax.swing.JComboBox<String> BirthMonth;
    private javax.swing.JComboBox<String> BirthMonth1;
    private javax.swing.JComboBox<String> BirthMonth2;
    private javax.swing.JComboBox<String> BirthMonth3;
    private javax.swing.JComboBox<String> BirthYear;
    private javax.swing.JComboBox<String> BirthYear1;
    private javax.swing.JComboBox<String> BirthYear2;
    private javax.swing.JComboBox<String> BirthYear3;
    private javax.swing.JTabbedPane DealPane;
    private javax.swing.JButton DeleteDeal;
    private javax.swing.JButton DeleteEmployee;
    private javax.swing.JComboBox<String> Department;
    private javax.swing.JComboBox<String> Department1;
    private javax.swing.JTextArea Description;
    private javax.swing.JTextArea Description2;
    private javax.swing.JTabbedPane EmployeePane;
    private javax.swing.JTextField ID;
    private javax.swing.JTextField ID1;
    private javax.swing.JTextField ID2;
    private javax.swing.JTextField ID3;
    private javax.swing.JTabbedPane JTabbedPane;
    private javax.swing.JList<String> List1;
    private javax.swing.JList<String> List2;
    private javax.swing.JTextField MOBILE;
    private javax.swing.JTextField MOBILE1;
    private javax.swing.JTextField MOBILE2;
    private javax.swing.JTextField MOBILE3;
    private javax.swing.JTextField NIP;
    private javax.swing.JTextField NIP2;
    private javax.swing.JTextField Name;
    private javax.swing.JTextField Name1;
    private javax.swing.JTextField Name2;
    private javax.swing.JTextField Name3;
    private javax.swing.JComboBox<String> Sex;
    private javax.swing.JComboBox<String> Sex1;
    private javax.swing.JButton UpdateDeal;
    private javax.swing.JButton UpdateEmployee;
    private javax.swing.JTable ZdarzeniaModel;
    private javax.swing.JTable ZdarzeniaModel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextField txtEmployee;
    private javax.swing.JTextField txtEmployee1;
    // End of variables declaration//GEN-END:variables
}
