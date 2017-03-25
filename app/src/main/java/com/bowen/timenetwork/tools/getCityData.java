package com.bowen.timenetwork.tools;

import com.bowen.timenetwork.bean.CityData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/25 0025.
 */
public class GetCityData {
    private CharacterParser characterParser;

    public GetCityData(CharacterParser characterParser) {
        this.characterParser = characterParser;
    }
    public List<CityData> filledData(List<String> date) {
        List<CityData> mSortList = new ArrayList<CityData>();

        for (int i = 0; i < date.size(); i++) {
            CityData cityData = new CityData();
            cityData.setName(date.get(i));
            // 汉字转换成拼音
            String pinyin = characterParser.getSelling(date.get(i));
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                cityData.setFristA(sortString.toUpperCase());
            }
                mSortList.add(cityData);

        }
        return mSortList;
    }
}
