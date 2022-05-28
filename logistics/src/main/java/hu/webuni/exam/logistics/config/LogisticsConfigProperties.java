package hu.webuni.exam.logistics.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@ConfigurationProperties(prefix = "logistics")
@Component
public class LogisticsConfigProperties {

    private Jwt jwt = new Jwt();

    public static class Jwt {
        private String issuer;
        private String alg;
        private String secret;
        private Duration duration;
        public String getIssuer() {
            return issuer;
        }
        public void setIssuer(String issuer) {
            this.issuer = issuer;
        }
        public String getAlg() {
            return alg;
        }
        public void setAlg(String alg) {
            this.alg = alg;
        }
        public String getSecret() {
            return secret;
        }
        public void setSecret(String secret) {
            this.secret = secret;
        }
        public Duration getDuration() {
            return duration;
        }
        public void setDuration(Duration duration) {
            this.duration = duration;
        }
    }

    public Jwt getJwt() {
        return jwt;
    }

    public void setJwt(Jwt jwt) {
        this.jwt = jwt;
    }
}
