package sqa.hanu_minimart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sqa.hanu_minimart.model.Product;
import sqa.hanu_minimart.model.ProductStatus;
import sqa.hanu_minimart.repository.ProductRepository;

import javax.naming.directory.InvalidAttributesException;
import javax.transaction.Transactional;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    public void addNewProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(int id) {
        boolean exists = productRepository.existsById(id);
        if (!exists){
            throw new IllegalStateException("Product does not exist!");
        }
        productRepository.deleteById(id);
    }

    public List<Product> getProductByName(String name) {
        System.out.println(name);
        return productRepository.findByNameContaining(name);
    }

    public List<Product> getProductNearExpireDate() {
        return productRepository.findNearlyExpireProduct();
    }

    public List<Product> getNewProduct() {
        return productRepository.findNewestImportProduct();
    }

<<<<<<< HEAD
    @Transactional
    public void updateProductQuantity(int id, String name, int quantity, double price, String category, LocalDate expireDate, String status) {
=======
    public void updateProductQuantity(int id, String name, double price, int quantity, String category, String status, String expireDate) {
>>>>>>> origin/main
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Product does not exist!"));

        if(name != null && name.length() > 0 && !Objects.equals(name, product.getName())){
            product.setName(name);
        }

        if(quantity != 0 && quantity != product.getQuantity()){
            product.setQuantity(quantity);
        }

        if(price != 0 && price != product.getPrice()){
            product.setPrice(price);
        }

        if(category != null && category.length() > 0 && !Objects.equals(category, product.getCategory())){
            product.setCategory(category);
        }

        if(status.equalsIgnoreCase("hot")){
            product.setProductStatus(ProductStatus.HOT);
        }else if (status.equalsIgnoreCase("new")){
            product.setProductStatus(ProductStatus.NEW);
        }
<<<<<<< HEAD
        product.setStatus(status);
     
=======

        if(expireDate != null && expireDate.length() >0 && !expireDate.equals(product.getExpireDate().toString())){
            product.setExpireDate(LocalDate.parse(expireDate));
        }

        productRepository.save(product);
    }

    public List<Product> getProductByStatus(String status) {
        return productRepository.findByProductStatus(status);
    }

    public List<Product> getProductByCategory(String category) {
        return productRepository.findByCategory(category);
>>>>>>> origin/main
    }
}
