package center.misaki.config;

import center.misaki.domain.PathTotal;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {

    /**
     * 配置路径
     * @return 返回被注入值的路径类到spring容器内
     */
    @Bean
    @ConfigurationProperties("com.path")
    public PathTotal pathTotal(){
        return new PathTotal();
    }

}
