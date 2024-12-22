package com.example.ex05_recyclerview01a;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class SceneInfo {
    private String description;
    private int imgId;
    String imgFile;
    private String country;
    private String category; // 新增類別屬性

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public SceneInfo(String description, int imgId) {
        this.description = description;
        this.imgId = imgId;
    }

    public SceneInfo(String description, int imgId, String country, String category) {
        this.description = description;
        this.imgId = imgId;
        this.country = country;
        this.category = category;
    }

    public SceneInfo(String description, String imgFile, String country, String category) {
        this.description = description;
        this.imgFile = imgFile;
        this.country = country;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public static SceneInfo[] getMyListDataArray() {
        SceneInfo[] myListData = new SceneInfo[]{
                new SceneInfo("Email", android.R.drawable.ic_dialog_email),
                new SceneInfo("Info", android.R.drawable.ic_dialog_info),
                new SceneInfo("Delete", android.R.drawable.ic_delete),
                new SceneInfo("Dialer", android.R.drawable.ic_dialog_dialer),
                new SceneInfo("Alert", android.R.drawable.ic_dialog_alert),
                new SceneInfo("Map", android.R.drawable.ic_dialog_map),
                new SceneInfo("Email", android.R.drawable.ic_dialog_email),
                new SceneInfo("Info", android.R.drawable.ic_dialog_info),
                new SceneInfo("Delete", android.R.drawable.ic_delete),
                new SceneInfo("Dialer", android.R.drawable.ic_dialog_dialer),
                new SceneInfo("Alert", android.R.drawable.ic_dialog_alert),
                new SceneInfo("Map", android.R.drawable.ic_dialog_map),
        };
        return myListData;
    }

    public static ArrayList<SceneInfo> getArrayListFromJsonFile(Context context){
        ArrayList<SceneInfo> myList = new ArrayList<SceneInfo>();

        try {
            //將由檔案傳出的字串轉換成物件儲存在記憶體
            JSONObject obj = new JSONObject(SceneInfo.loadJSONObjectFromAsset(context, "12131361_json.json"));
            JSONArray allSceneArr = obj.getJSONArray("all");
            //用for取得所有資料
            for(int i = 0; i < allSceneArr.length(); i++){
                JSONObject myJSONObject = allSceneArr.getJSONObject(i);

                String description = myJSONObject.getString("description");
                String country = myJSONObject.getString("country");
                String imgFile = myJSONObject.getString("imgFile");
                String category = myJSONObject.getString("category"); // 新增類別

                // 動態取得圖片資源 ID
                int resId = context.getResources().getIdentifier(imgFile, "drawable", context.getPackageName());
                if (resId == 0) {
                    // 如果找不到圖片，設置為預設圖片
                    resId = android.R.drawable.ic_menu_report_image;
                }


                SceneInfo mySceneInfo = new SceneInfo(description, resId, country, category);
                myList.add(mySceneInfo);
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return myList;
    }

        //由 12131361_json.json 將資料傳入
        public static String loadJSONObjectFromAsset(Context context, String filename){
            String jsonStr  = null;
            try {
                InputStream is = context.getAssets().open(filename);

                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                jsonStr = new String(buffer, StandardCharsets.UTF_8);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return jsonStr;
        }
    }

