package tw.org.iii.iiiandroid04;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private String answer;
    private int dig = 3;
    private EditText input;
    private TextView log;
    private int counter; //預設為0
    private long lastTime = 0;
    private int temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = findViewById(R.id.input);
        log = findViewById(R.id.log);
        answer = createAnswer(3);
    }

    //產生答案
    private String createAnswer(int dig) {
        LinkedList<Integer> list = new LinkedList<>(); //LinkedList類似一個陣列
        for (int i=0; i<10; i++) list.add(i); //新增0~9
        Collections.shuffle(list); //shuffle 洗牌
        //建一個StringBuffer把list的物件全部存成一個字串
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<dig; i++) {
            sb.append(list.get(i));
        }
        Log.v("DCH",sb.toString());
        return sb.toString();
    }

    public void guess(View view) {
        counter++;
        String strInput= input.getText().toString();
        if (!isRIghtNumber(strInput)) {
            return;
        }
        String result = checkAB(strInput);
        log.append(counter + ":" + strInput + "=>" + result + "\n");
        //獲勝條件
        if (result.equals(dig + "A0B")) { //字串內容必須用equals比對 ==是比較物件
            showDialog(true);
        }
        // 失敗條件
        else if (counter == 10) {
            showDialog(false);
        }
        input.setText("");
    }

    //確認輸入是否符合規則，數字與位數
    private boolean isRIghtNumber(String g) {
        return g.matches("^[0-9]{"+dig+"}");
    }

    //新增提示訊息
    private void showDialog(boolean isWinner){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(isWinner?"WINNER":"Loser")
                .setMessage(isWinner?"恭喜猜對":"答案是"+answer)
                //新增按鈕 按完開新局
                .setPositiveButton("重開新局", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        newGame(null);
                    }
                })
                .setCancelable(false) //不能取消，強迫按按鈕，按其他地方沒反應
                .create();
        /*
        builder.setTitle("Title")
        builder.setMessage("");
        alterDialog = builder.create();
        */
        alertDialog.show();
    }

    public void newGame(View view) {
        counter = 0;
        input.setText("");
        log.setText("");
        answer = createAnswer(dig);
    }

    public void setting(View view) {
        String[] items = {"3", "4", "5", "6"};
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("要猜幾個數字呢?")
                .setSingleChoiceItems(items,dig -3, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        temp = which;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dig = temp + 3;
                        newGame(null);
                    }
                })
                .create();
        alertDialog.show();
    }

    //觀察用，實際上會自動執行destroy，忘記可以去看生命週期
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("DCH", "onDestroy");
    }

    //finish()實際上不用特別寫一個
    @Override
    public void finish() {
        super.finish();
        Log.v("DCH", "finish");
    }

    //控制按返回鍵的動作
    @Override
    public void onBackPressed() {
        //如果三秒內再按一次就離開
        if (System.currentTimeMillis() - lastTime > 3*1000) {
            lastTime = System.currentTimeMillis();
            Toast.makeText(this, "再按一次返回離開", Toast.LENGTH_SHORT).show(); //在下方出現短暫的提示訊息
        }
        else
            super.onBackPressed();
    }

    public void exit(View view) {
        //確認是否離開的訊息
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage("確定要離開嗎?")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        alertDialog.show();
    }

    private String checkAB(String guess) {
        int a, b; a = b = 0;
        for (int i=0; i<guess.length(); i++) {
            if (guess.charAt(i) == answer.charAt(i)) //字串比對第i碼對答案第i碼
                a++;
            else if (answer.indexOf(guess.charAt(i)) != -1) //indexOf只有在字串中沒出現過就會回傳-1
                b++;
        }
        return a + "A" + b + "B";
    }
}
