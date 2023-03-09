package com.cognizant.uupostcrud.Model;

import java.util.List;

public class PageOfItems<T> {
    private List<T> items;
    private boolean hasNext;
    private int totalElements;

    public PageOfItems(List<T> items, boolean hasNext, int totalElements) {
        this.items = items;
        this.hasNext = hasNext;
        this.totalElements = totalElements;
    }

    public PageOfItems() {

    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalItems) {
        this.totalElements = totalItems;
    }
}


