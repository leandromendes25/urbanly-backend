package com.leandromendes25.urbanly.service;

import com.leandromendes25.urbanly.dtos.request.CartItemRequestDTO;
import com.leandromendes25.urbanly.dtos.request.UpdateCartItemRequestDTO;
import com.leandromendes25.urbanly.dtos.response.CartResponseDTO;
import com.leandromendes25.urbanly.entity.Cart;
import com.leandromendes25.urbanly.entity.CartItem;
import com.leandromendes25.urbanly.entity.Client;
import com.leandromendes25.urbanly.entity.Product;
import com.leandromendes25.urbanly.exceptions.ResourceNotFoundException;
import com.leandromendes25.urbanly.mapper.CartMapper;
import com.leandromendes25.urbanly.repository.CartRepository;
import com.leandromendes25.urbanly.repository.ClientRepository;
import com.leandromendes25.urbanly.repository.ProductRepository;
import com.leandromendes25.urbanly.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    public CartResponseDTO getCart(){
        String email = SecurityUtils.getEmailFromContext();
        Client client = clientRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email não encontrado"));
        Cart cart = cartRepository.findByClient(client).orElseThrow(() -> new ResourceNotFoundException("Carrinho não encontrado"));
        return CartMapper.toResponse(cart);
    }
    public CartResponseDTO addItem(CartItemRequestDTO cartItemRequestDTO){
        String email = SecurityUtils.getEmailFromContext();
        Client client = clientRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email não encontrado"));
        Cart cart = cartRepository.findByClient(client).orElseThrow(() -> new ResourceNotFoundException("Carrinho não encontrado"));
        Product product = productRepository.findById(cartItemRequestDTO.productId()).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
        if (product.getStock() < cartItemRequestDTO.quantity()){
            throw new ResourceNotFoundException("Estoque insuficiente");
        }
        //vai estar pegando o primeiro produto que encontrar que já estiver na lista
        Optional<CartItem> existingItem = cart.getItems().stream().filter(item -> item.getProduct().getId().equals(cartItemRequestDTO.productId())).findFirst();
        //quando achar, vai alterar a quantidade
        if (existingItem.isPresent()){
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + cartItemRequestDTO.quantity());
        } else {
            //se não vai criar um cartItem do zero
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(cartItemRequestDTO.quantity());
            cartItem.setPriceAtMoment(
                    product.getPrice()
            );
            cart.addItem(cartItem);
        }
        cartRepository.save(cart);
        return CartMapper.toResponse(cart);
    }
    public CartResponseDTO updateItemQuantity(Long itemId, UpdateCartItemRequestDTO updateItemRequestDTO){
        String email = SecurityUtils.getEmailFromContext();
        Client client = clientRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email não encontrado"));
        Cart cart = cartRepository.findByClient(client).orElseThrow(() -> new ResourceNotFoundException("Carrinho não encontrado"));
        //do carrinho, está pegando o itemCart
        CartItem item = cart.getItems().stream().filter(cartItem ->
                                cartItem.getId()
                                        .equals(itemId)
                        )
                        .findFirst()
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Item não encontrado"
                                )
                        );
        Product product = item.getProduct();
        if(product.getStock() < updateItemRequestDTO.quantity()){

            throw new RuntimeException(
                    "Estoque insuficiente"
            );
        }

        item.setQuantity(updateItemRequestDTO.quantity());

        cartRepository.save(cart);

        return CartMapper.toResponse(cart);
    }

    public CartResponseDTO removeItem(Long itemId){
        String email = SecurityUtils.getEmailFromContext();
        Client client = clientRepository.findByEmail(email).orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Cliente não encontrado"
                                )
                        );
        Cart cart =
                cartRepository.findByClient(client)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Carrinho não encontrado"
                                )
                        );

        CartItem item =
                cart.getItems()
                        .stream()
                        .filter(cartItem ->
                                cartItem.getId()
                                        .equals(itemId)
                        )
                        .findFirst()
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Item não encontrado"
                                )
                        );

        cart.removeItem(item);

        cartRepository.save(cart);

        return CartMapper.toResponse(cart);
    }

}
