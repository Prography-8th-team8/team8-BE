package prography.cakeke.server.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import prography.cakeke.server.store.application.service.StoreService;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
@Rollback
public class BaseTest {
    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public StoreService storeService;

    @Value("${api.naver.search.localpath}")
    public String testLocalPath;

    @Value("${api.naver.search.clientID}")
    public String testClientID;

    @Value("${api.naver.search.clientSecretKey}")
    public String testClientSecretKey;

    public String testStoreName = "겸재케이크";
    public Integer testDisplayNum = 1;
    public Long testStoreId = 2L;

}
