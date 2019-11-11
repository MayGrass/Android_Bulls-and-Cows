package tw.org.iii.iiiandroid04;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
        String result = checkAB(strInput);
        log.append(strInput + "=>" + result + "\n");
        //獲勝條件
        if (result.equals(dig + "A0B")) { //字串內容必須用equals比對 ==是比較物件
            showDialog(true);
        }
        // 失敗條件
        else if (counter == 5) {
            showDialog(false);
        }
        input.setText("");
    }

    //新增提示訊息
    private void showDialog(boolean isWinner){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(isWinner?"WINNER":"Loser")
                .setMessage(isWinner?"恭喜老爺":"謎底是"+answer)
                //新增按鈕 按完開新局
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        newGame(null);
                    }
                })
                .setCancelable(false) //不能取消，只能按OK，按其他地方沒反應
                .create();
        /*
        builder.setTitle("Title")
        builder.setMessage("");
        alterDialog = builder.create();
        */

        alertDialog.show();
    }

    public void newGame(View view) {
        Log.v("DCH", "new game");
    }

    public void setting(View view) {
    }

    public void exit(View view) {
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
