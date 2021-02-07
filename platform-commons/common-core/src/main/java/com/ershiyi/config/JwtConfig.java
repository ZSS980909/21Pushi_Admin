package com.ershiyi.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    /** 签名密匙 **/
    private String secret;
    /** 签发人 **/
    private String issuer;
    /** 令牌有效时间(毫秒) **/
    private long expireTime;
    /** 令牌有效刷新时间(毫秒) **/
    private long refreshExpireTime;
    /** 认证头配置 **/
    private Header header = new Header();

    @Data
    public class Header{
        private String basic;
        private String bearer;

        public String getBasic() {
            return basic;
        }

        public void setBasic(String basic) {
            this.basic = basic;
        }

        public String getBearer() {
            return bearer;
        }

        public void setBearer(String bearer) {
            this.bearer = bearer;
        }
    }
}
