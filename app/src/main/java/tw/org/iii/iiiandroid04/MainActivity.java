package tw.org.iii.iiiandroid04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }

    public void newGame(View view) {
        createAnswer(4);
    }

    public void setting(View view) {
    }

    public void exit(View view) {
    }
}
