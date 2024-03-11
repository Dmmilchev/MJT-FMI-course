package bg.sofia.uni.fmi.mjt.trading.stock;

import java.time.LocalDateTime;

public class MicrosoftStockPurchase extends AnyStockPurchase{
    private static final String stockTicker = "MSFT";

    public MicrosoftStockPurchase(int quantity, LocalDateTime purchaseTimestamp, double purchasePricePerUnit){
        super(stockTicker, quantity, purchaseTimestamp, purchasePricePerUnit);
    }

    public String getStockTicker() {
        return stockTicker;
    }
}
