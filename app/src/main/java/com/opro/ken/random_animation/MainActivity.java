package com.opro.ken.random_animation;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private View m_view_go;
    private Button m_Start;
    private TextView nba_team_name;
    private TypedArray nba_logo;
    private int nba_logo_count;
    private Handler m_handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initnbalogodrawable();
    }

    private void initnbalogodrawable() {
        nba_logo = getNba_logo();
        nba_logo_count = getNba_logo().length();
        m_view_go.setBackground(nba_logo.getDrawable(0));
    }


    private void initView() {
        m_view_go = findViewById(R.id.random);
        nba_team_name = (TextView) findViewById(R.id.team_name);
        m_Start = (Button) findViewById(R.id.btn_Start);
    }

    public TypedArray getNba_logo() {
        TypedArray nba_logo = getResources().obtainTypedArray(R.array.nba_logo);
        return nba_logo;
    }

    public void click(View view) {

        m_handler.post(mStartRandomTask);
        m_handler.postDelayed(mStopRandomTask, 3000);
        m_Start.setEnabled(false);
    }

    private StartRandomTask mStartRandomTask = new StartRandomTask();
    private StopRandomTask mStopRandomTask = new StopRandomTask();
    private class StartRandomTask implements Runnable {
        @Override
        public void run() {
            int index = (int) (Math.random() * nba_logo_count);
            m_view_go.setBackground(nba_logo.getDrawable(index));
            m_handler.postDelayed(this,100);
        }
    }
    private class StopRandomTask implements Runnable {
        @Override
        public void run() {
            m_handler.removeCallbacks(mStartRandomTask);
            m_Start.setEnabled(true);
        }
    }
}