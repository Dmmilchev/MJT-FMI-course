package bg.sofia.uni.fmi.mjt.trading.price;

public class PriceChart implements PriceChartAPI{
    private double microsoftStockPrice;
    private double googleStockPrice;
    private double amazonStockPrice;
    public PriceChart(double microsoftStockPrice, double googleStockPrice, double amazonStockPrice) {
        this.microsoftStockPrice = microsoftStockPrice;
        this.googleStockPrice = googleStockPrice;
        this.amazonStockPrice = amazonStockPrice;
    }

    public double getCurrentPrice(String stockTicker) {
        double tmp;
        if (stockTicker.equals("AMZ")) {
            tmp = amazonStockPrice;
        }
        else if(stockTicker.equals("GOOG")) {
            tmp = googleStockPrice;
        }
        else if(stockTicker.equals("MSFT")) {
            tmp = amazonStockPrice;
        }
        else {
            //maybe should handle it with raising an error, but don't know how for now
            return 0;
        }
        tmp *= 100;
        return (double)Math.round(tmp)/100;
    }

    public boolean changeStockPrice(String stockTicker, int percentChange) {
        if (percentChange < 0) {
            return false;
        }
        if (stockTicker.equals("AMZ")) {
            amazonStockPrice = amazonStockPrice + (double) amazonStockPrice*((double)percentChange/100);
        }
        else if(stockTicker.equals("GOOG")) {
            googleStockPrice = googleStockPrice + (double) googleStockPrice*((double)percentChange/100);
        }
        else if(stockTicker.equals("MSFT")) {
            microsoftStockPrice = microsoftStockPrice + (double) microsoftStockPrice*((double)percentChange/100);
        }
        else {
            return false;
        }
        return true;

    }

}
