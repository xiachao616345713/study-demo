package study.test1.backup;

import java.util.List;
import java.util.Map;

/**
 * @author chao
 * @since 2019-01-21
 */
public class MEmail {
//
//    //=======================from db======================
//
//    /**
//     * load email info
//     */
//    public void loadEmailInfoFromDb() {
//        List<Map<String, Object>> listConfig = getEmailConfig();
//        listConfig.forEach(item -> {
//            EmailConfig config = new EmailConfig();
//            config.setId(getObjectToString(item.get("id")));
//            config.setName(getObjectToString(item.get("name")));
//            config.setImapHost(getObjectToString(item.get("imap_h")));
//            config.setImapHHost(getObjectToString(item.get("imap_hh")));
//            config.setImapPort(getObjectToString(item.get("imap_p")));
//            config.setPopHost(getObjectToString(item.get("pop_h")));
//            config.setPopHHost(getObjectToString(item.get("pop_hh")));
//            config.setPopPort(getObjectToString(item.get("pop_p")));
//            config.setSmtpHost(getObjectToString(item.get("smtp_h")));
//            config.setSmtpHHost(getObjectToString(item.get("smtp_hh")));
//            config.setSmtpPort(getObjectToString(item.get("smtp_p")));
//            GlobalData.EMAIL_CONFIG_MAP.put(String.valueOf(item.get("id")), config);
//        });
//
////        GlobalData.EMAIL_CONFIG_MAP.putAll(listConfig.stream().collect(
////                Collectors.toMap(
////                        (Map<String, Object> itemK) -> String.valueOf(itemK.get("id")),
////                        (Map<String, Object> itemV) -> {
////                            EmailConfig config = new EmailConfig();
////                            config.setId(getObjectToString(itemV.get("id")));
////                            config.setName(getObjectToString(itemV.get("name")));
////                            config.setImapHost(getObjectToString(itemV.get("imap_h")));
////                            config.setImapHHost(getObjectToString(itemV.get("imap_hh")));
////                            config.setImapPort(getObjectToString(itemV.get("imap_p")));
////                            config.setPopHost(getObjectToString(itemV.get("pop_h")));
////                            config.setPopHHost(getObjectToString(itemV.get("pop_hh")));
////                            config.setPopPort(getObjectToString(itemV.get("pop_p")));
////                            config.setSmtpHost(getObjectToString(itemV.get("smtp_h")));
////                            config.setSmtpHHost(getObjectToString(itemV.get("smtp_hh")));
////                            config.setSmtpPort(getObjectToString(itemV.get("smtp_p")));
////                            return config;
////                        }
////                )));
////
//        List<Map<String, Object>> listSuffix = getEmailSuffix();
//        listSuffix.forEach(item -> GlobalData.EMAIL_SUFFIX_MAP.put((String) item.get("suffix"),
//                new EmailSuffix(String.valueOf(item.get("ec_id")), (String) item.get("suffix"))));
////        GlobalData.EMAIL_SUFFIX_MAP.putAll(listSuffix.stream().collect(
////                Collectors.toMap(
////                        (Map<String, Object> itemK) -> (String) itemK.get("suffix"),
////                        (Map<String, Object> itemV) -> new EmailSuffix((String) itemV.get("suffix"), String.valueOf(itemV.get("ec_id")))
////                )));
//    }
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    private List<Map<String, Object>> getEmailConfig() {
//        return jdbcTemplate.queryForList("select * from email_config");
//    }
//
//    private List<Map<String, Object>> getEmailSuffix() {
//        return jdbcTemplate.queryForList("select * from email_suffix");
//    }
//
//    /**
//     * 数据转换成字符串,null->""
//     */
//    private String getObjectToString(Object o) {
//        if (o instanceof String) {
//            return (String) o;
//        } else if (o == null) {
//            return "";
//        } else {
//            return String.valueOf(o);
//        }
//    }
}
