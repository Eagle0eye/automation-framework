import org.openqa.selenium.edge.EdgeDriver;
import pages.cores.HomePage;
import utils.ProductRepository;

public class run {
    public static void main(String[] args) {

        EdgeDriver webdriver = new EdgeDriver();

        try {

            HomePage homePage = new HomePage(webdriver);
            homePage.open();
            ProductRepository repository = new ProductRepository(webdriver);
            repository.loadProducts();

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
