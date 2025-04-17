package productmanagement.tp2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import productmanagement.tp2.Repositories.ProductRepository;
import productmanagement.tp2.entities.Product;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Tp2Application implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;
    public static void main(String[] args) {
        SpringApplication.run(Tp2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*
        productRepository.save(new Product(null,"Computer",4300,3));
        productRepository.save(new Product(null,"Printer",1200,4));
        productRepository.save(new Product(null,"Smart Phone",3200,32));
        productRepository.save(new Product(null,"Smart Phone",3455,45));
        productRepository.save(new Product(null,"Smart Phone",2333,44));
        */

        //affichage de la liste des produits

        List<Product> products = productRepository.findAll();
        products.forEach(p ->{
            System.out.println(p);
        });

        System.out.println("---------------------------------------------");
        //affichage du produit qui a pour id 1

        Product p= productRepository.findById(Long.valueOf(1)).get();
        System.out.println("le produit de ID egal a 1 est : \n");
        System.out.println(p.getName());

        System.out.println("---------------------------------------------");

        //affichage de la liste des produits qui contiennent la lettre C

            //methode 1
            List<Product> list = productRepository.findByNameContains("C");
            list.forEach(e-> {
                System.out.println(e);
            });

        System.out.println("---------------------------------------------");

            //methode 2
            List<Product> list3 = productRepository.search("%C%");
            System.out.println("-----------------");
            list3.forEach(e-> {
                System.out.println(e);
            });

        System.out.println("---------------------------------------------");
        //affichage de la liste des produits qui ont un prix superieur a 3000

            //methode 1
            List<Product> list2 = productRepository.findByPriceGreaterThan(3000);
            System.out.println("-----------------------");
            list2.forEach(e-> {
                System.out.println(e);
            });

        System.out.println("---------------------------------------------");

            //methode 2
            List<Product> list4 = productRepository.search(3200);
            System.out.println("-----------------");
            list4.forEach(e-> {
                System.out.println(e);
            });

        System.out.println("---------------------------------------------");

        //Test de quelques opérations de gestion de patients :
        /*
        - Ajouter des produits
        - Consulter tous les produits
        - Consulter un produit
        - Chercher des produits
        - Mettre à jour un patient
        - supprimer un produit
        */

        //Ajout
        productRepository.save(new Product(null,"Laghoviz",1000,1000));

        System.out.println("---------------------------------------------");

        //Consulter les produits
        productRepository.findAll().forEach(k-> {
            System.out.println(k);
        });

        System.out.println("---------------------------------------------");
        //Consulter un produit

        Optional<Product> produitOpt = Optional.ofNullable(productRepository.findById(5L));
        if (produitOpt.isPresent()) {
            System.out.println("Le produit est : " + produitOpt.get().getName());
        } else {
            System.out.println("Aucun produit avec l'ID 5 n'existe !");
        }


        System.out.println("---------------------------------------------");
        //Chercher des produits

        Optional<Product> optionalProduct = Optional.ofNullable(productRepository.findById(122L));
        if (optionalProduct.isPresent()) {
            System.out.println("le produit est " + optionalProduct.get().getName());
        } else {
            System.out.println("Aucun produit avec l'ID 122 n'existe !");
        }


        System.out.println("---------------------------------------------");
        //Mettre à jour un patient

        Product ProductModifie = productRepository.findById(Long.valueOf(6)).get();
        if(ProductModifie != null){
            ProductModifie.setName("Smart Laghoviz");
            ProductModifie.setPrice(3000);
            productRepository.save(ProductModifie);
            System.out.println("MODIFIE AVEC SUCCES");
        }
        else {
            System.out.println("Le produit a modifie n existe pas ");
        }

        System.out.println("---------------------------------------------");
        //supprimer un produit

        productRepository.deleteById(14L);
        System.out.println("le produit de ID egal a 14 est supprime : \n");

        System.out.println("---------------------------------------------");


        //affichage de la liste des produits

        List<Product> products0 = productRepository.findAll();
        products0.forEach(n ->{
            System.out.println(n);
        });
    }



}
