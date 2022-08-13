package com.jcduhdt.sharp.codeposition.util;

import com.jcduhdt.sharp.codeposition.data.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {


    /**
     * @param projectName eg: sharp
     * @param content     form clipboard, eg: [INFO][2021-12-28T19:14:32.821+0800][..ji.com/guarana/sharp/model/user_service.Register/login.go:57] _com_common_info||ctx_format=unset||func=getSendMQDelay||datetime=2021-09-25 18:00:00||area_id=55000425||regularRule={"type":2,"cal_time":"-1"}||target_tsp=1632600000||delay=-8090072||
     * @return match result:filePath、fileName、funcName、row、col
     * @throws Exception
     */
    public static Data fileDataRegex(String projectName, String content) throws Exception {

        // 截取path、funcName、row等有用信息
        Pattern pattern = Pattern.compile("(?<=[0-9]\\]\\[).*(?=\\])");
        Matcher matcher = pattern.matcher(content);
        String allPAth = "";
        if (matcher.find()) {
            allPAth = matcher.group();
        }
        if (allPAth.equals("")) {
            throw new Exception("no content matched");
        }

        // 两种case
        if (allPAth.contains(projectName)) {
            pattern = Pattern.compile(projectName + ".*");
            matcher = pattern.matcher(allPAth);
            if (matcher.find()) {
                allPAth = matcher.group();
            }
        } else {
            pattern = Pattern.compile("(?<=\\/).*");
            matcher = pattern.matcher(allPAth);
            if (matcher.find()) {
                allPAth = matcher.group();
            }
        }

        // 获取row
        String rowStr = "";
        pattern = Pattern.compile("[0-9]+");
        matcher = pattern.matcher(allPAth);
        if (matcher.find()) {
            rowStr = matcher.group();
        }

        int row = 0;
        if (!rowStr.equals("")) {
            row = Integer.parseInt(rowStr);
        }

        // filepath,funcName
        String funcName = "";
        pattern = Pattern.compile("(?<=[a-zA-Z])\\..*(?=\\/)");
        matcher = pattern.matcher(allPAth);
        if (matcher.find()) {
            funcName = matcher.group();
            funcName = funcName.replace(".", "");
        }
        String realPath = allPAth.replaceAll("(?<=[a-zA-Z])\\..*(?=\\/)", "");
        realPath = realPath.replaceAll(":[0-9]+", "");

        // filename
        String[] splitRes = realPath.split("/");
        String fileName = splitRes[splitRes.length - 1];

        return new Data(realPath, fileName, funcName, row - 1, 0);
    }
}
