package bg.sofia.uni.fmi.mjt.trading.stock;

import java.time.LocalDateTime;

public class AmazonStockPurchase extends AnyStockPurchase{
    private static final String stockTicker = "AMZ";

    public AmazonStockPurchase(int quantity, LocalDateTime purchaseTimestamp, double purchasePricePerUnit){
        super(stockTicker, quantity, purchaseTimestamp, purchasePricePerUnit);
    }
    public String getStockTicker() {
        return stockTicker;
    }
}
