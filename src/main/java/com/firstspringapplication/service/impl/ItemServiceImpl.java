package com.firstspringapplication.service.impl;

import com.firstspringapplication.dao.ItemDAO;
import com.firstspringapplication.model.Item;
import com.firstspringapplication.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemDAO itemDAO;

    @Override
    public Item save(Item item) {
        if (item.getId() == null && itemDAO.findByName(item.getName()) == null) {
            return itemDAO.save(item);
        }
        throw new RuntimeException("Cant save item");
    }

    @Override
    public Item update(Item item) {
        if (itemDAO.findById(item.getId()).isPresent() && item.getId() != null) {
            return itemDAO.save(item);
        }
        throw new RuntimeException("Cant update item");
    }

    @Override
    public Item findById(Integer id) {
        Optional<Item> item = itemDAO.findById(id);
        return item.orElse(null);
    }

    @Override
    public List<Item> findAllItems() {
        return itemDAO.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        itemDAO.deleteById(id);
    }
}
