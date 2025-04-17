package productmanagement.tp2.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import productmanagement.tp2.entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //methode 1 est utiliser des methodes dans interface et l utiliser dans main
    List<Product> findByNameContains(String mc);
    List<Product> findByPriceGreaterThan(double price);
    Product findById(long id);
    //methode 2 some sql
    @Query("select p from Product p where p.name like :x")
    List<Product> search(@Param("x") String mc);

    @Query("select p from Product p where p.price>:x")
    List<Product> search(@Param("x") double price);

}
