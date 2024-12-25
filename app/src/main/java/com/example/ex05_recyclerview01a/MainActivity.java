package com.example.ex05_recyclerview01a;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
    private static final String TAG = "MainActivity";

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

        // 載入完整的資料清單
        fullSceneList = SceneInfo.getArrayListFromJsonFile(MainActivity.this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 初始化 Adapter 並設定
        adapter = new SceneAdapter(new ArrayList<>(fullSceneList));
        recyclerView.setAdapter(adapter);

        // 設定篩選按鈕
        findViewById(R.id.button).setOnClickListener(v -> filterScenes("coding"));
        findViewById(R.id.button2).setOnClickListener(v -> filterScenes("konen"));
        findViewById(R.id.button3).setOnClickListener(v -> filterScenes("xinli"));

        Button addToCartButton = findViewById(R.id.button4);
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 顯示 Toast 訊息
                Toast.makeText(MainActivity.this, "已將此商品加入至購物車", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), ThirdActivity.class);
                v.getContext().startActivity(intent);
            }
        });
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