package com.example.project;

public class WikiData {
    String threeLetterSymbol;
    String oneLetterSymbol;
    String category;
    String wikipage;
    String img;

    public WikiData(String threeLetterSymbol, String oneLetterSymbol, String category, String wikipage, String img) {
        this.threeLetterSymbol = threeLetterSymbol;
        this.oneLetterSymbol = oneLetterSymbol;
        this.category = category;
        this.wikipage = wikipage;
        this.img = img;
    }

    public String getThreeLetterSymbol() {
        return threeLetterSymbol;
    }

    public void setThreeLetterSymbol(String threeLetterSymbol) {
        this.threeLetterSymbol = threeLetterSymbol;
    }

    public String getOneLetterSymbol() {
        return oneLetterSymbol;
    }

    public void setOneLetterSymbol(String oneLetterSymbol) {
        this.oneLetterSymbol = oneLetterSymbol;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWikipage() {
        return wikipage;
    }

    public void setWikipage(String wikipage) {
        this.wikipage = wikipage;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "WikiData{" +
                "threeLetterSymbol='" + threeLetterSymbol + '\'' +
                ", oneLetterSymbol='" + oneLetterSymbol + '\'' +
                ", category='" + category + '\'' +
                ", wikipage='" + wikipage + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
