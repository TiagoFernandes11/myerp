package br.erp.myerp.domain.product.controller;

import br.erp.myerp.domain.product.dto.ProductCreateDTO;
import br.erp.myerp.domain.product.dto.ProductResponseDTO;
import br.erp.myerp.domain.product.dto.ProductUpdateDTO;
import br.erp.myerp.domain.product.service.ProductService;
import br.erp.myerp.common.security.response.Response;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final int PAGE_SIZE = 20;

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll(@RequestParam(name = "filter", required = false, defaultValue = "") String filter,
                                                            @RequestParam(name = "value", required = false, defaultValue = "") String value,
                                                            @RequestParam(name = "pageNum", required = false, defaultValue = "0") int pageNum){
        List<ProductResponseDTO> products = productService.findAll(filter, value, pageNum, PAGE_SIZE).getContent();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findProduct(@PathVariable Long id){
        ProductResponseDTO productResponseDTO = productService.find(id);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
    }

    @GetMapping("/by-sku/{sku}")
    public ResponseEntity<ProductResponseDTO> findProduct(@PathVariable String sku){
        ProductResponseDTO productResponseDTO = productService.find(sku);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
    }

    @PostMapping
    public ResponseEntity<Response> createProduct(@RequestBody @Valid ProductCreateDTO dto){
        productService.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response(HttpStatus.CREATED, "Product was successfully created"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductUpdateDTO dto){
        productService.updateProduct(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, "Product id: " + id + " was successfully updated"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, "Product id: " + id + " was successfully deleted"));
    }
}
