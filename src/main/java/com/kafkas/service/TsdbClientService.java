package com.kafkas.service;

import com.baidubce.services.tsdb.TsdbClient;
import com.baidubce.services.tsdb.model.Datapoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by wushenjun on 17-1-9.
 * 百度时序数据库service处理
 */
@Service
public class TsdbClientService {
    //logger 日志处理
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TsdbClient tsdbClient;

    @PostConstruct
    public void init() {
        logger.info("测试百度时序数据库客户端");
        tsdbClient.getMetrics();
        List<Datapoint> datapointList = null;
        tsdbClient.writeDatapoints(datapointList);
    }
}
