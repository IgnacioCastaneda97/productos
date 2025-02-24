package com.gestion.productos.service;

import com.gestion.productos.model.Product;
import com.gestion.productos.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> getProduct() {
        List<Product>listaProductos = productRepository.findAll();
        return listaProductos;
    }

    @Override
    public void saveProduct(Product producto) {
        productRepository.save(producto);
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);

    }

    @Override
    public Product findProduct(long id) {
        Product producto = productRepository.findById(id).orElse(null);
        return producto;
    }

    @Override
    public void editarProducto(Long id, Product productoActualizado) {
            // busque producto por id
        Product producto = productRepository.findById(id).orElse(null);
            if (producto!=null){
                // update de los datos de producto
                producto.setName(productoActualizado.getName());
                producto.setDescription(productoActualizado.getDescription());
                producto.setPrice(productoActualizado.getPrice());
                producto.setStock(productoActualizado.getStock());

                // guardar el nuevo producto en bd
                productRepository.save(producto);
            }else {
                System.out.println("no se encontro producto con "+id+" ese id");
            }
    }
}
