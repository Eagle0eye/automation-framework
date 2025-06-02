import Cache.DTO.ProductCache;
import Cache.DTO.UserInfo;
import Cache.CacheService;

import java.io.IOException;

public class run {
    public static void main(String[] args) throws IOException {
        CacheService.addInfo("automation2@armyspy.com", "auto12345678");
        System.out.println(CacheService.getUserInfo().toString());

        // Update user info
        CacheService.updateUserInfo(UserInfo.builder()
                .title("Mrs")
                .name("Test Automation")
                .address("address")
                .address2("address2")
                .city("city")
                .company("company")
                .firstname("Updated")
                .lastname("Name")
                .phone("+1234567890")
                .city("New City")
                .state("New State")
                .zipcode("12345")
                .country("Updated Country")
                .build());
        System.out.println(CacheService.getUserInfo().toString());
        // Add products
        CacheService.addProductCache(ProductCache.builder().productName("ProductA").quantity(1).price(100).build());
        CacheService.addProductCache(ProductCache.builder().productName("ProductA").quantity(2).price(100).build()); // will be merged
        CacheService.addProductCache(ProductCache.builder().productName("ProductB").quantity(1).price(200).build());

        // Print products
        CacheService.printProducts();

        // Remove product
        CacheService.removeProductCache("ProductA");
        CacheService.removeProductCache("ProductA");
        CacheService.removeProductCache("ProductA");
        // Print again
        CacheService.printProducts();
    }
}
