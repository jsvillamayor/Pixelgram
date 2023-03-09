package com.example.com.cognizant.Model;

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

    /*i'm just adding a comment here so that i can merge to develop*/

    public PageOfItems() {

    }

    public List<T> getItems() {
        return this.items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public boolean getHasNext() {
        return this.hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public int getTotalElements() {
        return this.totalElements;
    }

    public void setTotalElements(int totalItems) {
        this.totalElements = totalItems;
    }
}


