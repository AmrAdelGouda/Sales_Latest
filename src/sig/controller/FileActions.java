package sig.controller;

import java.util.ArrayList;
import java.io.FileWriter;
import sig.view.UI;
import sig.model.Invoice_Lines_Table;
import javax.swing.JOptionPane;
import sig.view.Header_Frame_View;
import java.util.Date;
import java.nio.file.Files;
import javax.swing.JFileChooser;
import java.util.List;
import java.io.File;
import sig.view.Lines_Frame_View;
import java.awt.event.ActionEvent;
import java.nio.file.Path;
import sig.model.Line_Model;
import java.nio.file.Paths;
import sig.model.Inovice_Header_Table;
import java.awt.event.ActionListener;
import sig.model.Header_Model;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class FileActions implements ActionListener {

    private Lines_Frame_View _Invoice_LinePopup;
    private Header_Frame_View _Invoice_headerPopup;
    private UI _UI_Frame;

    public FileActions(UI eframe) {

        this._UI_Frame = eframe;
    }

    @Override
    public void actionPerformed(ActionEvent action) {
        System.out.println(action.getActionCommand());

        switch (action.getActionCommand()) {

            case "Create New Invoice":
                Create_NewInvoice();
                break;
                
            case "Create Item":
                Save_Changes();
                break;
                
            case "Delete Item":
                cancel();
                break;
                
            case "Delete Invoice":
                Delete_Invoice();
                break;

            case "newInvoice_Cancel":
                newInvoiceDialog_Cancel();
                break;

            case "newInvoice_Save":
                newInvoiceDialog_OK();
                break;

            case "save file":
                save_file();
                break;

            case "load file":
                load_file();
                break;

            case "newLine_Cancel":
                newLineDialog_Cancel();
                break;

            case "newLine_Save":
                newLineDialog_OK();
                break;

            default:
                System.out.println("Error : Selection is Not Valid");

        }
    }

    private void load_file() {
        JFileChooser _ChooserFile = new JFileChooser();
        try {
            int _res = _ChooserFile.showOpenDialog(_UI_Frame);
            if (_res == JFileChooser.APPROVE_OPTION) {
                File _InvoiceHeaderFile = _ChooserFile.getSelectedFile();
                Path _InvoiceHeaderPath = Paths.get(_InvoiceHeaderFile.getAbsolutePath());
                if (!_InvoiceHeaderFile.getAbsolutePath().endsWith(".csv")) {
                    JOptionPane.showMessageDialog(_UI_Frame, "Wrong file format", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                List<String> headerLines = Files.readAllLines(_InvoiceHeaderPath);
                ArrayList<Header_Model> Headers = new ArrayList<>();
                for (String headerLine : headerLines) {
                    String[] Stringss = headerLine.split(",");
                    String String_Num1 = Stringss[0];
                    String String_Num2 = Stringss[1];
                    String String_Num3 = Stringss[2];
                    int code = Integer.parseInt(String_Num1);
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                        Date invoiceDate = dateFormat.parse(String_Num2);

                        Header_Model hdr = new Header_Model(code, String_Num3, invoiceDate);
                        Headers.add(hdr);
                    } catch (Exception e) {
                        System.out.println(e);

                        JOptionPane.showMessageDialog(_UI_Frame, "Date format is Wrong", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                }
                _UI_Frame.setarries(Headers);

                _res = _ChooserFile.showOpenDialog(_UI_Frame);
                if (_res == JFileChooser.APPROVE_OPTION) {
                    File lineFile = _ChooserFile.getSelectedFile();
                    Path linePath = Paths.get(lineFile.getAbsolutePath());

                    if (!_InvoiceHeaderFile.getAbsolutePath().endsWith(".csv")) {
                        JOptionPane.showMessageDialog(_UI_Frame, "Wrong file format", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    List<String> lineLines = Files.readAllLines(linePath);
                    ArrayList<Line_Model> invoiceLines = new ArrayList<>();
                    for (String line : lineLines) {
                        String[] Strings = line.split(",");
                        String arr1 = Strings[0];    // invoice num (int)
                        String arr2 = Strings[1];    // item name   (String)
                        String arr4 = Strings[3];    // count       (int)
                        String arr3 = Strings[2];    // price       (double)
                        
                        int Code = Integer.parseInt(arr1);
                        double price = Double.parseDouble(arr3);
                        int count = Integer.parseInt(arr4);
                        Header_Model inv = _UI_Frame.getInvoices(Code);
                        Line_Model invoiceLine = new Line_Model(arr2, price, count, inv);
                        inv.getLines().add(invoiceLine);
                    }
                }
                Inovice_Header_Table headerTable = new Inovice_Header_Table(Headers);
                _UI_Frame.setTheheaderTable(headerTable);
                _UI_Frame.gettheInvoicetbl().setModel(headerTable);
                System.out.println("files read");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(_UI_Frame, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void Create_NewInvoice() {
        _Invoice_headerPopup = new Header_Frame_View(_UI_Frame);
        _Invoice_headerPopup.setVisible(true);
    }

    private void Delete_Invoice() {
        int selectedInvoiceIndex = _UI_Frame.gettheInvoicetbl().getSelectedRow();
        if (selectedInvoiceIndex != -1) {
            _UI_Frame.getlstofLines().remove(selectedInvoiceIndex);
            _UI_Frame.getTheheaderTable().fireTableDataChanged();

            _UI_Frame.getTheLinetbl().setModel(new Invoice_Lines_Table(null));
            _UI_Frame.setlstofLines(null);
            _UI_Frame.getNumberOfTheInvoiceInsideLabel().setText("");
            _UI_Frame.getTheNameofCustLbl().setText("");
            _UI_Frame.getTheDateINsideTheLabel().setText("");
            _UI_Frame.getTheTotalOfTheInvoicelabel().setText("");
            
        }
    }

    private void Save_Changes() {
        _Invoice_LinePopup = new Lines_Frame_View(_UI_Frame);
        _Invoice_LinePopup.setVisible(true);
    }

    private void cancel() {
        int selectedLineIndex = _UI_Frame.getTheLinetbl().getSelectedRow();
        int selectedInvoiceIndex = _UI_Frame.gettheInvoicetbl().getSelectedRow();
        if (selectedLineIndex != -1) {
            _UI_Frame.getLinesArray().remove(selectedLineIndex);
            Invoice_Lines_Table lineTableModel = (Invoice_Lines_Table) _UI_Frame.getTheLinetbl().getModel();
            lineTableModel.fireTableDataChanged();
            _UI_Frame.getTheTotalOfTheInvoicelabel().setText("" + _UI_Frame.getlstofLines().get(selectedInvoiceIndex).get_ItemTotal());
            _UI_Frame.getTheheaderTable().fireTableDataChanged();
            _UI_Frame.gettheInvoicetbl().setRowSelectionInterval(selectedInvoiceIndex, selectedInvoiceIndex);
        }
    }

    private void save_file() {
        ArrayList<Header_Model> invoicesArray = _UI_Frame.getlstofLines();
        JFileChooser fc = new JFileChooser();
        try {
            int _res = fc.showSaveDialog(_UI_Frame);
            if (_res == JFileChooser.APPROVE_OPTION) {
                File _InvoiceHeaderFile = fc.getSelectedFile();
                if (!_InvoiceHeaderFile.getAbsolutePath().endsWith(".csv")) {
                    JOptionPane.showMessageDialog(_UI_Frame, "Wrong file format", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                FileWriter hfw = new FileWriter(_InvoiceHeaderFile);
                String headers = "";
                String lines = "";
                for (Header_Model invoice : invoicesArray) {
                    headers += invoice.toString();
                    headers += "\n";
                    for (Line_Model line : invoice.getLines()) {
                        lines += line.toString();
                        lines += "\n";
                    }
                }

                headers = headers.substring(0, headers.length() - 1);
                lines = lines.substring(0, lines.length() - 1);
                _res = fc.showSaveDialog(_UI_Frame);
                File lineFile = fc.getSelectedFile();
                if (!lineFile.getAbsolutePath().endsWith(".csv")) {
                    JOptionPane.showMessageDialog(_UI_Frame, "Wrong file format", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                FileWriter lfw = new FileWriter(lineFile);
                hfw.write(headers);
                lfw.write(lines);
                hfw.close();
                lfw.close();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(_UI_Frame, "Folder/File path is not found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void newLineDialog_Cancel() {
        _Invoice_LinePopup.setVisible(false);
        _Invoice_LinePopup.dispose();
        _Invoice_LinePopup = null;
    }

    private void newInvoiceDialog_OK() {
        _Invoice_headerPopup.setVisible(false);

        String custName = _Invoice_headerPopup.getCustomerNameTextField().getText();
        String str = _Invoice_headerPopup.getDateTextField().getText();
        Date da = new Date();

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            da = dateFormat.parse(str);
            int inv_Num = 0;
            for (Header_Model inv : _UI_Frame.getlstofLines()) {
                if (inv.get_Num() > inv_Num) {
                    inv_Num = inv.get_Num();
                }
            }
            inv_Num++;
            Header_Model newInv = new Header_Model(inv_Num, custName, da);
            _UI_Frame.getlstofLines().add(newInv);
            _UI_Frame.getTheheaderTable().fireTableDataChanged();
            _Invoice_headerPopup.dispose();
            _Invoice_headerPopup = null;
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(_UI_Frame, "Wrong date format", "Invalid date format", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void newLineDialog_OK() {
        _Invoice_LinePopup.setVisible(false);

        String name = _Invoice_LinePopup.getItemNameTextField().getText();
        String str1 = _Invoice_LinePopup.getItemCountTextField().getText();
        String String_Num2 = _Invoice_LinePopup.getItemPriceTextField().getText();
        int count = 1;
        double price = 1;
        try {
            count = Integer.parseInt(str1);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(_UI_Frame, "Cannot convert number", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }

        try {
            price = Double.parseDouble(String_Num2);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(_UI_Frame, "Cannot convert price", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }
        int selectedInvHeader = _UI_Frame.gettheInvoicetbl().getSelectedRow();
        if (selectedInvHeader != -1) {
            Header_Model inv_Header = _UI_Frame.getlstofLines().get(selectedInvHeader);
            Line_Model line = new Line_Model(name, price, count, inv_Header);
            //invHeader.getLines().add(line);
            _UI_Frame.getLinesArray().add(line);
            Invoice_Lines_Table lineTable = (Invoice_Lines_Table) _UI_Frame.getTheLinetbl().getModel();
            lineTable.fireTableDataChanged();
            _UI_Frame.getTheheaderTable().fireTableDataChanged();
        }
        _UI_Frame.gettheInvoicetbl().setRowSelectionInterval(selectedInvHeader, selectedInvHeader);
        _Invoice_LinePopup.dispose();
        _Invoice_LinePopup = null;
    }

    private void newInvoiceDialog_Cancel() {
        _Invoice_headerPopup.setVisible(false);
        _Invoice_headerPopup = null;
        _Invoice_headerPopup.dispose();

    }

}
