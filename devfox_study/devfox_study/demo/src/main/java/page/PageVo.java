package page;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageVo {

    private int startPage; // 시작 페이지 번호
    private int endPage; // 끝 페이지 번호
    private boolean prev; // 이전 페이지 여부
    private boolean next; // 다음 페이지 여부
    private int total; // 전체 항목 수
    private Criteria cri; // 검색 조건 객체

    public PageVo(Criteria cri, int total) {
        this.cri = cri;
        this.total = total;

        // 끝 페이지 계산
        this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;

        // 시작 페이지 계산
        this.startPage = this.endPage - 9;

        // 실제 끝 페이지 계산
        int realEnd = (int) Math.ceil((double) total / cri.getAmount());

        // 계산된 끝 페이지 보정
        if (realEnd < this.endPage) {
            this.endPage = realEnd;
        }

        // 이전 페이지 여부 계산
        this.prev = this.startPage > 1;

        // 다음 페이지 여부 계산
        this.next = this.endPage < realEnd;
    }
}
