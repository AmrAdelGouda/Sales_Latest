package sig.model;

import java.util.Date;
import java.util.ArrayList;


public class Header_Model {

    private int _Invoice_Number;
    private String _Customer_Name;
    private Date _Invoice_Date;
    private ArrayList<Line_Model> _Lines;

    public Header_Model() {

    }

    public Header_Model(int num, String customer, Date date) {
        this._Invoice_Number = num;
        this._Customer_Name = customer;
        this._Invoice_Date = date;
    }

    public int get_Num() {
        return _Invoice_Number;
    }

    public String get_Customer() {
        return _Customer_Name;
    }

    public void setNum(int num) {
        this._Invoice_Number = num;
    }

    public Date get_Date() {
        return _Invoice_Date;
    }

    public void setDate(Date date) {
        this._Invoice_Date = date;
    }

    public void setCustomer(String customer) {
        this._Customer_Name = customer;
    }

    public void setLines(ArrayList<Line_Model> lines) {
        this._Lines = lines;
    }

    public ArrayList<Line_Model> getLines() {
        if (_Lines != null) {
            return _Lines;

        }
        return _Lines = new ArrayList<>();
    }

    public double get_ItemTotal() {

        int i = 0;
        double Sum = 0.0;
        while (i < getLines().size()) {
            Sum = Sum + getLines().get(i).get_LineTotal();
            i++;
        }

        return Sum;
    }

}
