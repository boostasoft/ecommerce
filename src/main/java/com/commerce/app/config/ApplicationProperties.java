package com.commerce.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Commerce.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private final ApplicationProperties.Adyen adyen = new ApplicationProperties.Adyen();
    public ApplicationProperties() {
    }

    public Adyen getAdyen() {
        return this.adyen;
    }

    public static class Adyen{
        private String apiKey;
        private String merchantAccount;
        private String originKey;

        public Adyen() {
        }

        public String getApiKey() {
            return apiKey;
        }

        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }

        public String getMerchantAccount() {
            return merchantAccount;
        }

        public void setMerchantAccount(String merchantAccount) {
            this.merchantAccount = merchantAccount;
        }

        public String getOriginKey() {
            return originKey;
        }

        public void setOriginKey(String originKey) {
            this.originKey = originKey;
        }
    }
}
