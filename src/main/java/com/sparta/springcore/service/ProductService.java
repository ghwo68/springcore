package com.sparta.springcore.service;

import com.sparta.springcore.model.Product;
import com.sparta.springcore.dto.ProductMypriceRequestDto;
import com.sparta.springcore.repository.ProductRepository;
import com.sparta.springcore.dto.ProductRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired //생성자가 지금과 같이 하나라면 생략 가능!
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository; //위의 변수 productRepository와 productRepository의 이름이 동일하기 때문에 구분하기 위해 this를 선언
    }

    public Product createProduct(ProductRequestDto requestDto){
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Product product = new Product(requestDto);

        productRepository.save(product);

        return product;
    }

    public Product updateProduct(Long id, ProductMypriceRequestDto requestDto){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );

        int myPrice = requestDto.getMyprice();
        product.setMyprice(myPrice);
        productRepository.save(product);

        return product;
    }

    public List<Product> getProducts(){
        List<Product> products = productRepository.findAll();

        return products;
    }
}
