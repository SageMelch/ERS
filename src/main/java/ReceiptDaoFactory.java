public class ReceiptDaoFactory {
    private static ReceiptDao receiptDao;

    private ReceiptDaoFactory() {
    }

    public static ReceiptDao getReceiptDao(){
        if(receiptDao==null){
            receiptDao = new ReceiptDaoImp();
        }
        return receiptDao;
    }
}

