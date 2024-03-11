package bg.sofia.uni.fmi.mjt.trading;

import java.time.LocalDateTime;
import java.util.Arrays;

import bg.sofia.uni.fmi.mjt.trading.stock.StockPurchase;
import bg.sofia.uni.fmi.mjt.trading.stock.AmazonStockPurchase;
import bg.sofia.uni.fmi.mjt.trading.stock.MicrosoftStockPurchase;
import bg.sofia.uni.fmi.mjt.trading.stock.GoogleStockPurchase;
import bg.sofia.uni.fmi.mjt.trading.stock.AnyStockPurchase;
import bg.sofia.uni.fmi.mjt.trading.price.PriceChartAPI;
import bg.sofia.uni.fmi.mjt.trading.price.PriceChart;

public class Portfolio implements PortfolioAPI{

    private static final int FIXED_CHANGE_OF_STOCK_PRICE = 5;
    private String owner;
    private PriceChartAPI priceChart;
    private double budget;
    private StockPurchase[] stockPurchases;
    private int maxSize;
    private int currSize;
    public Portfolio(String owner, PriceChartAPI priceChart, double budget, int maxSize){
        this.owner = owner;
        this.priceChart = priceChart;
        this.budget = budget;
        this.maxSize = maxSize;
        this.currSize = 0;
        stockPurchases = new StockPurchase[maxSize];
    }
    public Portfolio(String owner, PriceChartAPI priceChart, StockPurchase[] stockPurchases, double budget, int maxSize){
        this.owner = owner;
        this.priceChart = priceChart;
        this.budget = budget;
        this.stockPurchases = Arrays.copyOf(stockPurchases, maxSize);
        this.maxSize = maxSize;
        this.currSize = 0;
    }

    private StockPurchase attemptBuyingStock(StockPurchase stock) {
        if (budget < stock.getTotalPurchasePrice() || currSize >= maxSize){
            return null;
        }

        budget -= stock.getTotalPurchasePrice();
        stockPurchases[currSize] = stock;
        currSize++;
        priceChart.changeStockPrice(stock.getStockTicker(), stock.getQuantity() * FIXED_CHANGE_OF_STOCK_PRICE);

        return stock;
    }

    private boolean isPurchaseInTimeInterval(StockPurchase stock, LocalDateTime startTime, LocalDateTime endTime) {
        return stock.getPurchaseTimestamp().isAfter(startTime) && stock.getPurchaseTimestamp().isBefore(endTime);
    }

    @Override
    public StockPurchase buyStock(String stockTicker, int quantity) {
        if (quantity <= 0) {
            return null;
        }

        if (stockTicker.equals("AMZ")) {
            StockPurchase stock = new AmazonStockPurchase(quantity, LocalDateTime.now(), priceChart.getCurrentPrice("AMZ"));
            return attemptBuyingStock(stock);
        }
        else if(stockTicker.equals("GOOG")) {
            StockPurchase stock = new GoogleStockPurchase(quantity, LocalDateTime.now(), priceChart.getCurrentPrice("GOOG"));
            return attemptBuyingStock(stock);
        }
        else if(stockTicker.equals("MSFT")) {
            StockPurchase stock = new MicrosoftStockPurchase(quantity, LocalDateTime.now(), priceChart.getCurrentPrice("MSFT"));
            return attemptBuyingStock(stock);
        }

        return null;
    }

    @Override
    public StockPurchase[] getAllPurchases() {
        return Arrays.copyOf(stockPurchases, currSize);
    }

    private int getPurchasesInTimeIntervalCount(LocalDateTime start, LocalDateTime end) {
        int count = 0;
        for (int i = 0; i < currSize; ++i) {
            if (isPurchaseInTimeInterval(stockPurchases[i], start, end)) {
                ++count;
            }
        }
        return count;
    }
    @Override
    public StockPurchase[] getAllPurchases(LocalDateTime startTimestamp, LocalDateTime endTimestamp) {
        int sizeArray = getPurchasesInTimeIntervalCount(startTimestamp, endTimestamp);
        StockPurchase[] result = new StockPurchase[sizeArray];

        int itemsInRes = 0;
        for (int i = 0; i < currSize; ++i) {
            if (isPurchaseInTimeInterval(stockPurchases[i], startTimestamp, endTimestamp)) {
                result[itemsInRes] = stockPurchases[i];
                itemsInRes++;
            }
        }

        return result;
    }

    @Override
    public double getNetWorth() {
        double netWorth = 0;
        for (StockPurchase stock : stockPurchases) {
            if (stock != null) {
                netWorth += stock.getQuantity() * priceChart.getCurrentPrice(stock.getStockTicker());
            }
        }

        netWorth = Math.round(netWorth * 100);
        return netWorth/100;
    }

    @Override
    public double getRemainingBudget() {
        return (double)Math.round(budget * 100) / 100;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    public PriceChartAPI getPriceChart() {
        return priceChart;
    }

    public int getCurrentSize() {
        return currSize;
    }



}
