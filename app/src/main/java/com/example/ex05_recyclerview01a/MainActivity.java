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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<SceneInfo> fullSceneList; // 儲存完整的資料清單
    private SceneAdapter adapter;

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
        /*
        try {
            //將由檔案傳出的字串轉換成物件儲存在記憶體上
            JSONObject obj = new JSONObject(SceneInfo.loadJSONObjectFromAsset(this, "12131361_json.json"));
            JSONArray allSceneArr = obj.getJSONArray("all");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        */

        // 載入完整的資料清單
        fullSceneList = SceneInfo.getArrayListFromJsonFile(MainActivity.this);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //SceneAdapter adapter = new SceneAdapter(SceneInfo.getMyListDataArrayListV2());
        //SceneAdapter adapter = new SceneAdapter(SceneInfo.getArrayListFromJsonFile(MainActivity.this));
        // 初始化 Adapter 並設定
        adapter = new SceneAdapter(fullSceneList);
        recyclerView.setAdapter(adapter);

        // 設定篩選按鈕
        findViewById(R.id.button).setOnClickListener(v -> filterScenes("筆電"));
        findViewById(R.id.button2).setOnClickListener(v -> filterScenes("滑鼠"));
        findViewById(R.id.button3).setOnClickListener(v -> filterScenes("鍵盤"));
        findViewById(R.id.button4).setOnClickListener(v -> filterScenes("其他"));
    }

    private void filterScenes(String category) {
        // 篩選符合條件的資料
        ArrayList<SceneInfo> filteredList = new ArrayList<>();
        for (SceneInfo scene : fullSceneList) {
            if (scene.getCategory().equals(category)) {
                filteredList.add(scene);
            }
        }
        // 更新 Adapter 資料
        adapter.updateData(filteredList);
    }

}