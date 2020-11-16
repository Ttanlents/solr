package entity;


import org.apache.solr.client.solrj.beans.Field;

import java.util.List;
import java.util.Objects;

/**
 * @author 余俊锋
 * @date 2020/11/13 10:51
 * @Description
 */

public class Goods {
    @Field
    private String id;
    @Field
    private String name;
    @Field
    private String title;
    @Field
    private Double price;
    @Field
    private String pic;

    @Field  //整合域  使用集合
    private List<String> keywords;




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }



    public String getPic() {
        return pic;
    }



    public void setPic(String pic) {
        this.pic = pic;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return id == goods.id &&
                Objects.equals(name, goods.name) &&
                Objects.equals(title, goods.title) &&
                Objects.equals(price, goods.price) &&
                Objects.equals(pic, goods.pic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, title, price, pic);
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", pic='" + pic + '\'' +
                ", keywords=" + keywords +
                '}';
    }
}
