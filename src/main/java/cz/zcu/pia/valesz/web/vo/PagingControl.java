package cz.zcu.pia.valesz.web.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple class for passing paging-related parameters to JSP.
 */
public class PagingControl {

    /**
     * Number of the current page. Starts at 0.
     */
    private int currentPage;

    /**
     * Number of the last page.
     */
    private int lastPageNum;

    /**
     * Default constructor initializes object to 1 page.
     */
    public PagingControl() {
        this.currentPage = 0;
        this.lastPageNum = 0;
    }

    public PagingControl(int currentPage, int lastPageNum) {
        this.currentPage = currentPage;
        this.lastPageNum = lastPageNum;
    }

    /**
     * Returns true if the current page is 0.
     * @return True if the current page is the first one.
     */
    public boolean isFirstPage() {
        return getCurrentPage() == 0;
    }

    /**
     * Returns true if the current page is the last page.
     * @return True if the curent page is the last one.
     */
    public boolean isLastPge() {
        return getCurrentPage() == getLastPageNum();
    }

    /**
     * Returns the number of the current page.
     * @return Current page.
     */
    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getLastPageNum() {
        return lastPageNum;
    }

    public void setLastPageNum(int lastPageNum) {
        this.lastPageNum = lastPageNum;
    }

    /**
     * Returns the number of page which is after the current one.
     * If the current one is the last, number of the current page is returned.
     *
     * @return
     */
    public int getNextPage() {
        if(isLastPge()) {
            return currentPage;
        } else {
            return currentPage+1;
        }
    }

    /**
     * Returns the number of the page which is before the current one.
     * If the current one is the first, number of the current page is returned.
     * @return
     */
    public int getPrevPage() {
        if(isFirstPage()) {
            return currentPage;
        } else {
            return currentPage-1;
        }
    }

    /**
     * Creates a list of numbers from 1 to lastPage and returns it.
     *
     * @return List of page numbers.
     */
    public List<Integer> getPageNumbers() {
        List<Integer> pageNums = new ArrayList<>();
        for (int i = 0; i <= getLastPageNum(); i++) {
            pageNums.add(i);
        }

        return pageNums;
    }
}
