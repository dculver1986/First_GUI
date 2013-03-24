/**
 *
 * @author Daniel
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.util.regex.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;
import java.io.LineNumberReader;
import java.util.Vector;

public class ShirtopiaFinal extends JFrame {

    private static final int MWA_WIDTH = 375;
    private static final int MWA_HEIGHT = 500;
    private JTextField nameTF;
    private JTextField addressTF;
    private JTextField cityTF;
    private JTextField stateTF;
    private JTextField zipTF;
    private JTextField phoneTF;
    private JTextField orderTF;
    private JTextField sizeTF;
    private JTextField quantityTF;

    /**
     * Constructor -- set up the JFrame title and the GUI
     */
    public ShirtopiaFinal() {
        setTitle("Shirtopia Ordering System");
        setupGUI();
    }

    /**
     * setupGUI lays out the GUI
     */
    private void setupGUI() {
        setLayout(null);


        /**
         * Set the title of the application (in the title bar)
         */
        JLabel titleLbl = new JLabel("**Welcome to Shirtopia**");
        titleLbl.setBounds(110, 25, 300, 20);
        add(titleLbl);

        //name text field label
        JLabel nameLbl = new JLabel("Your Name:");
        nameLbl.setBounds(70, 70, 100, 20);
        add(nameLbl);
        //name text field
        nameTF = new JTextField(50);
        nameTF.setBounds(140, 70, 155, 20);
        add(nameTF);


        //address text field label
        JLabel addressLbl = new JLabel("Address:");
        addressLbl.setBounds(70, 100, 100, 20);
        add(addressLbl);
        //address text field
        addressTF = new JTextField(50);
        addressTF.setBounds(140, 100, 155, 20);
        add(addressTF);

        //city text field label
        JLabel cityLbl = new JLabel("City:");
        cityLbl.setBounds(70, 130, 100, 20);
        add(cityLbl);
        //city text field
        cityTF = new JTextField(50);
        cityTF.setBounds(140, 130, 155, 20);
        add(cityTF);

        //state text field label
        JLabel stateLbl = new JLabel("State:");
        stateLbl.setBounds(70, 160, 100, 20);
        add(stateLbl);
        //state text field
        stateTF = new JTextField(50);
        stateTF.setBounds(140, 160, 155, 20);
        add(stateTF);

        //zip text field label
        JLabel zipLbl = new JLabel("Zip:");
        zipLbl.setBounds(70, 190, 100, 20);
        add(zipLbl);
        //zip text field
        zipTF = new JTextField(50);
        zipTF.setBounds(140, 190, 155, 20);
        add(zipTF);

        //phone text field label
        JLabel phoneLbl = new JLabel("Phone:");
        phoneLbl.setBounds(70, 220, 100, 20);
        add(phoneLbl);
        //phone text field
        phoneTF = new JTextField(50);
        phoneTF.setBounds(140, 220, 155, 20);
        add(phoneTF);

        //order text field label
        JLabel orderLbl = new JLabel("Order- Polo/T-Shirt/V-neck:");
        orderLbl.setBounds(70, 250, 155, 20);
        add(orderLbl);
        //order text field
        orderTF = new JTextField(50);
        orderTF.setBounds(140, 280, 155, 20);
        add(orderTF);

        //order text field label
        JLabel sizeLbl = new JLabel("Size- Small/Medium/Large:");
        sizeLbl.setBounds(70, 310, 180, 20);
        add(sizeLbl);
        //order text field
        sizeTF = new JTextField(50);
        sizeTF.setBounds(140, 340, 155, 20);
        add(sizeTF);

        //quantity text field label
        JLabel quantityLbl = new JLabel("Quantity- Enter a number 1 - 99:");
        quantityLbl.setBounds(70, 370, 180, 20);
        add(quantityLbl);
        //quantity text field
        quantityTF = new JTextField(50);
        quantityTF.setBounds(140, 400, 155, 20);
        add(quantityTF);


        //submit button
        JButton submitBtn = new JButton("Submit");
        submitBtn.setBounds(70, 435, 80, 22);
        submitBtn.addActionListener(new SubmitListener());
        add(submitBtn);
        //exit button
        JButton exitBtn = new JButton("Quit");
        exitBtn.setBounds(240, 435, 80, 22);
        exitBtn.addActionListener(new QuitListener());
        add(exitBtn);
    }

    public static void main(String[] args) {

        ShirtopiaFinal frame = new ShirtopiaFinal();
        frame.setSize(MWA_WIDTH, MWA_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);







        // TODO code application logic here
    }

    private class QuitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        } // end actionPerformed()
    } // end class QuitListener

    private class SubmitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            PrintWriter pw = null;

            try {                              // true for 'append'
                pw = new PrintWriter(new BufferedWriter(
                        new FileWriter("file.txt", false)));
            } catch (IOException a) {
                JOptionPane.showMessageDialog(null,
                        "Cannot create/write file: " + "file.txt",
                        "File create/write failure:" + a.getMessage(), JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }

            String nameErrorMsg = "Wrong. Please Enter Your Name.";
            String name = nameTF.getText().trim();
            String addressErrorMsg = "Wrong. Please Enter Your Address.";
            String address = addressTF.getText().trim();
            String cityErrorMsg = "Wrong. Please Enter Your City.";
            String city = cityTF.getText().trim();
            String stateErrorMsg = "Wrong. Please Enter Valid State.";
            String state = stateTF.getText().trim();
            String statePattern = "^[a-zA-Z][a-zA-Z]$";
            String zip = zipTF.getText().trim();
            String zipErrorMsg = "Wrong. Please Enter a Valid 5 Digit Zip.";
            String zipPattern = "^(\\d{5})$";
            String phone = phoneTF.getText().trim();
            String phoneErrorMsg = "Wrong. Please Enter a Valid Phone Number. (xxx) xxx-xxxx.";
            String phonePattern = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
            String orderErrorMsg = "Wrong. Please Enter an Order.";
            String order = orderTF.getText().trim();
            String size = sizeTF.getText().trim();
            String sizeErrorMsg = "Wrong. Please Enter a Size.";
            String quantity = quantityTF.getText().trim();
            String quantityErrorMsg = "Wrong. Please Enter a Valid Quantity 1 - 99";
            String quantityPattern = "^(0?[1-9]|[1-9]\\d)$";

            String errorMsg = "";
            if (name.equals("")) {
                errorMsg += nameErrorMsg + "\n";

            } else {
                pw.println(name);

            }
            if (address.equals("")) {
                errorMsg += addressErrorMsg + "\n";
            } else {
                pw.println(address);

            }
            if (city.equals("")) {
                errorMsg += cityErrorMsg + "\n";
            } else {
                pw.println(city);

            }
            if (!state.matches(statePattern)) {
                errorMsg += stateErrorMsg + "\n";
            } else {
                pw.println(state);

            }
            if (!zip.matches(zipPattern)) {
                errorMsg += zipErrorMsg + "\n";
            } else {
                pw.println(zip);

            }
            if (!phone.matches(phonePattern)) {
                errorMsg += phoneErrorMsg + "\n";
            } else {
                pw.println(phone);

            }
            if (order.equals("")) {
                errorMsg += orderErrorMsg + "\n";
            } else {
                pw.println(order);

            }
            if (size.equals("")) {
                errorMsg += sizeErrorMsg + "\n";
            } else {
                pw.println(size);

            }
            if (!quantity.matches(quantityPattern)) {
                errorMsg += quantityErrorMsg + "\n";
            } else {
                pw.println(quantity);

            }
            pw.flush();
            pw.close();



            if (!errorMsg.equals("")) {

                JOptionPane.showMessageDialog(null, errorMsg);
            } else {
                BufferedReader br;
                String line = "";
                try {
                    br = new BufferedReader(new FileReader("file.txt"));
                    StringBuffer sb = new StringBuffer("");

                    // instead of: while((line = br.readLine()) != null) 
                    String customerConfirm = null;
                    if ((customerConfirm = br.readLine()) != null) {
                        sb.append("customer name: ").append(customerConfirm).append("\n");


                        String addressConfirm = "";
                        if ((addressConfirm = br.readLine()) != "") {
                            sb.append("address: ").append(addressConfirm).append("\n");

                        }

                        String cityConfirm = null;
                        if ((cityConfirm = br.readLine()) != null) {
                            sb.append("city: ").append(cityConfirm).append("\n");

                        }
                        String stateConfirm = null;
                        if ((stateConfirm = br.readLine()) != null) {
                            sb.append("state: ").append(stateConfirm).append("\n");

                        }
                        String zipConfirm = null;
                        if ((zipConfirm = br.readLine()) != null) {
                            sb.append("zip: ").append(zipConfirm).append("\n");

                        }
                        String phoneConfirm = null;
                        if ((phoneConfirm = br.readLine()) != null) {
                            sb.append("phone: ").append(phoneConfirm).append("\n");

                        }
                        String orderConfirm = null;
                        if ((orderConfirm = br.readLine()) != null) {
                            sb.append("order: ").append(orderConfirm).append("\n");

                        }
                        String sizeConfirm = null;
                        if ((sizeConfirm = br.readLine()) != null) {
                            sb.append("size: ").append(sizeConfirm).append("\n");

                        }
                        String quantityConfirm = null;
                        if ((quantityConfirm = br.readLine()) != null) {
                            sb.append("quantity: ").append(quantityConfirm).append("\n");

                        }

                    }

                    {
                        sb.append(line).append("\n");
                    }
                    line = sb.toString();
                } catch (FileNotFoundException b) {
                    System.out.println("There was an exception!  The file was not found!");
                } catch (IOException b) {
                    System.out.println("There was an exception handling the file!");
                }



                String exitMsg = "You may now select 'OK'.";
                String confirmMsg = "Thanks for Ordering!\n";
                String outputMsg = confirmMsg + line + exitMsg;
                JOptionPane.showMessageDialog(null, outputMsg);

                System.exit(0);
            }
        }
    }
}
