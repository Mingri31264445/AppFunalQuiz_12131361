package com.example.ex05_recyclerview01a;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {
    private ArrayList<SceneInfo> fullSceneList; // 儲存完整的資料清單
    private SceneAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 載入完整的資料清單
        fullSceneList = SceneInfo.getArrayListFromJsonFile2(ThirdActivity.this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 初始化 Adapter 並設定
        adapter = new SceneAdapter(new ArrayList<>(fullSceneList));
        recyclerView.setAdapter(adapter);

        Button Button5 = findViewById(R.id.btnback2);
        Button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 點擊後返回上一頁
                finish();
            }
        });
    }
}