package com.gestion.productos.controller;

import com.gestion.productos.model.Product;
import com.gestion.productos.service.IProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private IProductService productService;
    //Simulamos el servicio

    @InjectMocks
    private ProductController productController;
    //Inyectamos el controlador con el mock

    @Test
    public void testGetProduct(){
        //config el mock
        Product product1 = new Product(1L,"silla","gamer",200.00,10);
        Product product2 = new Product(2L,"silla_roja","gamer",200.00,10);
        Product product3 = new Product(3L,"silla rosa","gamer",200.00,10);
        List<Product> listaProductos = Arrays.asList(product1,product2,product3);
        when(productService.getProduct()).thenReturn(listaProductos);

        //Probar el servicio
        List<Product> resultado = productService.getProduct();

        // verificar resultado
        assertEquals(3,resultado.size());
        assertEquals("silla",resultado.get(0).getName());
        verify(productService, times(1)).getProduct();

    }

    @Test
    public void testCrearProduct() {
        //Config el mock
        Product product1 = new Product(1L, "silla", "gamer", 200,10);
        doNothing().when(productService).saveProduct(any(Product.class));

        //Ejecutar el mtodo del controlador
        ResponseEntity<String> respuesta = productController.saveProducto(product1);

        //Verificar el resultado
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("El producto fue creado con exito", respuesta.getBody());
        verify(productService, times(1)).saveProduct(any(Product.class));
    }
    @Test
    public void testDeleteProducto() {
        //Config el mock
        doNothing().when(productService).deleteProduct(1L);

        //Ejecutar el mtodo del controlador
        ResponseEntity<String> respuesta = productController.deleteProduct(1L);

        //Verificar el resultado
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("El producto se eliminó correctamente", respuesta.getBody());
        verify(productService, times(1)).deleteProduct(1L);
    }
    @Test
    public void testEditarProduc(){
        Product productActualizado = new Product(1L,"silla","gamer",200.00,10);
        doNothing().when(productService).editarProducto(1L, productActualizado);

        ResponseEntity<String> respuesta = productController.editarProduct(1L,productActualizado);

        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals( "se Actualizó correctamente",respuesta.getBody());
        verify(productService, times(1)).editarProducto(1L, productActualizado);

    }

    @Test
    public void testGetProductById(){
        // config mock
        Product product = new Product(1l,"silla","gamer",200.00,10);
        when(productService.findProduct(1L)).thenReturn(product);

        //Ejecutar el mtodo del controlador
        Product respuesta = productController.findProduct(1L);

        //Verificar el resultado
        assertNotNull(respuesta);
        assertEquals(1L, respuesta.getIdProducto());
        assertEquals("silla", respuesta.getName());
        verify(productService, times(1)).findProduct(1L);

    }

}
