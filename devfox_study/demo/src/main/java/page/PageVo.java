package page;

import lombok.Getter;
import lombok.ToString;

//クリテリアから受け取った値とスタートページなどの変数を一緒に使用してページ機能を具現するための計算をするVo
@Getter
@ToString
public class PageVo {

    private int startPage; // 開始ページ番号
    private int endPage; // 最後のページ番号
    private int total; // 全項目数
    private Criteria cri; // 検索条件オブジェクト
    //1ページに入る量と(例1~10)と総掲示文(1~100)の量を入れて計算してくれるメソッド
    public PageVo(Criteria cri, int total) {
        this.cri = cri;
        this.total = total;

        // 実際の最後のページの計算
        int realEnd = (int) Math.ceil((double) total / cri.getAmount());

        // エンドページ補正
        this.endPage = Math.min((int) Math.ceil(cri.getPageNum() / 10.0) * 10, realEnd);

        // 開始ページの計算
        this.startPage = Math.max(1, this.endPage - 9);
    }
    
}
