package pojo;

import java.util.ArrayList;
import java.util.List;

public class UsersListResponsePOJO {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private Support support;
    private List<User> data;

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }

        public List<User> getData() {
        return data;
    }

    public void setData(ArrayList<User> data) {
        this.data = data;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "page=" + page +
                ", per_page=" + per_page +
                ", total=" + total +
                ", total_pages=" + total_pages +
                ", data=" + data +
                ", support=" + support +
                '}';
    }
}
