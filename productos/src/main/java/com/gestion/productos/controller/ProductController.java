package com.gestion.productos.controller;

import com.gestion.productos.model.Product;
import com.gestion.productos.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private IProductService productService;

    // EndPoint para obtener todos los productos
    @GetMapping("/products/traer")
    public List<Product>getProduct(){
        return productService.getProduct();
    }

    @GetMapping("/products/buscar/{idProducto}")
    public Product findProduct(@PathVariable Long idProducto){

        return  productService.findProduct(idProducto);
    }

    //endPoint para crear un Producto
    @PostMapping("/product/crear")
    public ResponseEntity<String> saveProducto(@RequestBody Product product){
        productService.saveProduct(product);
        return ResponseEntity.ok("El producto fue creado con exito");
    }

    //EndPoint para borrar un producto
    @DeleteMapping("/product/borrar/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok( "El producto se eliminó correctamente");
    }

    //EndPoint para modificar un producto
    @PutMapping("/product/editar/{id}")
    public ResponseEntity<String>editarProduct(@PathVariable Long id,@RequestBody Product productActualizado){
        productService.editarProducto(id,productActualizado);
        return ResponseEntity.ok("se Actualizó correctamente");
    }

}
