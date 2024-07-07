package page;

import lombok.Data;

//ページ機能を作る時にページ番号と掲示文の量を探して検索する時にキーワードとタイプを受け入れてくれる
@Data
public class Criteria {

    private int pageNum;// ページ番号
    private int amount; // 1ページに出力するレコードの数
    private String keyword; // 検索ワード
    private int type; // 1はタイトル、2はID、3は内容
 // 基本生成者: ページ番号を 1 に、レコード数を 10 に初期化
    public Criteria() {
        this(1, 10);
    }

 // ページ番号とレコード数をパラメーターとして受け取る作成者
    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }

    public int getStartRow() {
        return (pageNum - 1) * amount;
    }
    
}
