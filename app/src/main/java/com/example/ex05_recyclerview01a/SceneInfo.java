package com.example.ex05_recyclerview01a;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
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
                jsonStr = new String(buffer, "UTF-8");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return jsonStr;
        }

        public static ArrayList<SceneInfo> getMyListDataArrayListV3() {
            return null;
        }


        //用陣列存取
        /*public static ArrayList<SceneInfo> getMyListDataArrayListV2() {
        ArrayList<SceneInfo> myList = new ArrayList<SceneInfo>();

        String[] descriptionArr = {
                "下龍灣",
                "孟買",
                "杜拜"
        };

        int[] imgIdArr = {
                R.drawable.shalongwan,
                R.drawable.mongmy,
                R.drawable.taipei
        };

        String[] countryArr = {
                "越南",
                "印度",
                "阿拉伯聯合大公國"
        };

        for (int i = 0; i < descriptionArr.length; i++) {
            SceneInfo mySceneInfo = new SceneInfo(descriptionArr[i], imgIdArr[i], countryArr[i]);
            myList.add(mySceneInfo);//把資料傳進myList
        }

        return myList;
    }

         */
        //土法煉鋼
        public static ArrayList<SceneInfo> getMyListDataArrayList() {
        ArrayList<SceneInfo> myList = new ArrayList<SceneInfo>();


        /*
        String description = "下龍灣";
        int imgId = R.drawable.shalongwan;
        String country = "越南";
        SceneInfo mySceneInfo =  new SceneInfo(description, imgId, country);
        myList.add(mySceneInfo);
        //-------------------------------------------------------
        description = "孟買";
        imgId = R.drawable.mongmy;
        country = "印度";
        mySceneInfo =  new SceneInfo(description, imgId, country);
        myList.add(mySceneInfo);
        //-------------------------------------------------------
        description = "杜拜";
        imgId = R.drawable.dobuy;
        country = "阿拉伯聯合大公國";
        mySceneInfo =  new SceneInfo(description, imgId, country);
        myList.add(mySceneInfo);
        //-------------------------------------------------------
        description = "台北";
        imgId = R.drawable.taipei;
        country = "台灣";
        mySceneInfo =  new SceneInfo(description, imgId, country);
        myList.add(mySceneInfo);
        //-------------------------------------------------------
        description = "台南";
        imgId = R.drawable.taina;
        country = "台灣";
        mySceneInfo =  new SceneInfo(description, imgId, country);
        myList.add(mySceneInfo);
        //-------------------------------------------------------
        description = "巴黎";
        imgId = R.drawable.paris;
        country = "法國";
        mySceneInfo =  new SceneInfo(description, imgId, country);
        myList.add(mySceneInfo);
        //-------------------------------------------------------
        description = "雪梨";
        imgId = R.drawable.sheli;
        country = "澳洲";
        mySceneInfo =  new SceneInfo(description, imgId, country);
        myList.add(mySceneInfo);
        //-------------------------------------------------------
        description = "開羅";
        imgId = R.drawable.kailo;
        country = "埃及";
        mySceneInfo =  new SceneInfo(description, imgId, country);
        myList.add(mySceneInfo);
        //-------------------------------------------------------
        description = "巨石陣";
        imgId = R.drawable.bigstonezhen;
        country = "英國";
        mySceneInfo =  new SceneInfo(description, imgId, country);
        myList.add(mySceneInfo);
        //-------------------------------------------------------
        description = "吳哥窟";
        imgId = R.drawable.wugeku;
        country = "柬埔寨";
        mySceneInfo =  new SceneInfo(description, imgId, country);
        myList.add(mySceneInfo);
        //-------------------------------------------------------
        */

        return myList;
    }
        public static ArrayList<SceneInfo> getMyListDataArrayListFromData () {
            return null;
        }
        public static ArrayList<SceneInfo> getMyListDataArrayListFromOpenData () {
            return null;
        }
        public static ArrayList<SceneInfo> getMyListDataArrayListFromSQLite () {
            return null;
        }
    }

