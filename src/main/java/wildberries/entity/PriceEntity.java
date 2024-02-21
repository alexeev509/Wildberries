package wildberries.entity;

public class PriceEntity {
    private Long nmId;

    private Long price;

    private Long discount;

    private Long promoCode;

    public Long getNmId() {
        return nmId;
    }

    public void setNmId(Long nmId) {
        this.nmId = nmId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public Long getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(Long promoCode) {
        this.promoCode = promoCode;
    }

    @Override
    public String toString() {
        return "PriceEntity{" +
                "nmId=" + nmId +
                ", price=" + price +
                ", discount=" + discount +
                ", promoCode=" + promoCode +
                '}';
    }
}
