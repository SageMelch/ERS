import com.mysql.cj.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class ReceiptDaoImp implements ReceiptDao{
    private Session currentSession;
    private Transaction currentTransaction;

    public ReceiptDaoImp(){
    }
    public Session openCurrentSession(){
        currentSession = (Session) currentSession.getSessionFactory();
        return currentSession;
    }

    @Override
    public void addReceipt(Receipt receipt) throws SQLException {

    }

    @Override
    public void updateReceipt(Receipt receipt) {
        // pen the session

    }

    @Override
    public List<Receipt> getReceipts() {
        return null;
    }

    @Override
    public Receipt getReceiptById(int id) {
        return null;
    }
}
