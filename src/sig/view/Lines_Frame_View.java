package sig.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Lines_Frame_View extends JDialog {

    private final JTextField _ItemName_TextField;
    private final JTextField _ItemCount_TextField;
    private final JTextField _ItemPrice_TextField;
    private final JLabel _ItemName_Label;
    private final JLabel _ItemCount_Label;
    private final JLabel _ItemPrice_Label;
    private final JButton _CreateItem_Button;
    private final JButton _DeleteItem_Button;

    public Lines_Frame_View(UI frame) {

        _ItemCount_TextField = new JTextField(20);
        _ItemCount_Label = new JLabel(" Count ");

        _ItemPrice_TextField = new JTextField(20);
        _ItemPrice_Label = new JLabel(" Price ");

        _ItemName_TextField = new JTextField(20);
        _ItemName_Label = new JLabel(" Product Name ");

        _DeleteItem_Button = new JButton(" Close  ");
        _CreateItem_Button = new JButton(" Add Item  ");

        _DeleteItem_Button.setActionCommand("newLine_Cancel");
        _CreateItem_Button.setActionCommand("newLine_Save");

        _DeleteItem_Button.addActionListener(frame.getTheSelectedActionHandler());
        _CreateItem_Button.addActionListener(frame.getTheSelectedActionHandler());

        setLayout(new GridLayout(4, 2));

        add(_ItemName_Label);
        add(_ItemName_TextField);
        add(_ItemPrice_Label);
        add(_ItemPrice_TextField);
        add(_ItemCount_Label);
        add(_ItemCount_TextField);
        add(_CreateItem_Button);
        add(_DeleteItem_Button);

        pack();
    }

    public JTextField getItemNameTextField() {
        return _ItemName_TextField;
    }

    public JTextField getItemCountTextField() {
        return _ItemCount_TextField;
    }

    public JTextField getItemPriceTextField() {
        return _ItemPrice_TextField;
    }
}
