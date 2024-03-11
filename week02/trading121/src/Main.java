import bg.sofia.uni.fmi.mjt.trading.Portfolio;
import bg.sofia.uni.fmi.mjt.trading.price.PriceChart;
import bg.sofia.uni.fmi.mjt.trading.stock.StockPurchase;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;

public class Main {
    public static void main(String[] args) {
        PriceChart priceChart = new PriceChart(1, 2, 3);
        Portfolio portfolio = new Portfolio("Deyan", priceChart, 100, 20);

        portfolio.buyStock("AMZ", 10);
        portfolio.buyStock("MSFT", 10);
        System.out.println(portfolio.getCurrentSize());
        System.out.println(portfolio.getRemainingBudget());
        System.out.println(portfolio.getNetWorth());
        System.out.println(portfolio.getPriceChart().getCurrentPrice("AMZ"));
        System.out.println(portfolio.getOwner());
        LocalDateTime start = LocalDateTime.of(2015, 6, 29, 19, 30, 40);
        LocalDateTime end = LocalDateTime.of(2025, 6, 29, 19, 30, 40);
        StockPurchase[] st = portfolio.getAllPurchases(start, end);
        for (StockPurchase s : st) {
            System.out.println(s);
        }
    }
}