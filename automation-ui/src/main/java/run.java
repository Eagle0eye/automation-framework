import DTO.ProductInfo;
import org.openqa.selenium.edge.EdgeDriver;
import pages.cores.ProductPage;


import java.util.ArrayList;
import java.util.List;

public class run {
    public static void main(String[] args) {
        EdgeDriver driver = new EdgeDriver();
        ProductPage page = new ProductPage(driver);
        page.open();
        page.loadProducts();
        List<ProductInfo> products = page.viewProductList();
        for (ProductInfo product : products) {
            System.out.println(product.getProductName());
        }

        List<String> search = new ArrayList<>();
        search.add(products.get(1).getProductName() );
        search.add(products.get(2).getProductName());
        search.add(products.get(3).getProductName());
        page.selectProduct(search);


    }
}
