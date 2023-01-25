package sig.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class Invoice_Lines_Table extends AbstractTableModel {

    private ArrayList<Line_Model> _Lines;
    private String[] _Columns = {"Item Name", "Unit Price", "Count", "Line Total"};

    public Invoice_Lines_Table(ArrayList<Line_Model> linesArray) {
        this._Lines = linesArray;
    }

    @Override
    public int getRowCount() {
        return _Lines == null ? 0 : _Lines.size();
    }

    @Override
    public Object getValueAt(int getRow, int getCol) {
        if (_Lines == null) {
            return "";
        } else {
            Line_Model line = _Lines.get(getRow);
            switch (getCol) {
                case 0:
                    return line.get_Item();
                case 3:
                    return line.get_LineTotal();
                case 2:
                    return line.get_Count();
                case 1:
                    return line.get_Price();
                default:
                    return "";
            }
        }
    }

    @Override
    public int getColumnCount() {
        return _Columns.length;
    }

    @Override
    public String getColumnName(int col) {
        return _Columns[col];
    }

}
