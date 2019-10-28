package webflux.webflux.Config;

import org.springframework.web.reactive.config.PathMatchConfigurer;
import reactor.util.annotation.NonNull;

import java.util.function.Predicate;

public class PathMatchConfigurer_ extends PathMatchConfigurer {

    private PathMatchConfigurer configurer;

    @NonNull
    public PathMatchConfigurer addPrefix(String prefix, Predicate<Class<?>> predicate)
    {
        return configurer.addPathPrefix(prefix,predicate);
    }

    @NonNull
    public void setUserCaseSensitiveMatch(Boolean match)
    {
        configurer.setUseCaseSensitiveMatch(match);
    }
}
