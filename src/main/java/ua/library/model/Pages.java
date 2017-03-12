package ua.library.model;

import ua.library.model.entities.Book;

import java.util.List;

public class Pages {

    private static final Integer DEFAULT_ROWS = 6;

    private Long booksCount;
    private Integer selectPage;
    private Integer rows;
    private Long pageCount;
    private List<Book> books;

    public Pages(){
        rows = DEFAULT_ROWS;
    }

    public Integer getFrom(){
        return selectPage * rows - rows;
    }

    public Integer getTo(){
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Integer getSelectPage() {
        return selectPage;
    }

    public void setSelectPage(Integer selectPage) {
        this.selectPage = selectPage;
    }

    public Long getBooksCount() {
        return booksCount;
    }

    public void setBooksCount(Long booksCount) {
        this.booksCount = booksCount;

        if(booksCount % rows == 0){
            pageCount = booksCount/rows;
        } else {
            pageCount = booksCount/rows + 1;
        }
    }

    @Override
    public String toString() {
        return "Pages{" +
                "selectPage=" + selectPage +
                ", rows=" + rows +
                ", pageCount=" + pageCount +
                ", books=" + books +
                '}';
    }
}
