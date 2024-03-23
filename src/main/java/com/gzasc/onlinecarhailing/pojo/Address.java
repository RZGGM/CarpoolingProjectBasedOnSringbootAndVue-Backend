package com.gzasc.onlinecarhailing.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//地址类
@Data
public class Address {

//    id
    private Integer id;

//    市
    private static String city = "湛江市";

//    县或是区
    private static String[] region = {
        "赤坎区", "霞山区", "坡头区", "麻章区", "廉江市", "雷州市", "吴川市", "遂溪县", "徐闻县"
    };
//    镇
    private static Map<String, String[]> town = new HashMap<>();
    static {
        List<String[]> townArrList = new ArrayList<>(){};
        townArrList.add(
                new String[]{"中华街道", "寸金街道", "民主街道", "中山街道", "沙湾街道", "调顺街道", "南桥街道", "北桥街道"}
        );
        townArrList.add(
            new String[]{"解放街道", "爱国街道", "工农街道", "友谊街道", "新兴街道", "海滨街道", "建设街道", "东新街道", "新园街道",
                    "海头街道", "泉庄街道", "乐华街道"}
        );
        townArrList.add(
                new String[]{"南调街道", "麻斜街道", "南三镇", "坡头镇", "乾塘镇", "龙头镇", "官渡镇"}
        );
        townArrList.add(
                new String[]{"东山街道", "东简街道", "民安街道", "麻章镇", "太平镇", "湖光镇", "硇洲镇"}
        );
        townArrList.add(
                new String[]{"罗州街道", "城南街道", "城北街道", "石城镇", "新民镇", "吉水镇", "河唇镇", "石角镇", "良垌镇",
                        "横山镇", "安铺镇", "营仔镇", "青平镇", "车板镇", "高桥镇", "石岭镇", "雅塘镇", "石颈镇", "长山镇",
                        "塘蓬镇", "和寮镇"}
        );
        townArrList.add(
                new String[]{"雷城街道", "西湖街道", "新城街道", "白沙镇", "沈塘镇", "客路镇", "杨家镇", "唐家镇", "企水镇",
                        "纪家镇", "松竹镇", "南兴镇", "雷高镇", "东里镇", "调风镇", "龙门镇", "英利镇", "北和镇", "乌石镇",
                        "覃斗镇", "附城镇"}

        );
        townArrList.add(
                new String[]{"梅菉街道", "塘尾街道", "大山江街道", "博铺街道", "海滨街道", "浅水镇", "长岐镇", "覃巴镇",
                        "王村港镇", "振文镇", "樟铺镇", "吴阳镇", "塘缀镇", "黄坡镇", "兰石镇"}
        );
        townArrList.add(
                new String[]{"遂城街道", "附城镇", " 黄略镇", "洋青镇", "界炮镇", "乐民镇", "江洪镇", "杨柑镇", "城月镇",
                        "乌塘镇", "建新镇", "岭北镇", "北坡镇", "港门镇", "草潭镇", "河头镇"}
        );
        townArrList.add(
                new String[]{"徐城街道", "迈陈镇", "海安镇", "曲界镇", "前山镇", "西连镇", "下桥镇", "龙塘镇", "下洋镇",
                        "锦和镇", "和安镇", "新寮镇", "南山镇", "城北乡", "角尾乡"}
        );

        for (int i = 0; i < region.length; i++) {
            town.put(region[i],townArrList.get(i));
        }
    }

//    详细地址
    private String detailAddress;






}
