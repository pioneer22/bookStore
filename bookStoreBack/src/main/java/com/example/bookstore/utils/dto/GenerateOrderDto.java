package main.java.com.example.bookstore.utils.dto;

import java.util.Arrays;
import java.util.List;

/**
 * 确认订单时 DTO
 */
public class GenerateOrderDto {

    private List<CarBook> carBookId;

    private Object[] orderMess;

    public List<CarBook> getCarBookId() {
        return carBookId;
    }

    public void setCarBookId(List<CarBook> carBookId) {
        this.carBookId = carBookId;
    }

    public Object[] getOrderMess() {
        return orderMess;
    }

    public void setOrderMess(Object[] orderMess) {
        this.orderMess = orderMess;
    }

    public static class CarBook{
        private Integer bookId;

        private Integer count;

        public Integer getBookId() {
            return bookId;
        }

        public void setBookId(Integer bookId) {
            this.bookId = bookId;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return "CarBook{" +
                    "bookId=" + bookId +
                    ", count=" + count +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GenerateOrderDto{" +
                "carBookId=" + carBookId +
                ", orderMess=" + Arrays.toString(orderMess) +
                '}';
    }
}
