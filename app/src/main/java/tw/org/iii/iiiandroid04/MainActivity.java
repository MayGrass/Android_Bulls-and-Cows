package tw.org.iii.iiiandroid04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //產生答案
    private String createAnswer(int dig) {
        HashSet<Integer> ans = new HashSet<>();
        while (ans.size() < dig) {
            ans.add((int) (Math.random() * 10));
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : ans) {
            sb.append(i);
        }

        Log.v("DCH", sb.toString());
        return "sb";
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
