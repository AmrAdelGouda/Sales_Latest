package sig.model;

public class Line_Model {

    private String _Item_Name;
    private int _Count;
    private Header_Model _Header;
    private double _Item_Price;

    public Line_Model() {
    }

    public Header_Model getHeader() {
        return _Header;
    }

    public Line_Model(String item, double price, int count, Header_Model _Header) {
        this._Item_Name = item;
        this._Count = count;
        this._Header = _Header;
        this._Item_Price = price;
    }

    public double get_Price() {
        return _Item_Price;
    }

    public String get_Item() {
        return _Item_Name;
    }

    public double get_LineTotal() {
        return _Item_Price * _Count;
    }

    public int get_Count() {
        return _Count;
    }

}
