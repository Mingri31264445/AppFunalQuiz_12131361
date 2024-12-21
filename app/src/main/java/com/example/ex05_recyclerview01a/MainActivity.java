package com.example.ex05_recyclerview01a;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        try {
            //將由檔案傳出的字串轉換成物件儲存在記憶體上
            JSONObject obj = new JSONObject(SceneInfo.loadJSONObjectFromAsset(this, "12131361_json.json"));
            JSONArray allSceneArr = obj.getJSONArray("all");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //SceneAdapter adapter = new SceneAdapter(SceneInfo.getMyListDataArrayListV2());
        SceneAdapter adapter = new SceneAdapter(SceneInfo.getArrayListFromJsonFile(MainActivity.this));
        recyclerView.setAdapter(adapter);
    }

}