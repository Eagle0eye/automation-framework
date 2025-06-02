package Cache;

import Cache.DTO.ProductCache;
import Cache.DTO.UserInfo;

import java.util.HashSet;
import java.util.Set;

public class CacheService {

    public static UserInfo getUserInfo() {
        return (UserInfo) Cache.get("user-info");
    }

    public static void addInfo(String username, String password) {
        Cache.put("user-info", UserInfo.builder().email(username).password(password).build());
    }

    public static void updateUserInfo(UserInfo userInfo) {
        UserInfo updated = (UserInfo) Cache.get("user-info");

        if (updated == null) {
            updated = userInfo;
        } else {
            updated.setTitle(userInfo.getTitle());
            updated.setPhone(userInfo.getPhone());
            updated.setAddress(userInfo.getAddress());
            updated.setAddress2(userInfo.getAddress2());
            updated.setCity(userInfo.getCity());
            updated.setState(userInfo.getState());
            updated.setZipcode(userInfo.getZipcode());
            updated.setCountry(userInfo.getCountry());
            updated.setFirstname(userInfo.getFirstname());
            updated.setLastname(userInfo.getLastname());
            updated.setCompany(userInfo.getCompany());
        }

        Cache.put("user-info", updated);
    }

    private static Set<ProductCache> getProductSet() {
        Object obj = Cache.get("order-products");
        if (obj == null) {
            Set<ProductCache> newProducts = new HashSet<>();
            Cache.put("order-products", newProducts);
            return newProducts;
        }
        return (Set<ProductCache>) obj;
    }

    public static void addProductCache(ProductCache productCache) {
        Set<ProductCache> products = getProductSet();
        boolean found = false;

        for (ProductCache product : products) {
            if (product.getProductName().equals(productCache.getProductName())) {
                product.setQuantity(product.getQuantity() + productCache.getQuantity());
                found = true;
                break;
            }
        }

        if (!found) {
            products.add(productCache);
        }

        Cache.put("order-products", products);
    }

    public static void removeProductCache(ProductCache productCache) {
        Set<ProductCache> products = getProductSet();
        ProductCache found = null;
        for (ProductCache product : products) {
            if (product.getProductName().equals(productCache.getProductName())) {
                found = product;
            }
        }
        if (found != null) {
            products.remove(found);
        }
        Cache.put("order-products", products);
    }


    public static void removeProductCache(String productName) {
        Set<ProductCache> products = getProductSet();
        ProductCache toRemove = null;

        for (ProductCache product : products) {
            if (product.getProductName().equals(productName)) {
                if (product.getQuantity() > 1) {
                    product.setQuantity(product.getQuantity() - 1);
                } else {
                    toRemove = product;
                }
                break;
            }
        }

        if (toRemove != null) {
            products.remove(toRemove);
        }

        Cache.put("order-products", products);
    }

    public static Set<ProductCache> getSavedProducts() {
        return getProductSet();
    }

    public static void printProducts() {
        getProductSet().forEach(System.out::println);
    }
}
