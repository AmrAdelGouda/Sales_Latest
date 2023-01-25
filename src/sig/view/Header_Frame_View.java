package sig.view;


import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JDialog;


public class Header_Frame_View extends JDialog {
    private final JTextField _Date_TextField;
    private final JTextField _CustomerName_TextField;
    private final JLabel _Date_Label;
    private final JLabel _customerName_Label;
    private final JButton _Cancel_Button;
    private final JButton _saveChanges_Button;
    

    public Header_Frame_View(UI frame) {
         _Date_TextField = new JTextField(20);
         _CustomerName_TextField = new JTextField(20);
         _customerName_Label = new JLabel(" customer name");

         _Cancel_Button = new JButton(" cancel" );
         _saveChanges_Button = new JButton(" save ");
         _Date_Label = new JLabel(" date ");
       
        _saveChanges_Button.setActionCommand("newInvoice_Save");
        _Cancel_Button.setActionCommand("newInvoice_Cancel");
        
        
        _Cancel_Button.addActionListener(frame.getTheSelectedActionHandler());
        _saveChanges_Button.addActionListener(frame.getTheSelectedActionHandler());
        
        setLayout(new GridLayout(3, 2));
  
        add(_customerName_Label);
        add(_CustomerName_TextField);
        add(_Date_Label);
        add(_Date_TextField);
        add(_saveChanges_Button);
        add(_Cancel_Button);
        
      
        
        pack();
        
    }

    public JTextField getCustomerNameTextField() {
        return _CustomerName_TextField;
    }

    public JTextField getDateTextField() {
        return _Date_TextField;
    }
    
}
