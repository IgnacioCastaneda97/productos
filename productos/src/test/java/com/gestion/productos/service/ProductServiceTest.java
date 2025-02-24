package com.gestion.productos.service;

import com.gestion.productos.model.Product;
import com.gestion.productos.repository.IProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTest {

    @Mock
    private IProductRepository productRepository;
    //Simulamos el repositorio

    @InjectMocks
    private ProductService productService;
    //Inyectamos el servicio con el mock

    @Test
    public void testGetProduct(){
        //Config el mock
        Product product1 = new Product(1L,"silla","gamer",200.00,10);
        Product product2 = new Product(2L,"silla_roja","gamer",200.00,10);
        Product product3 = new Product(3L,"silla rosa","gamer",200.00,10);
        List<Product> listaProductos = Arrays.asList(product1,product2,product3);
        when(productRepository.findAll()).thenReturn(listaProductos);

        //Probar el servicio
        List<Product> resultado = productService.getProduct();

        // verificar resultado
        assertEquals(3,resultado.size());
        assertEquals("silla",resultado.get(0).getName());
        verify(productRepository, times(1)).findAll();

    }

    @Test
    public void testSaveProduct(){
        //Config el mock
        Product product = new Product(1L,"silla","gamer",200.00,10);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        productService.saveProduct(product);

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void testDeleteProduct() {
        //Probar el servicio
        productService.deleteProduct(1L);

        //Verificar que el mtodo fue llamado
        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testFindProduct(){
        Product product = new Product(1L,"silla","gamer",200.00,10);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product resultado = productService.findProduct(1L);

        assertEquals("silla",resultado.getName());
        verify(productRepository, times(1)).findById(1L);
    }
    @Test
    public void testEditarProducto(){
        Product productExistente = new Product(1L,"silla","gamer",200.00,10);
        Product productActualizado = new Product(1L,"silla amarilla","gamer",500.00,100);
        when(productRepository.findById(1L)).thenReturn(Optional.of(productExistente));
        when(productRepository.save(any(Product.class))).thenReturn(productActualizado);
        //probar resultados
        productService.editarProducto(1L, productActualizado);
        //verificar resultado
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(any(Product.class));

    }
}
