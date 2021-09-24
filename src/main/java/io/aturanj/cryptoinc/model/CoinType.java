package io.aturanj.cryptoinc.model;

public enum CoinType {

    BTC("Bitcoin"),
    ETH("Etherium"),
    LTC("Litecoin");

    public final String label;

    private CoinType(String label) {
        this.label = label;
    }
}
