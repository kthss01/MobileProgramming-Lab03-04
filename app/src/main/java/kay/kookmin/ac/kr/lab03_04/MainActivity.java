package kay.kookmin.ac.kr.lab03_04;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btStart;
    private Button btStop;
    private MediaPlayer mMplayer;
    private ListView lvMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMplayer = new MediaPlayer();

        // create Button object and added Event listenr
        btStart = (Button) findViewById(R.id.bt_start);
        btStop = (Button) findViewById(R.id.bt_stop);
        lvMusic = (ListView) findViewById(R.id.lvMusic);

        //prepare mp3 file for MediaPlayer
        try {
            AssetFileDescriptor afd = getResources().openRawResourceFd(R.raw.boysandgirls);
            mMplayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
        } catch(Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        // Music Start
        btStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    mMplayer.prepare(); // 음악을 재생하기전에 프리페어 과정이 필요함
                    mMplayer.start();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Music Stop
        btStop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mMplayer.stop();
            }
        });

        // Create listiview
        final String[] musicList = {"Boys and Girls", "No Make Up", "Zeze"};
        ArrayAdapter<String> musicAdapter;
        musicAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,musicList);
        lvMusic.setAdapter(musicAdapter);
        lvMusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "click : " + musicList[position], Toast.LENGTH_LONG).show();
            }
        });

    }
}
