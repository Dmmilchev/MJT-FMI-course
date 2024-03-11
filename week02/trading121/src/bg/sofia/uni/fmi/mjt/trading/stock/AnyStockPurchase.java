package bg.sofia.uni.fmi.mjt.trading.stock;

import java.time.LocalDateTime;
import java.lang.Math;
public abstract class AnyStockPurchase implements StockPurchase{
    private final String stockTicker;
    private final int quantity;
    private final LocalDateTime purchaseTimestamp;
    private final double purchasePricePerUnit;


    AnyStockPurchase(String stockTicker, int quantity, LocalDateTime purchaseTimestamp, double purchasePricePerUnit){
        this.stockTicker = stockTicker;
        this.quantity = quantity;
        this.purchaseTimestamp = purchaseTimestamp;
        this.purchasePricePerUnit = purchasePricePerUnit;
    }
    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public LocalDateTime getPurchaseTimestamp() {
        return purchaseTimestamp;
    }

    @Override
    public double getPurchasePricePerUnit() {
        long tmp = Math.round(purchasePricePerUnit*100);
        return tmp /100;
    }

    @Override
    public double getTotalPurchasePrice() {
        return getQuantity() * getPurchasePricePerUnit();
    }

}
