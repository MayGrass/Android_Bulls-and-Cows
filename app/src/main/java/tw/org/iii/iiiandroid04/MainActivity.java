package tw.org.iii.iiiandroid04;

import androidx.appcompat.app.AppCompatActivity;

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
        String strInput= input.getText().toString();
        String result = checkAB(strInput);
        log.append(strInput + "=>" + result + "\n");
        input.setText("");
    }

    public void newGame(View view) {
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
