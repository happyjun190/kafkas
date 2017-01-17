package com.kafkas.redis;

/**
 * Created by wushenjun on 17-1-17.
 */
public class RedisCarDTO {
    private String cartId;
    private int num;
    private int ischecked;
    private int storeId;

    public RedisCarDTO() {
    }

    public RedisCarDTO(String cartId, int num, int ischecked, int storeId) {
        this.cartId = cartId;
        this.num = num;
        this.ischecked = ischecked;
        this.storeId = storeId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getIschecked() {
        return ischecked;
    }

    public void setIschecked(int ischecked) {
        this.ischecked = ischecked;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "{" +
                "\"cartId\":" + cartId +
                ", \"num\":" + num +
                ", \"ischecked\":" + ischecked +
                ", \"storeId\":" + storeId +
                '}';
    }
}
