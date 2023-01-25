package sig.model;

import javax.swing.table.AbstractTableModel;
import sig.view.UI;
import java.util.ArrayList;

public class Inovice_Header_Table extends AbstractTableModel {

    private String[] _columns = {"Invoice Num", "Invoice Date", "Customer Name", "Invoice Total"};
    private ArrayList<Header_Model> _InvoiceList;

    public Inovice_Header_Table(ArrayList<Header_Model> _InvoiceList) {
        this._InvoiceList = _InvoiceList;
    }

    @Override
    public int getColumnCount() {
        return _columns.length;
    }

    @Override
    public int getRowCount() {
        return _InvoiceList.size();
    }

    @Override
    public String getColumnName(int col) {
        return _columns[col];
    }

    @Override
    public Object getValueAt(int getRow, int getCol) {
        Header_Model inv = _InvoiceList.get(getRow);
        switch (getCol) {
            case 0:
                return inv.get_Num();
            case 3:
                return inv.get_ItemTotal();
            case 2:
                return inv.get_Customer();
            case 1:
                return UI.dateFormat.format(inv.get_Date());

        }
        return "";
    }

}
