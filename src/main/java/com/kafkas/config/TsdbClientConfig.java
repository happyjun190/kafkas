package com.kafkas.config;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.tsdb.TsdbClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wushenjun on 17-1-9.
 * 测试百度时序数据库--客户端
 */
@Configuration
public class TsdbClientConfig {
    String ACCESS_KEY_ID = "aaa";//<your-access-key-id>;        // 用户的Access Key ID
    String SECRET_ACCESS_KEY = "bbb";//<your-secret-access-key> // 用户的Secret Access Key
    String ENDPOINT = "databasename.tsdb.iot.gz.baidubce.com";//<your-tsdb-database-endpoint>;    // 用户的时序数据库域名，形式如databasename.tsdb.iot.gz.baidubce.com

    @Bean
    public TsdbClient tsdbClient() {
        // 创建配置
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY))
                .withEndpoint(ENDPOINT);

        // 初始化一个TsdbClient
        TsdbClient tsdbClient = new TsdbClient(config);
        return tsdbClient;
    }


}
