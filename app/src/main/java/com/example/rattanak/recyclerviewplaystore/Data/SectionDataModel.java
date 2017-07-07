package com.example.rattanak.recyclerviewplaystore.Data;

import java.util.ArrayList;

/**
 * Created by rattanak on 6/30/17.
 */
/**
 * fuction to combine single data to section data
 * 1. add to single data via Section data model
*/
public class SectionDataModel {
    private String headerTitle;
    private ArrayList<SingleItemModel> allItemsInSection;

    //set constructor and array of single item
    public void SectionDataModel(String headerTitle, ArrayList<SingleItemModel> allItemsInSection) {
        this.headerTitle = headerTitle;
        this.allItemsInSection = allItemsInSection;
    }



    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public ArrayList<SingleItemModel> getAllItemsInSection() {
        return allItemsInSection;
    }

    public void setAllItemsInSection(ArrayList<SingleItemModel> allItemsInSection) {
        this.allItemsInSection = allItemsInSection;
    }


}
