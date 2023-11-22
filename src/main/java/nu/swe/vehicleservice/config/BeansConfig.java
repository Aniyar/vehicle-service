package nu.swe.vehicleservice.config;

import feign.RequestInterceptor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Configuration class for beans creation or modification.
 */
@Configuration
public class BeansConfig {

    /**
     * This method is responsible for intercepting outgoing requests and adding 'Accept-Language' from original request.
     *
     * @return {@link RequestInterceptor}
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header("Accept-Language",
                LocaleContextHolder.getLocale().getLanguage());
    }

    /**
     * This method is responsible for MessageSource bean creation with custom source path.
     *
     * @return {@link MessageSource}
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:messages/errors", "classpath:messages/ValidationMessages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

}
