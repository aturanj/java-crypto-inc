package io.aturanj.cryptoinc.model;

public enum OrderType {

    BUY("Buy"),
    SELL("Sell");

    public final String label;

    private OrderType(String label) {
        this.label = label;
    }
}
