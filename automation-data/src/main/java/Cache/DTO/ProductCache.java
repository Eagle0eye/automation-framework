package Cache.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class ProductCache {
    private String productName;
    private int quantity;
    private int price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductCache)) return false;
        ProductCache that = (ProductCache) o;
        return Objects.equals(productName, that.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName);
    }
}
