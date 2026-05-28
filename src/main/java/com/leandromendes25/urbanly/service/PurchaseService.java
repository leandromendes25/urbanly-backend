package com.leandromendes25.urbanly.service;

import com.leandromendes25.urbanly.dtos.request.PurchaseRequestDTO;
import com.leandromendes25.urbanly.dtos.response.PurchaseResponseDTO;
import com.leandromendes25.urbanly.entity.Cart;
import com.leandromendes25.urbanly.entity.Client;
import com.leandromendes25.urbanly.entity.Purchase;
import com.leandromendes25.urbanly.entity.PurchaseItem;
import com.leandromendes25.urbanly.entity.enums.PurchaseStatus;
import com.leandromendes25.urbanly.exceptions.ResourceNotFoundException;
import com.leandromendes25.urbanly.exceptions.UnauthorizedException;
import com.leandromendes25.urbanly.mapper.PurchaseMapper;
import com.leandromendes25.urbanly.repository.CartRepository;
import com.leandromendes25.urbanly.repository.ClientRepository;
import com.leandromendes25.urbanly.repository.PurchaseRepository;
import com.leandromendes25.urbanly.security.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PurchaseService {
    private final ClientRepository clientRepository;

    private final CartRepository cartRepository;

    private final PurchaseRepository purchaseRepository;

    public PurchaseResponseDTO checkout() {
        String email = SecurityUtils.getEmailFromContext();
        Client client = clientRepository.findByEmail(email).orElseThrow(() ->
                                new ResourceNotFoundException("Cliente não encontrado"));
        Cart cart = cartRepository.findByClient(client)
                        .orElseThrow(() -> new ResourceNotFoundException("Carrinho não encontrado"));
        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Carrinho vazio");}

        Purchase purchase = new Purchase();
        purchase.setClient(client);
        purchase.setStatus(PurchaseStatus.PENDING);
        List<PurchaseItem> purchaseItems =
                cart.getItems()
                        .stream()
                        .map(cartItem -> {
                            PurchaseItem purchaseItem = new PurchaseItem();
                            purchaseItem.setPurchase(purchase);
                            purchaseItem.setProduct(
                                    cartItem.getProduct()
                            );
                            purchaseItem.setQuantity(
                                    cartItem.getQuantity()
                            );
                            purchaseItem.setPriceAtPurchase(
                                    cartItem.getPriceAtMoment()
                            );
                            return purchaseItem;
                        }).toList();

        purchase.setItems(purchaseItems);
        purchase.setTotalAmount(
                purchase.getTotalAmount()
        );
        Purchase savedPurchase = purchaseRepository.save(purchase);
        cart.getItems().clear();
        cartRepository.save(cart);
        return PurchaseMapper.toResponse(
                savedPurchase
        );
    }

    public List<PurchaseResponseDTO> getAllPurchases() {
        String email = SecurityUtils.getEmailFromContext();
        Client client = clientRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Cliente não encontrado"));
        return purchaseRepository.findByClientOrderByCreatedAtDesc(client)
                .stream()
                .map(PurchaseMapper::toResponse)
                .toList();
    }

    public PurchaseResponseDTO getPurchaseById(Long purchaseId) {
        String email = SecurityUtils.getEmailFromContext();
        Client client = clientRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Cliente não encontrado"));
        Purchase purchase = purchaseRepository.findById(purchaseId).orElseThrow(() ->
                new ResourceNotFoundException("Compra não encontrada"));
        if (!purchase.getClient().getId().equals(client.getId())) {
            throw new UnauthorizedException("Essa compra não pertence ao cliente logado");
        }
        return PurchaseMapper.toResponse(purchase);
    }
}
