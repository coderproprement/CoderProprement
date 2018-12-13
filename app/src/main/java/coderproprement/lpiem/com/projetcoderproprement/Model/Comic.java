package coderproprement.lpiem.com.projetcoderproprement.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Comic implements ComicInterface{
    private String title;
    private String description;
    private String diamondCode;
    private Date parutionDate;
    private float price;
    private int pageCount;
    private String imageUrl;
    private List<ComicCreator> comicCreatorsList;

    public Comic(String title, String description, String diamondCode, Date parutionDate, float price, int pageCount, String imageUrl, List<ComicCreator> comicCreatorsList) {
        this.title = title;
        this.description = description;
        this.diamondCode = diamondCode;
        this.parutionDate = parutionDate;
        this.price = price;
        this.pageCount = pageCount;
        this.imageUrl = imageUrl;
        this.comicCreatorsList = comicCreatorsList;
    }

    public Comic() {
        this.comicCreatorsList = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiamondCode() {
        return diamondCode;
    }

    public void setDiamondCode(String diamondCode) {
        this.diamondCode = diamondCode;
    }

    public Date getParutionDate() {
        return parutionDate;
    }

    public void setParutionDate(Date parutionDate) {
        this.parutionDate = parutionDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<ComicCreator> getComicCreatorsList() {
        return comicCreatorsList;
    }

    public void setComicCreatorsList(List<ComicCreator> comicCreatorsList) {
        this.comicCreatorsList = comicCreatorsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comic comic = (Comic) o;
        return Float.compare(comic.price, price) == 0 &&
                pageCount == comic.pageCount &&
                Objects.equals(title, comic.title) &&
                Objects.equals(description, comic.description) &&
                Objects.equals(diamondCode, comic.diamondCode) &&
                Objects.equals(parutionDate, comic.parutionDate) &&
                Objects.equals(imageUrl, comic.imageUrl) &&
                Objects.equals(comicCreatorsList, comic.comicCreatorsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, diamondCode, parutionDate, price, pageCount, imageUrl, comicCreatorsList);
    }

    @Override
    public String toString() {
        return "Comic{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", diamondCode='" + diamondCode + '\'' +
                ", parutionDate=" + parutionDate +
                ", price=" + price +
                ", pageCount=" + pageCount +
                ", imageUrl='" + imageUrl + '\'' +
                ", comicCreatorsList=" + comicCreatorsList +
                '}';
    }

    @Override
    public void addCreator(ComicCreator c) {
        if(isCreatorPresent(c)==false){
            this.comicCreatorsList.add(c);
        }else{
            System.out.println("Gestion du comic : "+"Le créateur est déjà présent dans la liste!");
        }
    }

    @Override
    /**
     * Vérifie si un créateur d'un comic est présent ou non dans la liste
     */
    public boolean isCreatorPresent(ComicCreator c) {
        for(ComicCreator creator:this.comicCreatorsList){
            if(creator.equals(c)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeCreator(ComicCreator c) {
        if(isCreatorPresent(c)==true){
            this.comicCreatorsList.remove(c);
        }else{
            System.out.println("Gestion du comic : "+"Le créateur n'est pas présent dans la liste!");
        }
    }
}
