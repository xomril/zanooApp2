package com.example.zaznoo.listiners;

import com.example.zaznoo.models.ZaznooTable;

import java.util.List;

public interface ZaznooTableResponseListiner {
        void onZaznnoTableArrived(List<ZaznooTable> zaznooTableList);
}
