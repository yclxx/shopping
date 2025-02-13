package org.dromara.boss.controller;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.dromara.boss.domain.vo.ZpAddResultDataVo;
import org.dromara.boss.domain.vo.ZpDetailInfoVo;
import org.dromara.boss.domain.vo.ZpListJobInfoVo;
import org.dromara.boss.domain.vo.ZpListDataVo;
import org.dromara.boss.utils.ZpUtils;
import org.dromara.common.core.utils.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiexi
 * @description
 * @date 2024/11/9 16:46
 */
@Slf4j
public class BossTest {

    //    private static final List<String> activityTimes = List.of(new String[]{"刚刚活跃", "今日活跃", "昨日活跃", "3日内活跃"});
    private static final List<String> activityTimes = List.of(new String[]{"刚刚活跃", "今日活跃", "昨日活跃"});

    public static void main(String[] args) {
        // 生成二维码
        // https://www.zhipin.com/wapi/zppassport/captcha/randkey
        // {
        //    "code": 0,
        //    "message": "Success",
        //    "zpData": {
        //        "qrId": "bosszp-ffa951e4-9fdd-4fc2-874a-2eab7794e1ae",
        //        "randKey": "Iu196jfOWkrNKfDRxoqD02xYDOCLsqb6",
        //        "secretKey": "OjEaJ771",
        //        "shortRandKey": "bosszp-pKkFPpyPjpxZn0MI"
        //    }
        //}
        // https://www.zhipin.com/wapi/zpweixin/qrcode/getqrcode?content=bosszp-ffa951e4-9fdd-4fc2-874a-2eab7794e1ae&w=200&h=200
        // https://www.zhipin.com/wapi/zppassport/qrcode/scan?uuid=bosszp-ffa951e4-9fdd-4fc2-874a-2eab7794e1ae
        // {scaned: true, allweb: true, newScaned: true, scanedV2: true}

        // https://www.zhipin.com/wapi/zppassport/qrcode/scanLogin?qrId=bosszp-75eaa558-273a-4c88-af6c-da52803f827c

        String cookie = "ab_guid=41f96b5b-6c82-49dd-b139-d51cef2ba072; __fid=a8dfcec6045ce365c7dddf85fa017eb9; lastCity=101210100; __g=-; Hm_lvt_194df3105ad7148dcf2b98a91b5e727a=1731141636,1731286331,1731325440,1731372868; Hm_lpvt_194df3105ad7148dcf2b98a91b5e727a=1731372868; HMACCOUNT=DEA65BA8B3E75B7F; wt2=DCZPRwLJNTKlZb-gFsfdLAC4ElMUtpzxOgGfXA90wQKtzZJ9kF8QgSiY_jy2zZQW36VcdQuvoJ6-57QDywse8Gw~~; wbg=0; zp_at=ZL9Xw0SgDLQ9A_GpwKiepEEHl1Yf6pQGjZbpgmJnIm4~; __c=1731372867; __a=92813523.1731325621.1731325621.1731372867.16.2.3.16; bst=V2RtMgFuH73l1gXdJqzR4QIS-w7D_ezQ~~|RtMgFuH73l1gXdJqzR4QIS-w7DPQwg~~";
        String zpToken = "V2RtMgFuH73l1gXdJqzR4QIS-w7D_ezQ~~|RtMgFuH73l1gXdJqzR4QIS-w7DPQwg~~";
        String token = "OGrDF30Eu5GwT9G";
        String traceid = "F-deb68cCEiTMoVqgq";

        // 广东
//        String encryptExpectId = "56aa55211a9b5edc1nR93tm4FlNZxA~~";
        // 深圳
        String encryptExpectId = "9ee2cb4d952fe9021nR93tm4FlNZxQ~~";
        // 杭州
//        String encryptExpectId = "4ea2b9a8c7ecd7c51nR93tm4F1FZww~~";
        Integer page = 1;

        Map<String, String> headers = new HashMap<>();
        headers.put("cookie", cookie);
        headers.put("zp_token", zpToken);
        headers.put("token", token);
        headers.put("traceid", traceid);

        ZpUtils instance = ZpUtils.getInstance(headers);

        int pushCount = 0;

        for (int i = 1; i <= 25; i++) {
            ZpListDataVo zpData = instance.queryZpList(encryptExpectId, i, true);
            if (null == zpData || ObjectUtil.isEmpty(zpData.getJobList())) {
                log.info("获取招聘列表失败，返回结果为空");
                return;
            }
            List<ZpListJobInfoVo> jobList = zpData.getJobList();
            for (ZpListJobInfoVo zpJobInfoVo : jobList) {
                log.info("招聘信息为：{}", zpJobInfoVo);
                // 跳过猎头 bossTitle
                if (StringUtils.isNotBlank(zpJobInfoVo.getBossTitle()) && zpJobInfoVo.getBossTitle().contains("猎头")) {
                    log.info("跳过猎头,{}", zpJobInfoVo.getBossTitle());
                    continue;
                }

                // 跳过兼职 jobName
                if (StringUtils.isNotBlank(zpJobInfoVo.getJobName()) && zpJobInfoVo.getJobName().contains("兼职")) {
                    log.info("跳过兼职,{}", zpJobInfoVo.getJobName());
                    continue;
                }

                String salaryDesc = zpJobInfoVo.getSalaryDesc();
                if (StringUtils.isBlank(salaryDesc) || !salaryDesc.contains("-")) {
                    log.info("薪资{}不符合预期，跳过", salaryDesc);
                    continue;
                }
                String[] split = salaryDesc.split("-");
                // 最低薪资
                String minSalary = split[0];
                // 最高薪资 可能是 15K·13薪
                String maxSalaryStr = split[1];
                if (!maxSalaryStr.contains("K")) {
                    log.info("薪资单位未识别处理，{}", salaryDesc);
                    continue;
                }
                // 截取K前面的数字
                String maxSalary = maxSalaryStr.substring(0, maxSalaryStr.indexOf("K"));
                if (!NumberUtil.isInteger(minSalary) || !NumberUtil.isInteger(maxSalary)) {
                    log.info("薪资{}不符合预期，跳过", salaryDesc);
                    continue;
                }
                // 转整为int
                int minSalaryInt = Integer.parseInt(minSalary);
                int maxSalaryInt = Integer.parseInt(maxSalary);
                if (minSalaryInt < 15 && maxSalaryInt < 18) {
                    log.info("薪资{}不符合预期，跳过", salaryDesc);
                    continue;
                }

                // 休眠一下，防止被当成机器人
                int sleepTime = (RandomUtil.getRandom().nextInt(10) + 10) * 1000;
                ThreadUtil.sleep(sleepTime);

                // 查询招聘详情
                ZpDetailInfoVo infoData = instance.queryZpInfoVo(zpJobInfoVo.getSecurityId(), zpJobInfoVo.getLid(), true);
                log.info("获取招聘详细信息成功：{}", infoData);

                // 判断招聘岗位是否是JAVA
                if (!infoData.getJobInfo().getPositionName().contains("Java")) {
                    log.info("岗位不是java，跳过,{}", infoData.getJobInfo().getPositionName());
                    continue;
                }

                // 跳过猎头
                if (StringUtils.isNotBlank(infoData.getBossInfo().getTitle()) && infoData.getBossInfo().getTitle().contains("猎头")) {
                    log.info("跳过猎头,{}", infoData.getBossInfo().getTitle());
                    continue;
                }

                // 判断boos 活跃时间
                String activeTimeDesc = infoData.getBossInfo().getActiveTimeDesc();
                // null 刚刚活跃 今日活跃 3日内活跃 本周活跃 2周内活跃 本月活跃 半年前活跃
                if (null == activeTimeDesc || !activityTimes.contains(activeTimeDesc)) {
                    log.info("长时间不活跃的跳过,{}", activeTimeDesc);
                    continue;
                }

                // 休眠一下，防止被当成机器人
                int sleepTime2 = (RandomUtil.getRandom().nextInt(10) + 20) * 1000;
                ThreadUtil.sleep(sleepTime2);

                // 投递
                ZpAddResultDataVo zpAddResultDataVo = instance.addZp(infoData.getSecurityId(), infoData.getJobInfo().getEncryptId(), infoData.getLid(), true);
                log.info("投递公司：{}，返回结果：{}", infoData.getBossInfo().getBrandName(), zpAddResultDataVo);
                pushCount++;
            }
        }

        log.info("投递完成，共投递：{}", pushCount);

//        // 查询工作列表
//        String url = "https://www.zhipin.com/wapi/zpgeek/pc/recommend/job/list.json?city=&experience=&payType=&partTime=&degree=&industry=&scale=&salary=&jobType=&encryptExpectId=56aa55211a9b5edc1nR93tm4FlNZxA~~&page=1&pageSize=15";
//        String zpToken = "V2RtMgFuH73l1gXdJqzhoQKi2w7DnUxg~~|RtMgFuH73l1gXdJqzhoQKi2w7DvWzA~~";
//        String token = "OGrDF30Eu5GwT9G";
//        String traceid = "F-2039b9S6ebv8geGj";
//
//        HttpRequest request = HttpUtil.createGet(url);
//        request.header("cookie", "lastCity=101210100; __g=-; __l=l=%2Fcv.zhipin.com%2F&r=&friend_source=0&s=3&friend_source=0; Hm_lvt_194df3105ad7148dcf2b98a91b5e727a=1728885186,1729409095,1729664298,1731141636; HMACCOUNT=BADCA945DE6CA02D; __zp_stoken__=8209fOT%2FDlMK8wr3CujIqCg0HDwg3KT4%2FLXQxOSs1OzU5Pzs5Nzk%2FMxs5KSTCuS8ZD1HDjBs%2BLD87ND45MTs7Mxw%2FN8S9wrk4PiAuwrwtGA1Vw4kKAAhtDTPCvghKDVs1KXPCvz44NDxZwrs4wrYMwrw%2BwrIMwrk5wrQ4PDw5JDxWWQkIPDxPSFsKTlNJUVBCDkBHQS48Mjg%2FwqfCkMO5Ij0KCg8IDgUFAAcBAQEEAgQFBQAHAQsLDgkPIzvCncK%2BxKZNw5rCqMSVxarDhcKjw4DDq8OVwq%2FErMKwxJ3CocS%2FwqfEo8KYw6nCisOOwrrDmMK%2Fw7rChcOMwpfChsKGw6nCrMOJwp7DjMK0w7hIw7jCnMKCwpfDuVHDncKOw6dRwqbCs8KrwrHCikfDrk%2FDslzCnUjCgMKhwoHCvMKvS0nCqsKowrzCr1lHb8K4Q1lXUUJowrHCtFpzfkRaZwVfR1h7BggHOlwLwqnDjw%3D%3D; Hm_lpvt_194df3105ad7148dcf2b98a91b5e727a=1731141656; wt2=D7KxX5_07xzCWOlJ0hC1epg-WyXEoImj5c-AraOCe3lYl79FKy8Ym-30PtiGcz-eXEnTjA4MKEcH2Y4DPzVyhgA~~; wbg=0; zp_at=gJZXggc6XUnHRAPvOjr7Cgz9LCa1xKCDQdP_Igw9_3Y~; __c=1731141624; __a=47353380.1725625445.1729664298.1731141624.139.6.7.17; ab_guid=25b40e22-cd12-43ec-b7b4-ad8c10467622; bst=V2RtMgFuH73l1gXdJqzhoQKi2w7DnUxg~~|RtMgFuH73l1gXdJqzhoQKi2w7DvWzA~~");
//        request.header("zp_token", zpToken);
//        request.header("token", token);
//        request.header("traceid", traceid);
//        String result = request.execute().body();
//        // {"code":0,"message":"Success","zpData":{"hasMore":true,"jobList":[{"securityId":"eZmQOmOcSxroA-i1kex0RRgDByT9lB8FJpspD6Jkqxb7Cuf2LBe4WAlYMeK6O62iLROdRAgnbx_FFAP9s2XmC5IeOjOJA_5ZSBREHIe1p_3WU7KF9tOZ8ngv7h3iYoqe67Vg7ctOAr9oXZEiKoKdabaBtzH3_D3VErmDd_PVZqyUumHYVZP2BjlzW4GThNBJ3cyE_L1ktH1aoioYBaMa3byNfoniz187gcO9Xg~~","bossAvatar":"https://img.bosszhipin.com/beijin/upload/avatar/20240622/607f1f3d68754fd00a4c8ea88c5b3b7355ea2440dbf28f2aa3b593af0cd47c75b555cfb87793e4e0_s.jpg.webp","bossCert":3,"encryptBossId":"3ed72fe18cbbb5521XR_3dq1Elc~","bossName":"何柳艳","bossTitle":"招聘职位","goldHunter":0,"bossOnline":false,"encryptJobId":"40e30d5b49f355531H1y29-7FFtS","expectId":1075456196,"jobName":"Android应用工程师","lid":"2336af9c-5481-4a67-883e-0949702398c8.f1:common.eyJzZXNzaW9uSWQiOiIyOGE2MGJkYi0wMjAzLTRkZmEtYTgxNy0zZmY2Y2Y0ZTM5NTkiLCJyY2RCelR5cGUiOiJmMV9ncmNkIn0.1","salaryDesc":"12-15K·13薪","jobLabels":["1-3年","本科","Android客户端产品研发","Java"],"jobValidStatus":1,"iconWord":"急聘","skills":["Android客户端产品研发","Java"],"jobExperience":"1-3年","daysPerWeekDesc":"","leastMonthDesc":"","jobDegree":"本科","cityName":"广州","areaDistrict":"白云区","businessDistrict":"太和","jobType":0,"proxyJob":0,"proxyType":0,"anonymous":0,"outland":0,"optimal":0,"iconFlagList":[1],"itemId":0,"city":0,"isShield":0,"atsDirectPost":false,"gps":null,"encryptBrandId":"e638385a55d4ca590nB62ty0FQ~~","brandName":"广州飞傲电子科技...","brandLogo":"https://img.bosszhipin.com/beijin/mcs/chatphoto/20190809/6821bf6c65abefb0468f3926d5669a8fa3b593af0cd47c75b555cfb87793e4e0_s.jpg","brandStageName":"未融资","brandIndustry":"其他行业","brandScaleName":"100-499人","welfareList":["节日福利","五险一金","年终奖","带薪年假","员工旅游","定期体检"],"industry":0,"contact":false,"showTopPosition":false},{"securityId":"FR8CnQ6NpjtDt-c1cPxjSscAaMbm6Gn_8pBX0_X768nq9qdQN8yL2pvGEnQEPpCtDdR2CHtFgm15YXBvLkiwUHNh42k7AjN5z-LNWxKaQJaNsU2irWdm1_2-uO_ijeMXaNMFKTh1ioqJZL0JgeMmUxDFN5wLndePDyBXhCnZ4rzNDmfZUQf3Ry240HuHIodjYTM~","bossAvatar":"https://img.bosszhipin.com/beijin/upload/avatar/20241024/607f1f3d68754fd0f25b9df301ae288339b5f317ed201de6c6fe12646f7ac5aacfb988d4bbc80817_s.png.webp","bossCert":3,"encryptBossId":"7fe160ae44c786450nF93tu4E1NQ","bossName":"唐先生","bossTitle":"高级HR人事主管","goldHunter":0,"bossOnline":true,"encryptJobId":"ca0efb781e5059991HJ-0t68EFFY","expectId":1075456196,"jobName":"高级java全栈工程师","lid":"2336af9c-5481-4a67-883e-0949702398c8.f1:common.eyJzZXNzaW9uSWQiOiIyOGE2MGJkYi0wMjAzLTRkZmEtYTgxNy0zZmY2Y2Y0ZTM5NTkiLCJyY2RCelR5cGUiOiJmMV9ncmNkIn0.2","salaryDesc":"9-14K","jobLabels":["3-5年","本科","Golang","大数据经验","不接受居家办公"],"jobValidStatus":1,"iconWord":"","skills":["Golang","大数据经验","不接受居家办公"],"jobExperience":"3-5年","daysPerWeekDesc":"","leastMonthDesc":"","jobDegree":"本科","cityName":"成都","areaDistrict":"双流区","businessDistrict":"华阳","jobType":0,"proxyJob":0,"proxyType":0,"anonymous":0,"outland":1,"optimal":0,"iconFlagList":[4],"itemId":0,"city":0,"isShield":0,"atsDirectPost":false,"gps":null,"encryptBrandId":"7fc72bb3cc301b7f1HF_3tm_F1o~","brandName":"重庆云辩","brandLogo":"https://img.bosszhipin.com/beijin/upload/com/logo/20240620/7b5b554d84f9729c572ec5f5210b2080f0ba1f6be960cb0a6cba56a2ca3c3c34f856b8c0dc3eaf52.png.webp","brandStageName":"","brandIndustry":"法律","brandScaleName":"100-499人","welfareList":["生日福利","带薪年假","有无线网","底薪加提成","节日福利","通讯补贴","全勤奖","零食下午茶","工龄奖","餐补"],"industry":0,"contact":false,"showTopPosition":false},{"securityId":"viRs1e2xeVej7-T1-zXZG8vI5f2DfH9VYBvyTHkxzMByUJXqOeNXUSILQxUt9ghlp8bTET5POLVBVRCa6-BE9c6yY7T24gmcEu0kOSp2A6MTML6XwUTUDkFqRKXuf8MdAgVisSIKoGbHdhlvGO2jTFotJ3OVgL9YgEXkDJSubcQJyCQ9hx9jqcED4S4HLUkS_7A~","bossAvatar":"https://img.bosszhipin.com/beijin/upload/avatar/20240203/607f1f3d68754fd05e8b06afd34e491b11403a3ea124d387768e77cc2abe9682a4acba30d2b68b8c_s.jpg.webp","bossCert":3,"encryptBossId":"2ca723d88f23ef2d0nR83d6_FVVX","bossName":"陈女士","bossTitle":"高级招聘经理","goldHunter":0,"bossOnline":true,"encryptJobId":"0d0aa53a9100c4d61XN-2NS_GFpS","expectId":1075456196,"jobName":"滴滴出行招聘新能源司机（广州）","lid":"2336af9c-5481-4a67-883e-0949702398c8.f1:common.eyJzZXNzaW9uSWQiOiIyOGE2MGJkYi0wMjAzLTRkZmEtYTgxNy0zZmY2Y2Y0ZTM5NTkiLCJyY2RCelR5cGUiOiJmMV9ncmNkIn0.3","salaryDesc":"12-18K","jobLabels":["经验不限","学历不限","免费培训","无交通肇事记录","车辆保险/保养"],"jobValidStatus":1,"iconWord":"","skills":["免费培训","无交通肇事记录","车辆保险/保养","免费道路救援","熟悉本地路况"],"jobExperience":"经验不限","daysPerWeekDesc":"","leastMonthDesc":"","jobDegree":"学历不限","cityName":"广州","areaDistrict":"天河区","businessDistrict":"东圃","jobType":0,"proxyJob":0,"proxyType":0,"anonymous":0,"outland":0,"optimal":0,"iconFlagList":[],"itemId":0,"city":0,"isShield":0,"atsDirectPost":false,"gps":null,"encryptBrandId":"5b92f7993652b3d61nZ809y5FlM~","brandName":"滴滴出行","brandLogo":"https://img.bosszhipin.com/beijin/upload/com/logo/20240718/7b5b554d84f9729cadd302dbb4e9c6084ff5c53a46a200c986695a2ce1675ebf8431288eb784f76e.jpg.webp","brandStageName":"","brandIndustry":"互联网","brandScaleName":"10000人以上","welfareList":["加班补助","团建聚餐","交通补助","绩效奖金","高温补贴","五险一金","节日福利"],"industry":0,"contact":false,"showTopPosition":false},{"securityId":"foFmQMYO0ea2h-y1VkQupBxaYCIbfIOSsS-oINTsJju0Xjs44vBB6ivivOZj1BKQNTcfOYAdOr7m36LS6HT6H6Lsx7B14xVPwHlFPKu0hliYH9jXD_jNHTi36Lbhfbb2JlKXRM8ILa67mtvbeUhqxYDWvRhd-bMxMCET7uOx0k5oHMb2z7TOPVzXmJUwpjs~","bossAvatar":"https://img.bosszhipin.com/beijin/mcs/useravatar/20180622/166a8ce48b29364548d3dc33043feddaeae6413758ff7cc6184047dba2532fe9_s.jpg","bossCert":3,"encryptBossId":"c408c001e5670e7c1XR_396-GFc~","bossName":"陈智炫","bossTitle":"HRBP","goldHunter":0,"bossOnline":false,"encryptJobId":"e4bb7166964a32c01XR62di4FVBW","expectId":1075456196,"jobName":"资深后端开发工程师-五险一金","lid":"2336af9c-5481-4a67-883e-0949702398c8.f1:common.eyJzZXNzaW9uSWQiOiIyOGE2MGJkYi0wMjAzLTRkZmEtYTgxNy0zZmY2Y2Y0ZTM5NTkiLCJyY2RCelR5cGUiOiJmMV9ncmNkIn0.4","salaryDesc":"20-40K·14薪","jobLabels":["5-10年","大专","分布式技术","微服务架构","服务器配置"],"jobValidStatus":1,"iconWord":"","skills":["分布式技术","微服务架构","服务器配置","容器技术","负载均衡技术","Spring","MySQL","Redis"],"jobExperience":"5-10年","daysPerWeekDesc":"","leastMonthDesc":"","jobDegree":"大专","cityName":"广州","areaDistrict":"越秀区","businessDistrict":"天河北","jobType":0,"proxyJob":0,"proxyType":0,"anonymous":0,"outland":0,"optimal":0,"iconFlagList":[],"itemId":0,"city":0,"isShield":0,"atsDirectPost":false,"gps":null,"encryptBrandId":"767ff71ad8a4707a1Xd62tq0","brandName":"龙腾出行","brandLogo":"https://img.bosszhipin.com/beijin/upload/com/workfeel/20230515/7bf6f160950405e9ad320fccc3d601b1da471bb5e4f9d36bd8c6456939c14171e4d1a771cdadc804.jpg","brandStageName":"已上市","brandIndustry":"互联网","brandScaleName":"100-499人","welfareList":["员工旅游","餐补","五险一金","补充医疗保险","定期体检","节日福利","年终奖","交通补助","带薪年假","零食下午茶"],"industry":0,"contact":false,"showTopPosition":false},{"securityId":"JZYqGS_8flhqm-w1TpEexkkdsc0LR3nHEE04PH9lGS6KK8Bder72Sp3M0smGL-HII7Pk25kO4K4z3dv3TiAB0dA9EX7kiUcL7nlXGuK8ieT98sHwec6AzV8D-cR0XSESEbk2GXe3QYg1kgyk85Gw5115C_o97BT2HRrCFvMj98ZhND3UWn2HS2qaqIHdTk8H","bossAvatar":"https://img.bosszhipin.com/beijin/upload/avatar/20220716/607f1f3d68754fd06626ee2402a747a9c77a66a792d154a5f18388e0aac2d625e2986b51b81eabe6_s.jpg","bossCert":3,"encryptBossId":"9b4671e3ad6e6a110nd-2d61F1I~","bossName":"梁先生","bossTitle":"猎头顾问","goldHunter":0,"bossOnline":true,"encryptJobId":"861a89bfefad1f151H182d27EFZY","expectId":1075456196,"jobName":"中级java开发工程师","lid":"2336af9c-5481-4a67-883e-0949702398c8.f1:common.eyJzZXNzaW9uSWQiOiIyOGE2MGJkYi0wMjAzLTRkZmEtYTgxNy0zZmY2Y2Y0ZTM5NTkiLCJyY2RCelR5cGUiOiJmMV9ncmNkIn0.5","salaryDesc":"11-13K","jobLabels":["3-5年","大专","MES开发经验","Java","大数据经验"],"jobValidStatus":1,"iconWord":"","skills":["MES开发经验","Java","大数据经验","不接受居家办公","SpringCloud","MySQL","Spring","ERP开发经验"],"jobExperience":"3-5年","daysPerWeekDesc":"","leastMonthDesc":"","jobDegree":"大专","cityName":"广州","areaDistrict":"","businessDistrict":"","jobType":0,"proxyJob":1,"proxyType":1,"anonymous":101,"outland":0,"optimal":0,"iconFlagList":[2],"itemId":0,"city":0,"isShield":0,"atsDirectPost":false,"gps":null,"encryptBrandId":"","brandName":"某大型交通/运输上市公司","brandLogo":"https://img.bosszhipin.com/beijin/app/mobile/n-af8dd288f86448d9eaaf3a4b0fd60b27.png","brandStageName":"","brandIndustry":"交通/运输","brandScaleName":"10000人以上","welfareList":[],"industry":0,"contact":false,"showTopPosition":false},{"securityId":"oZmHTCdH_oA25-V1w8tS1WdzRW3r8Jxh2qEsuxwqiNKiDBZzev7wk6A9UoBAnNIYE0tN4Qran7ZVSoCLjuz5N6TBHpZ2LvGFkqdx8mYXXbTlbRzrZeVInQz_kx71BBmAPdmcOSMsgoQxVlsVgTqWIHXTq2RjSTOW0_5C1mr9c1bPeNt3EU1Qx97fFhnyTQ~~","bossAvatar":"https://img.bosszhipin.com/beijin/upload/avatar/20230813/607f1f3d68754fd0d64b99773f4d7fe84f3ac93a13c692c1cae18e2ac12d392b854dcfd06a09532f_s.jpg.webp","bossCert":3,"encryptBossId":"166068aca3480b670nxy09q4FlVX","bossName":"万采","bossTitle":"资深HR","goldHunter":0,"bossOnline":true,"encryptJobId":"ede54b718f7ac4a01HNy39W8EFtV","expectId":1075456196,"jobName":"java开发工程师/洪山区-双休","lid":"2336af9c-5481-4a67-883e-0949702398c8.f1:common.eyJzZXNzaW9uSWQiOiIyOGE2MGJkYi0wMjAzLTRkZmEtYTgxNy0zZmY2Y2Y0ZTM5NTkiLCJyY2RCelR5cGUiOiJmMV9ncmNkIn0.6","salaryDesc":"12-13K","jobLabels":["5-10年","大专","Java","Java 开发经验；","分布式经验"],"jobValidStatus":1,"iconWord":"","skills":["Java","Java 开发经验；","分布式经验","微服务经验","微服务项目开发经验等","MySQL","MyBatis","Java性能的调优经验；","Spring"],"jobExperience":"5-10年","daysPerWeekDesc":"","leastMonthDesc":"","jobDegree":"大专","cityName":"武汉","areaDistrict":"洪山区","businessDistrict":"关山","jobType":0,"proxyJob":0,"proxyType":0,"anonymous":0,"outland":1,"optimal":0,"iconFlagList":[4],"itemId":0,"city":0,"isShield":0,"atsDirectPost":false,"gps":null,"encryptBrandId":"2d208a8834e4a58203d43Q~~","brandName":"中软国际","brandLogo":"https://img.bosszhipin.com/beijin/mcs/banner/5ceba8561d592ff6849e8a0525aacbfecfcd208495d565ef66e7dff9f98764da.jpg","brandStageName":"已上市","brandIndustry":"计算机软件","brandScaleName":"10000人以上","welfareList":["餐补","节日福利","定期体检","免费班车","带薪年假","年终奖","零食下午茶","五险一金","加班补助","员工旅游"],"industry":0,"contact":false,"showTopPosition":false},{"securityId":"DgucSwnGKZXzU-z1DGkRj0FJW0bM6KJ6MX_UpC7icwQZqaB5-M8LupyyC5DdJVcM9oLOH9c7grI9BYiRCsK7zlQsF2-Qv45UM7KN5Ish6ssSne48tYkZAVGmoMEEFF59O0MyVeM5uRaZ5SZimI7B5Pm6YYiwf208bpAtGY44uStQs34TwxZDqrLrKXNNKRVR","bossAvatar":"https://img.bosszhipin.com/beijin/upload/avatar/20241105/607f1f3d68754fd0b10f4885a6426a2834743de52b5aa58b3338d1cc669019088b9870a6b4056cd3_s.png.webp","bossCert":3,"encryptBossId":"b5220320c3483c510HR73N68E1c~","bossName":"高女士","bossTitle":"hr","goldHunter":0,"bossOnline":true,"encryptJobId":"97b6c231e0efa3c51H143Ny0EldX","expectId":1075456196,"jobName":"后端开发（外企+双休福利好）-居家办公","lid":"2336af9c-5481-4a67-883e-0949702398c8.f1:common.eyJzZXNzaW9uSWQiOiIyOGE2MGJkYi0wMjAzLTRkZmEtYTgxNy0zZmY2Y2Y0ZTM5NTkiLCJyY2RCelR5cGUiOiJmMV9ncmNkIn0.7","salaryDesc":"12-17K","jobLabels":["3-5年","本科","Java","AKS平台","SpringCloud"],"jobValidStatus":1,"iconWord":"","skills":["Java","AKS平台","SpringCloud","K8S","云计算经验","Oracle","接受居家办公","英语","云平台"],"jobExperience":"3-5年","daysPerWeekDesc":"","leastMonthDesc":"","jobDegree":"本科","cityName":"成都","areaDistrict":"武侯区","businessDistrict":"中和","jobType":0,"proxyJob":0,"proxyType":0,"anonymous":0,"outland":0,"optimal":0,"iconFlagList":[],"itemId":0,"city":0,"isShield":0,"atsDirectPost":false,"gps":null,"encryptBrandId":"fc705eb462e9b27b1HJ-2t60GA~~","brandName":"纬创软件","brandLogo":"https://img.bosszhipin.com/beijin/upload/com/workfeel/20240524/7bf6f160950405e96df30c5f98fe29c8c16f93a2d801591d2e542ce3e2a430b78b6aa33edad63b36.png.webp","brandStageName":"已上市","brandIndustry":"计算机软件","brandScaleName":"1000-9999人","welfareList":["五险一金","零食下午茶","团建聚餐","节日福利","补充医疗保险","带薪年假","员工旅游","定期体检"],"industry":0,"contact":false,"showTopPosition":false},{"securityId":"vWMbdNpubRmgj-H1uTzrR4o5nzu3UcG_UFCpljmmreMfl0ZiBVFSduJFyaKlXUdJpchK57MPQ1dA8B_K7YssOo_BxdEftCgWCdc42IDOC-D0DIEQKvMls3YweD3jiKpslxAWU5sGeYutuqD1D1dVLDDY-Oi8WyQr2Rl0JBgxOulaPytPApWPYPhe0u14WFwo","bossAvatar":"https://img.bosszhipin.com/boss/avatar/avatar_13.png","bossCert":3,"encryptBossId":"ab0edb315bc775610nNz3dS6EFA~","bossName":"孔先生","bossTitle":"Hr","goldHunter":0,"bossOnline":false,"encryptJobId":"3088fe44f35a82801H1-2dy_GVBX","expectId":1075456196,"jobName":"资深Java开发工程师(A29013)","lid":"2336af9c-5481-4a67-883e-0949702398c8.f1:common.eyJzZXNzaW9uSWQiOiIyOGE2MGJkYi0wMjAzLTRkZmEtYTgxNy0zZmY2Y2Y0ZTM5NTkiLCJyY2RCelR5cGUiOiJmMV9ncmNkIn0.8","salaryDesc":"20-40K","jobLabels":["5-10年","本科","Java","Spring","MyBatis"],"jobValidStatus":1,"iconWord":"","skills":["Java","Spring","MyBatis","MySQL","Redis","架构设计经验","分布式经验","不接受居家办公"],"jobExperience":"5-10年","daysPerWeekDesc":"","leastMonthDesc":"","jobDegree":"本科","cityName":"广州","areaDistrict":"黄埔区","businessDistrict":"科学城","jobType":0,"proxyJob":0,"proxyType":0,"anonymous":0,"outland":0,"optimal":0,"iconFlagList":[],"itemId":0,"city":0,"isShield":0,"atsDirectPost":false,"gps":null,"encryptBrandId":"c2c6002d92393f5d0HZy29S0Ew~~","brandName":"如祺出行","brandLogo":"https://img.bosszhipin.com/beijin/mcs/chatphoto/20200225/f7e5c3c16a7047f1342845289c3fccecf3b56e5b77a5766e4a9d5386ea5e9641_s.jpg","brandStageName":"已上市","brandIndustry":"互联网","brandScaleName":"100-499人","welfareList":["餐补","通讯补贴","五险一金","带薪年假","节日福利","年终奖","补充医疗保险"],"industry":0,"contact":false,"showTopPosition":false},{"securityId":"aN68eX2U_wHxe-U12G8QZR043YYdSbpT1KVU4UX4Hm9r94i1Zli-R9RcYOCOT4Nr9uJc0If65JFfhuDk5nEyr5-VR0Bup1sA261FgPETEvLxT1stF-leSNFS9YS1gZEVfz0lOux7PGnFTF20fW3pTsx_j8aajzUDzyz_eEbpuALYrVvhfEN6AgFKBZeWgWiODQs~","bossAvatar":"https://img.bosszhipin.com/beijin/upload/avatar/20240601/607f1f3d68754fd0183ca93f2f78c3d6f40cb220e908c94a28526770c231916705d57497f5d11efc_s.png.webp","bossCert":3,"encryptBossId":"19983b6949ee84c90XF_0t-9EVJT","bossName":"吴女士","bossTitle":"高级招聘主管","goldHunter":0,"bossOnline":true,"encryptJobId":"9b883b4d0ca55e811HJ73d-9FFNZ","expectId":1075456196,"jobName":"广州-月薪过万+滴滴官方直招网约车司机","lid":"2336af9c-5481-4a67-883e-0949702398c8.f1:common.eyJzZXNzaW9uSWQiOiIyOGE2MGJkYi0wMjAzLTRkZmEtYTgxNy0zZmY2Y2Y0ZTM5NTkiLCJyY2RCelR5cGUiOiJmMV9ncmNkIn0.9","salaryDesc":"10-15K","jobLabels":["经验不限","学历不限","驾驶证C1","无犯罪、吸毒、酒驾记录","会用导航"],"jobValidStatus":1,"iconWord":"","skills":["驾驶证C1","无犯罪、吸毒、酒驾记录","会用导航","周结工资","晚班/高温补贴","驾驶证C2","近3年无记满12分记录","免费培训","全职兼职均可","日结工资","车辆保险/保养","3年及以上驾龄","公司提供电车","自己带车","平台一对一派单"],"jobExperience":"经验不限","daysPerWeekDesc":"","leastMonthDesc":"","jobDegree":"学历不限","cityName":"广州","areaDistrict":"白云区","businessDistrict":"江高","jobType":0,"proxyJob":0,"proxyType":0,"anonymous":0,"outland":0,"optimal":0,"iconFlagList":[],"itemId":0,"city":0,"isShield":0,"atsDirectPost":false,"gps":null,"encryptBrandId":"5b92f7993652b3d61nZ809y5FlM~","brandName":"滴滴出行","brandLogo":"https://img.bosszhipin.com/beijin/upload/com/logo/20240718/7b5b554d84f9729cadd302dbb4e9c6084ff5c53a46a200c986695a2ce1675ebf8431288eb784f76e.jpg.webp","brandStageName":"","brandIndustry":"互联网","brandScaleName":"10000人以上","welfareList":["节日福利","加班补助","交通补助","高温补贴","绩效奖金","五险一金","团建聚餐"],"industry":0,"contact":false,"showTopPosition":false},{"securityId":"6jOPVyZOix3wG-z198nOPoPNQIZ-Y2azg-TfTmofm-CvSc4bctaUrmnJUAkv03sZ2VFAc5jwMOJPJsGquPEtr5C8fv8s65nSx8CPy5PiQ6dvdAKK2Fj94TrKHF5FAOODD7wgbBv1XhWMXWpRJbOu6Vdeqj7mAUDy1gqUOTq09BAw3va_ChXxqAJlSWpMQxzVKQ~~","bossAvatar":"https://img.bosszhipin.com/beijin/upload/avatar/20220315/607f1f3d68754fd09ed0bf2d8fe4de3b59cd9b66ae9588f71027f993f213c0b07d176c76c3b70509_s.png","bossCert":3,"encryptBossId":"7de8f726059c56101H1_3dy4EVc~","bossName":"张女士","bossTitle":"人事经理","goldHunter":0,"bossOnline":true,"encryptJobId":"362903c5d384bb5b1H1y2t6_F1BZ","expectId":1075456196,"jobName":"高级java研发工程师","lid":"2336af9c-5481-4a67-883e-0949702398c8.f1:common.eyJzZXNzaW9uSWQiOiIyOGE2MGJkYi0wMjAzLTRkZmEtYTgxNy0zZmY2Y2Y0ZTM5NTkiLCJyY2RCelR5cGUiOiJmMV9ncmNkIn0.10","salaryDesc":"11-12K","jobLabels":["5-10年","大专","不接受居家办公","Java","MySQL"],"jobValidStatus":1,"iconWord":"","skills":["不接受居家办公","Java","MySQL","团队管理经验","devops"],"jobExperience":"5-10年","daysPerWeekDesc":"","leastMonthDesc":"","jobDegree":"大专","cityName":"成都","areaDistrict":"武侯区","businessDistrict":"中和","jobType":0,"proxyJob":0,"proxyType":0,"anonymous":0,"outland":1,"optimal":0,"iconFlagList":[4],"itemId":0,"city":0,"isShield":0,"atsDirectPost":false,"gps":null,"encryptBrandId":"4b9c821dbcc12d301X1z3dW_GVM~","brandName":"上海龙运览网络科技","brandLogo":"https://img.bosszhipin.com/beijin/icon/894ce6fa7e58d64d57e7f22d2f3a9d18afa7fcceaa24b8ea28f56f1bb14732c0.png","brandStageName":"","brandIndustry":"电子商务","brandScaleName":"100-499人","welfareList":[],"industry":0,"contact":false,"showTopPosition":false},{"securityId":"tsVzkWvPbScVk-g1tsEjiC6xyWbyYgTCqr4_yVKj3QtkLGKZAexREHsxvc6mJG5jLjnbP1znTWa9Y_Jw54qZQ8SuwBJ2pYijc34oSIJiSzjPbCjpoCF8HVhScXB8QXNugZrE1hEMXe02JD9BkNF6S44zNfmTOqznt51NHzHiKo3pMzNRcR5dMccRSKb0Ax-s5Q~~","bossAvatar":"https://img.bosszhipin.com/beijin/upload/avatar/20231109/607f1f3d68754fd0b0267d656fffc4a45baa151d99ec3790d544541d41cd0c58114413ab9cc717cf_s.png.webp","bossCert":3,"encryptBossId":"c3d80b1868c332160nd92Nu9GVtQ","bossName":"班女士","bossTitle":"招聘HR","goldHunter":0,"bossOnline":false,"encryptJobId":"501fa09fe42863dd1HB429W_FFNW","expectId":1075456196,"jobName":"【长沙交易-出价&库存】后端开发工程师","lid":"2336af9c-5481-4a67-883e-0949702398c8.f1:common.eyJzZXNzaW9uSWQiOiIyOGE2MGJkYi0wMjAzLTRkZmEtYTgxNy0zZmY2Y2Y0ZTM5NTkiLCJyY2RCelR5cGUiOiJmMV9ncmNkIn0.11","salaryDesc":"20-30K·14薪","jobLabels":["3-5年","大专","Java","SpringCloud","Spring"],"jobValidStatus":1,"iconWord":"","skills":["Java","SpringCloud","Spring","MySQL","出价","库存","商城"],"jobExperience":"3-5年","daysPerWeekDesc":"","leastMonthDesc":"","jobDegree":"大专","cityName":"长沙","areaDistrict":"开福区","businessDistrict":"湘江中路","jobType":0,"proxyJob":0,"proxyType":0,"anonymous":0,"outland":1,"optimal":0,"iconFlagList":[4],"itemId":0,"city":0,"isShield":0,"atsDirectPost":false,"gps":null,"encryptBrandId":"6b46ec00bb8068171X162tm9Fg~~","brandName":"得物App","brandLogo":"https://img.bosszhipin.com/beijin/mcs/banner/b419a2f4b234c5f9d7626ce1e61d3acbcfcd208495d565ef66e7dff9f98764da.jpg","brandStageName":"B轮","brandIndustry":"电子商务","brandScaleName":"1000-9999人","welfareList":["节日福利","绩效奖金","加班补助","餐补","年终奖","股票期权","生日福利","交通补助","带薪年假","定期体检","五险一金","零食下午茶"],"industry":0,"contact":false,"showTopPosition":false},{"securityId":"GCbH_5zL9S4Jx-l160iQ4uZWCa5kEVWb46Jqv8gGe57wbLyLL9pw8QBrlQXU2r6O0ao2WBjcnYPF0OvJfJiI-OAc1NHJH0XOjg_wP4EA_9g1r_i-oO2Ze0ZmzZaLYU0zIiDpPuvKfpi6k1mm3QG4S5SRbYcHghq_TlBO_BGAyUvn_-tbrm5Be7Yz0b28GeQ9","bossAvatar":"https://img.bosszhipin.com/beijin/upload/avatar/20220808/607f1f3d68754fd0fe9e37e73d178d3f57f1a7fd59e59c0d06afadd9d1026ca054756346cbc663c9_s.jpg","bossCert":3,"encryptBossId":"05d8f5088f649b3e0nF-2Ny1FFtV","bossName":"何先生","bossTitle":"HR","goldHunter":0,"bossOnline":false,"encryptJobId":"65090af0e860ac3f1H1829W5FlVX","expectId":1075456196,"jobName":"高级开发工程师（工会项目） (MJ003511)","lid":"2336af9c-5481-4a67-883e-0949702398c8.f1:common.eyJzZXNzaW9uSWQiOiIyOGE2MGJkYi0wMjAzLTRkZmEtYTgxNy0zZmY2Y2Y0ZTM5NTkiLCJyY2RCelR5cGUiOiJmMV9ncmNkIn0.12","salaryDesc":"10-15K·13薪","jobLabels":["1-3年","本科","Java","Spring","MySQL"],"jobValidStatus":1,"iconWord":"","skills":["Java","Spring","MySQL","工会项目"],"jobExperience":"1-3年","daysPerWeekDesc":"","leastMonthDesc":"","jobDegree":"本科","cityName":"广州","areaDistrict":"黄埔区","businessDistrict":"科学城","jobType":0,"proxyJob":0,"proxyType":0,"anonymous":0,"outland":0,"optimal":0,"iconFlagList":[],"itemId":0,"city":0,"isShield":0,"atsDirectPost":false,"gps":null,"encryptBrandId":"925e69201c11e22a1HR729y8","brandName":"广电运通集团","brandLogo":"https://img.bosszhipin.com/beijin/upload/com/workfeel/20210524/147014051f66c7c67a86617db33a09a61d294a6e650e5c5f97dc8dacd8542bbb.jpg","brandStageName":"已上市","brandIndustry":"互联网金融","brandScaleName":"10000人以上","welfareList":["带薪年假","餐补","加班补助","节日福利","员工旅游","定期体检","五险一金","免费班车","补充医疗保险","年终奖","包吃"],"industry":0,"contact":false,"showTopPosition":false},{"securityId":"K4SvhQvU3sfCL-a1P-9tmsfSdPz1tLA7sV0spceJTs4bcAvgWj1gBIc9tP_duVjUMq_n3NxIjAlL2w7tePrgHFbQlSHYq3jjOmKTcQnev2kMW68OhQElcN_k8a5ZWv4dX-m-HGk9ZeU9FdoNyNeTq1e9csXjwS3LEBI1b39Gn4JpcTZCXE2DmlPqAT_z-WAFYxY~","bossAvatar":"https://img.bosszhipin.com/beijin/upload/avatar/20201109/4cbe8cef9183b12bf4a54a498491af657b3d436515f212dc231936c5c4a9742e_s.png","bossCert":3,"encryptBossId":"e0dec9f44c7c9a800nR80ty9GVtW","bossName":"梁自强","bossTitle":"猎头顾问","goldHunter":1,"bossOnline":false,"encryptJobId":"ae772979e8d2f9941Hx929q0E1FW","expectId":1075456196,"jobName":"web全栈研发工程师","lid":"2336af9c-5481-4a67-883e-0949702398c8.f1:common.eyJzZXNzaW9uSWQiOiIyOGE2MGJkYi0wMjAzLTRkZmEtYTgxNy0zZmY2Y2Y0ZTM5NTkiLCJyY2RCelR5cGUiOiJmMV9ncmNkIn0.13","salaryDesc":"20-25K","jobLabels":["5-10年","本科","全栈无侧重","JavaScript","Java"],"jobValidStatus":1,"iconWord":"","skills":["全栈无侧重","JavaScript","Java"],"jobExperience":"5-10年","daysPerWeekDesc":"","leastMonthDesc":"","jobDegree":"本科","cityName":"广州","areaDistrict":"","businessDistrict":"","jobType":0,"proxyJob":1,"proxyType":1,"anonymous":101,"outland":0,"optimal":0,"iconFlagList":[8],"itemId":0,"city":0,"isShield":0,"atsDirectPost":false,"gps":null,"encryptBrandId":"","brandName":"某中型互联网视频通讯平台公司","brandLogo":"https://img.bosszhipin.com/beijin/app/mobile/n-af8dd288f86448d9eaaf3a4b0fd60b27.png","brandStageName":"","brandIndustry":"互联网","brandScaleName":"500-999人","welfareList":[],"industry":0,"contact":false,"showTopPosition":false},{"securityId":"17qfAXmeLJg4e-w1VCrHYSyINlrCrcQi_rRMbMLxxQWDXv2rGr1-y7X_K7KdaSZXKdQTUFDmbkID_bQGq5XRwbYVRIya-A1IVIcnvazdXSJJJPp-HzFliGnLcxKtalyxRa4WMYBBxS0YCzcHmjb4AAK2y5Mg3YoiX4Oy4GVhiPFtiSmEUbF6FWiEkY4n9mi6lQ~~","bossAvatar":"https://img.bosszhipin.com/beijin/upload/avatar/20230503/607f1f3d68754fd0b439fd8eb2239550497efb7e5fe0e5ab50c43b08372c8c488350525ee0ebd60e_s.jpg","bossCert":3,"encryptBossId":"d3507ee4d09aeebd0H1639y0FVA~","bossName":"张星","bossTitle":"招聘者","goldHunter":0,"bossOnline":false,"encryptJobId":"6adf71867ebfd4071H180tu7FlpU","expectId":1075456196,"jobName":"高级java工程师","lid":"2336af9c-5481-4a67-883e-0949702398c8.f1:common.eyJzZXNzaW9uSWQiOiIyOGE2MGJkYi0wMjAzLTRkZmEtYTgxNy0zZmY2Y2Y0ZTM5NTkiLCJyY2RCelR5cGUiOiJmMV9ncmNkIn0.14","salaryDesc":"15-25K","jobLabels":["5-10年","本科","Java","Nginx","Spring"],"jobValidStatus":1,"iconWord":"","skills":["Java","Nginx","Spring","MySQL","分布式经验","团队管理经验","不接受居家办公"],"jobExperience":"5-10年","daysPerWeekDesc":"","leastMonthDesc":"","jobDegree":"本科","cityName":"成都","areaDistrict":"武侯区","businessDistrict":"银泰城","jobType":0,"proxyJob":0,"proxyType":0,"anonymous":0,"outland":1,"optimal":0,"iconFlagList":[4],"itemId":0,"city":0,"isShield":0,"atsDirectPost":false,"gps":null,"encryptBrandId":"fbdcd42c3f9d67ba1XV43NS_FFY~","brandName":"捷怡互娱","brandLogo":"https://img.bosszhipin.com/beijin/upload/com/workfeel/20240619/7bf6f160950405e90a38d107443c775a2be3a4176b4043f050c43b08372c8c488350525ee0ebd60e.png.webp","brandStageName":"未融资","brandIndustry":"游戏","brandScaleName":"0-20人","welfareList":["餐补","节假日加班费","全勤奖","包住","绩效奖金","保底工资","底薪加提成","有无线网","宿舍有空调"],"industry":0,"contact":false,"showTopPosition":false},{"securityId":"fxMRWDDC1XREh-V1DARMb36L3ehHmRiFP1VcOTuWfJWUCwzKnOdx1QzORZo52gNNTk0kxy-wCBpMuejcOSqkV_Q_qa3SK6t1DE3X3-_k4NbVqMP3vYKhKrt71x_SLYHZOT8Gxr_hYNW7BM4UqgUdwaGtNrXBspsM8FdhvKnqhyMt9T8bbVsUq5VGrW2Qwpw~","bossAvatar":"https://img.bosszhipin.com/boss/avatar/avatar_9.png","bossCert":3,"encryptBossId":"a295c83569c83fb20nRy3N-4FlU~","bossName":"苏女士","bossTitle":"招聘专员","goldHunter":0,"bossOnline":false,"encryptJobId":"3ce4cce15749a9111Hx83N26FFpR","expectId":1075456196,"jobName":"Java开发工程师（车联网经验）","lid":"2336af9c-5481-4a67-883e-0949702398c8.f1:common.eyJzZXNzaW9uSWQiOiIyOGE2MGJkYi0wMjAzLTRkZmEtYTgxNy0zZmY2Y2Y0ZTM5NTkiLCJyY2RCelR5cGUiOiJmMV9ncmNkIn0.15","salaryDesc":"13-20K","jobLabels":["5-10年","大专","Java","SpringCloud","MySQL"],"jobValidStatus":1,"iconWord":"","skills":["Java","SpringCloud","MySQL"],"jobExperience":"5-10年","daysPerWeekDesc":"","leastMonthDesc":"","jobDegree":"大专","cityName":"苏州","areaDistrict":"相城区","businessDistrict":"元和","jobType":0,"proxyJob":0,"proxyType":0,"anonymous":0,"outland":1,"optimal":0,"iconFlagList":[4],"itemId":0,"city":0,"isShield":0,"atsDirectPost":false,"gps":null,"encryptBrandId":"f5373e2a011dbd271nJ73d68","brandName":"宽文是风","brandLogo":"https://img.bosszhipin.com/beijin/mcs/chatphoto/20160721/4237b0eb88c4b757423679865f076e045b4ce0e6f0596452019ebe0cd528710e.jpg","brandStageName":"不需要融资","brandIndustry":"计算机软件","brandScaleName":"100-499人","welfareList":["定期体检","五险一金","员工旅游","培训","节日福利","每月主题活动","工会","带薪年假","年终奖","零食下午茶"],"industry":0,"contact":false,"showTopPosition":false}],"type":1}}
//        log.info("返回结果：{}", result);
//        ZpResult zpResult = JsonUtils.parseObject(result, ZpResult.class);
//
//        // 请求职位{
//        //                "securityId": "viRs1e2xeVej7-T1-zXZG8vI5f2DfH9VYBvyTHkxzMByUJXqOeNXUSILQxUt9ghlp8bTET5POLVBVRCa6-BE9c6yY7T24gmcEu0kOSp2A6MTML6XwUTUDkFqRKXuf8MdAgVisSIKoGbHdhlvGO2jTFotJ3OVgL9YgEXkDJSubcQJyCQ9hx9jqcED4S4HLUkS_7A~",
//        //                "bossAvatar": "https://img.bosszhipin.com/beijin/upload/avatar/20240203/607f1f3d68754fd05e8b06afd34e491b11403a3ea124d387768e77cc2abe9682a4acba30d2b68b8c_s.jpg.webp",
//        //                "bossCert": 3,
//        //                "encryptBossId": "2ca723d88f23ef2d0nR83d6_FVVX",
//        //                "bossName": "陈女士",
//        //                "bossTitle": "高级招聘经理",
//        //                "goldHunter": 0,
//        //                "bossOnline": true,
//        //                "encryptJobId": "0d0aa53a9100c4d61XN-2NS_GFpS",
//        //                "expectId": 1075456196,
//        //                "jobName": "滴滴出行招聘新能源司机（广州）",
//        //                "lid": "2336af9c-5481-4a67-883e-0949702398c8.f1:common.eyJzZXNzaW9uSWQiOiIyOGE2MGJkYi0wMjAzLTRkZmEtYTgxNy0zZmY2Y2Y0ZTM5NTkiLCJyY2RCelR5cGUiOiJmMV9ncmNkIn0.3",
//        //                "salaryDesc": "12-18K",
//        //                "jobLabels": [
//        //                    "经验不限",
//        //                    "学历不限",
//        //                    "免费培训",
//        //                    "无交通肇事记录",
//        //                    "车辆保险/保养"
//        //                ],
//        //                "jobValidStatus": 1,
//        //                "iconWord": "",
//        //                "skills": [
//        //                    "免费培训",
//        //                    "无交通肇事记录",
//        //                    "车辆保险/保养",
//        //                    "免费道路救援",
//        //                    "熟悉本地路况"
//        //                ],
//        //                "jobExperience": "经验不限",
//        //                "daysPerWeekDesc": "",
//        //                "leastMonthDesc": "",
//        //                "jobDegree": "学历不限",
//        //                "cityName": "广州",
//        //                "areaDistrict": "天河区",
//        //                "businessDistrict": "东圃",
//        //                "jobType": 0,
//        //                "proxyJob": 0,
//        //                "proxyType": 0,
//        //                "anonymous": 0,
//        //                "outland": 0,
//        //                "optimal": 0,
//        //                "iconFlagList": [],
//        //                "itemId": 0,
//        //                "city": 0,
//        //                "isShield": 0,
//        //                "atsDirectPost": false,
//        //                "gps": null,
//        //                "encryptBrandId": "5b92f7993652b3d61nZ809y5FlM~",
//        //                "brandName": "滴滴出行",
//        //                "brandLogo": "https://img.bosszhipin.com/beijin/upload/com/logo/20240718/7b5b554d84f9729cadd302dbb4e9c6084ff5c53a46a200c986695a2ce1675ebf8431288eb784f76e.jpg.webp",
//        //                "brandStageName": "",
//        //                "brandIndustry": "互联网",
//        //                "brandScaleName": "10000人以上",
//        //                "welfareList": [
//        //                    "加班补助",
//        //                    "团建聚餐",
//        //                    "交通补助",
//        //                    "绩效奖金",
//        //                    "高温补贴",
//        //                    "五险一金",
//        //                    "节日福利"
//        //                ],
//        //                "industry": 0,
//        //                "contact": false,
//        //                "showTopPosition": false
//        //            }
//
//        //  https://www.zhipin.com/wapi/zpgeek/job/detail.json?securityId=Yl6DUfvvEpj8b-31YuVndKl6Xx3VYm-81e0uQHCmBLaL8INLJtE0QTepE6JmuZcaVbM5ySzSeX8WcxPMr2L7roqh75DyfcpMD8l_Sw1kuAGxCPhB_Jmx2Fz96orKL21d425vQu53QJn2OZ3umkNn1t5JxfZe5Hsiuz4EGXJ6aB9V9dE51p00G5Ijdz8FCUpsvYRMT3L7-ocfv48kB65_-jOXaybRzCHp3dGJPQ~~&lid=6465a9ca-09f3-4da1-8e31-57ce56756fd7.f1:common.eyJzZXNzaW9uSWQiOiI0Yzc0Y2UwZC1jOTU3LTRlOTgtYmM1OS0yNGU5OWNjM2RmMTMiLCJyY2RCelR5cGUiOiJmMV9ncmNkIn0.1
//        // 职位详情
//        // 然后返回 结果
//
//        // {"sessionId":"45f4dc52-e009-4401-9b60-f01eb248de7a","rcdBzType":"f1_grcd"
//        // {"sessionId":"28a60bdb-0203-4dfa-a817-3ff6cf4e3959","rcdBzType":"f1_grcd"
//        String addUrl = "https://www.zhipin.com/wapi/zpgeek/friend/add.json?securityId=x5FXHayiiL7WC-x1Hd9ldCAD63mMSO91nqcJosiCdCA9StXwIA5jopVM6-2Q3yk8YoUlhgZjI6jo-8ZiO1PusfC-w5F4l9n4ROJTXT9QyT05lCfcjMxmopn8j5j9Xn9ehweobif5JKRjtN0kjvuEB2EvwGsqxFvZJlL29Tlw0xpkRZAbJkoIWmhoCDnn4ICApqM~&jobId=0d0aa53a9100c4d61XN-2NS_GFpS&lid=44fafc54-0e35-4c77-8b86-f858096d34e5.f1:common.eyJzZXNzaW9uSWQiOiI0NWY0ZGM1Mi1lMDA5LTQ0MDEtOWI2MC1mMDFlYjI0OGRlN2EiLCJyY2RCelR5cGUiOiJmMV9ncmNkIn0.3";

    }
}
