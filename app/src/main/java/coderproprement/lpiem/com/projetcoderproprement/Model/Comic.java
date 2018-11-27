package coderproprement.lpiem.com.projetcoderproprement.Model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Comic {
    private String title;
    private int issueNumber;
    private String description;
    private String diamonCode;
    private Date parutionDate;
    private float price;
    private int pageCount;
    private String imageUrl;
    private List<ComicCreator> comicCreatorsList;

    public Comic(String title, int issueNumber, String description, String diamonCode, Date parutionDate, float price, int pageCount, String imageUrl, List<ComicCreator> comicCreatorsList) {
        this.title = title;
        this.issueNumber = issueNumber;
        this.description = description;
        this.diamonCode = diamonCode;
        this.parutionDate = parutionDate;
        this.price = price;
        this.pageCount = pageCount;
        this.imageUrl = imageUrl;
        this.comicCreatorsList = comicCreatorsList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiamonCode() {
        return diamonCode;
    }

    public void setDiamonCode(String diamonCode) {
        this.diamonCode = diamonCode;
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
        return issueNumber == comic.issueNumber &&
                Float.compare(comic.price, price) == 0 &&
                pageCount == comic.pageCount &&
                Objects.equals(title, comic.title) &&
                Objects.equals(description, comic.description) &&
                Objects.equals(diamonCode, comic.diamonCode) &&
                Objects.equals(parutionDate, comic.parutionDate) &&
                Objects.equals(imageUrl, comic.imageUrl) &&
                Objects.equals(comicCreatorsList, comic.comicCreatorsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, issueNumber, description, diamonCode, parutionDate, price, pageCount, imageUrl, comicCreatorsList);
    }

    @Override
    public String toString() {
        return "Comic{" +
                "title='" + title + '\'' +
                ", issueNumber=" + issueNumber +
                ", description='" + description + '\'' +
                ", diamonCode='" + diamonCode + '\'' +
                ", parutionDate=" + parutionDate +
                ", price=" + price +
                ", pageCount=" + pageCount +
                ", imageUrl='" + imageUrl + '\'' +
                ", comicCreatorsList=" + comicCreatorsList +
                '}';
    }
}
