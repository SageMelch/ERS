import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

interface ReceiptDao {

    public void addReceipt(Receipt receipt) throws SQLException;
    public void updateReceipt(Receipt receipt);
    public List<Receipt> getReceipts();
    public Receipt getReceiptById(int id);

}
