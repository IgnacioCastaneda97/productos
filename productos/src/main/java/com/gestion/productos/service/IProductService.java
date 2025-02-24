package com.gestion.productos.service;

import com.gestion.productos.model.Product;

import java.util.List;

public interface IProductService {


    // metodo para traer o ver productos
    public List<Product> getProduct();

    // metodo para guardar o dar de alta productos
    public void saveProduct(Product producto);

    // metodo para borrar un producto
    // recibe como parametro un ID
    public void deleteProduct(long id);


    //metodo para buscar una producto
    public Product findProduct(long id);

    // metodo para modificar una producto
    void editarProducto(Long id, Product productoActualizado);
}
