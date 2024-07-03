package page;

import lombok.Data;

@Data
public class Criteria {

    private int pageNum; // 페이지 번호
    private int amount; // 한 페이지에 출력하는 레코드 수
    private String keyword; // 검색 단어
    private int type; // 1은 제목 2는 아이디 3은 내용
    // 기본 생성자: 페이지 번호를 1로, 레코드 수를 10으로 초기화
    public Criteria() {
        this(1, 10);
    }

    // 페이지 번호와 레코드 수를 매개변수로 받는 생성자
    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }

    public int getStartRow() {
        return (pageNum - 1) * amount;
    }
    
}
