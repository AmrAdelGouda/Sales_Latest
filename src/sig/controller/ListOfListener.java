
package sig.controller;

import sig.model.Line_Model;
import sig.model.Header_Model;
import java.util.ArrayList;
import sig.model.Invoice_Lines_Table;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import sig.view.UI;


public class ListOfListener implements ListSelectionListener {

    private final  UI UI_Frame;

    public ListOfListener(UI the_frame) {
        this.UI_Frame = the_frame;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int idx = UI_Frame.gettheInvoicetbl().getSelectedRow();
        
        if (idx != -1) {
            Header_Model Invoice = UI_Frame.getlstofLines().get(idx);
            ArrayList<Line_Model> Model = Invoice.getLines();
            Invoice_Lines_Table Drawtbl = new Invoice_Lines_Table(Model);
            
            UI_Frame.getTheLinetbl().setModel(Drawtbl);
            UI_Frame.setlstofLines(Model);
            UI_Frame.getNumberOfTheInvoiceInsideLabel().setText("" + Invoice.get_Num());
            UI_Frame.getTheNameofCustLbl().setText(Invoice.get_Customer());
            UI_Frame.getTheDateINsideTheLabel().setText(UI.dateFormat.format(Invoice.get_Date()));
            UI_Frame.getTheTotalOfTheInvoicelabel().setText("" + Invoice.get_ItemTotal());
            
        }
    }

}

    

